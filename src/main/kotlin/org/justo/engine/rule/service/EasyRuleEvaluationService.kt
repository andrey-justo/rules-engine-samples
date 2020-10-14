package org.justo.engine.rule.service

import org.jeasy.rules.api.*
import org.jeasy.rules.core.DefaultRulesEngine
import org.jeasy.rules.mvel.MVELRule
import org.justo.engine.rule.dao.AttributesDAO
import org.justo.engine.rule.dao.RulesDefinitionDAO
import org.justo.engine.rule.model.RuleAttributes

class EasyRuleEvaluationService(
  private val attributesDao: AttributesDAO,
  private val rulesDefinitionDao: RulesDefinitionDAO
) : EngineRule(alg = EngineRuleAlg.EASY_J) {

  companion object {
    const val EFFECTS_RETURN = "EFFECTS"
  }

  override fun group(userId: String): List<String> {
    val attributes = attributesDao.get(userId)
    val definitions = rulesDefinitionDao.get()
    // Engine rules down
    val effectRules: List<String> = mutableListOf()
    val facts: Facts = toFacts(attributes, effectRules)

    val conditions = definitions.map {
      MVELRule()
        .name(it.executableName)
        .priority(0)
        .description(it.name)
        .`when`(it.query)
        .then("${EFFECTS_RETURN}.add(\"${it.name}\")")
    }

    val rules = Rules(*conditions.toTypedArray())
    val engine: RulesEngine = DefaultRulesEngine()
    engine.fire(rules, facts)


    return effectRules
  }

  private fun toFacts(attributes: RuleAttributes, effectRules: List<String>): Facts {
    val facts = Facts()
    facts.add(Fact("enabled", attributes.enabled))
    facts.add(Fact("status", attributes.status))
    facts.add(Fact("score", attributes.score))
    facts.add(Fact(EFFECTS_RETURN, effectRules))
    return facts
  }

}
