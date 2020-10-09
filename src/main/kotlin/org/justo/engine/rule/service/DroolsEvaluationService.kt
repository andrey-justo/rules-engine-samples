package org.justo.engine.rule.service

import org.justo.engine.rule.dao.AttributesDAO
import org.justo.engine.rule.dao.RulesDefinitionDAO
import org.justo.engine.rule.model.RuleDefinition
import org.kie.api.KieServices
import org.kie.api.runtime.KieContainer
import org.kie.api.runtime.KieSession


class DroolsEvaluationService(
  val attributesDao: AttributesDAO,
  val rulesDefinitionDAO: RulesDefinitionDAO
) : EngineRule(alg = EngineRuleAlg.DROOLS) {

  private var kieContainer: KieContainer? = null

  override fun group(userId: String): List<String> {
    val attributes = attributesDao.get(userId)
    val rules = rulesDefinitionDAO.get()
    loadContainerFromString(rules)
    val globalList: List<String> = mutableListOf()
    val kieSession: KieSession = kieContainer!!.newKieSession()
    kieSession.setGlobal("list", globalList)
    kieSession.insert(attributes)
    kieSession.fireAllRules()
    kieSession.dispose()

    return globalList
  }

  private fun loadContainerFromString(rules: List<RuleDefinition>) {
    var startTime = System.currentTimeMillis()
    val ks = KieServices.Factory.get()
    val kr = ks.repository
    val kfs = ks.newKieFileSystem()
    for (rule in rules) {
      val replacement = "\$r"
      val drl: String = """
        package org.justo.engine.rule;
        import org.justo.engine.rule.model.RuleAttributes;
        global java.util.List list

        rule "${rule.name}"
          dialect  "mvel"
        when
          $replacement : RuleAttributes(${rule.query.replace("and", ", ")})
        then
          list.add("${rule.name}")
        end
      """
      kfs.write("src/main/resources/" + drl.hashCode() + ".drl", drl)
    }
    val kb = ks.newKieBuilder(kfs)
    kb.buildAll()
    if (kb.results.hasMessages(org.kie.api.builder.Message.Level.ERROR)) {
      throw RuntimeException(
        """
            Build Errors:
            ${kb.results}
            """.trimIndent()
      )
    }
    var endTime = System.currentTimeMillis()
    println("Time to build rules : " + (endTime - startTime) + " ms")
    startTime = System.currentTimeMillis()
    val kContainer = ks.newKieContainer(kr.defaultReleaseId)
    endTime = System.currentTimeMillis()
    println("Time to load container: " + (endTime - startTime) + " ms")
    kieContainer = kContainer
  }

}
