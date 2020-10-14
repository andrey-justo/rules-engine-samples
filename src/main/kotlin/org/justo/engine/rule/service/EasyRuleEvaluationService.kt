package org.justo.engine.rule.service

import org.jeasy.rules.api.Fact
import org.jeasy.rules.api.Facts
import org.jeasy.rules.api.Rule
import org.jeasy.rules.mvel.MVELRule
import org.justo.engine.rule.dao.AttributesDAO
import org.justo.engine.rule.dao.RulesDefinitionDAO
import org.justo.engine.rule.model.RuleAttributes

class EasyRuleEvaluationService(
  private val attributesDao: AttributesDAO,
  private val rulesDefinitionDao: RulesDefinitionDAO
) : EngineRule(alg = EngineRuleAlg.EASY_J) {

  override fun group(userId: String): List<String> {
    val attributes = attributesDao.get(userId)
    val definitions = rulesDefinitionDao.get()
    // Engine rules down
    val facts: Facts = toFacts(attributes)

    return definitions.mapNotNull {
      val rule: Rule = MVELRule()
        .name(it.name)
        .`when`(it.query)

      if (rule.evaluate(facts)) it.name else null
    }
  }

  private fun toFacts(attributes: RuleAttributes): Facts {
    val facts = Facts()
    facts.add(Fact("enabled", attributes.enabled))
    facts.add(Fact("status", attributes.status))
    facts.add(Fact("score", attributes.score))
    return facts
  }

}
