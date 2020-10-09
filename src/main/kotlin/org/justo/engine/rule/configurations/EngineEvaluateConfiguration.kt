package org.justo.engine.rule.configurations

import org.justo.engine.rule.dao.AttributesDAO
import org.justo.engine.rule.dao.RulesDefinitionDAO
import org.justo.engine.rule.service.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class EngineEvaluateConfiguration {

  @Bean
  @Autowired
  open fun engine(attributesDAO: AttributesDAO, rulesDefinitionDAO: RulesDefinitionDAO): Evaluate =
    CompositeEngineRules(
      mapOf(
        EngineRuleAlg.EASY_J to EasyRuleEvaluationService(attributesDAO, rulesDefinitionDAO),
        EngineRuleAlg.DROOLS to DroolsEvaluationService(attributesDAO, rulesDefinitionDAO)
      )
    )

}
