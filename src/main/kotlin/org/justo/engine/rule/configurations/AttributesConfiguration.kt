package org.justo.engine.rule.configurations

import org.justo.engine.rule.dao.AttributesDAO
import org.justo.engine.rule.dao.AttributesInMemory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class AttributesConfiguration {

  @Bean
  open fun attributesDAO(): AttributesDAO = AttributesInMemory()

}
