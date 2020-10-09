package org.justo.engine.rule.configurations

import org.justo.engine.rule.dao.RulesDefinitionDAO
import org.justo.engine.rule.dao.RulesDefinitionInMemory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class RulesDefinitionConfiguration {

  @Bean
  open fun rulesDefinitionDAO(): RulesDefinitionDAO = RulesDefinitionInMemory()

}
