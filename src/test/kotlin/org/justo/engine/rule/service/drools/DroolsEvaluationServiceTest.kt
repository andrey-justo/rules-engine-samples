package org.justo.engine.rule.service.drools


import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.justo.engine.rule.dao.AttributesInMemory
import org.justo.engine.rule.dao.RulesDefinitionInMemory
import org.justo.engine.rule.service.DroolsEvaluationService

// Drools need to be worked to support all rules that we want
@Disabled
class DroolsEvaluationServiceTest {

  @Test
  fun `basic drool test`() {
    val definitions = RulesDefinitionInMemory()
    val attributes = AttributesInMemory()
    val service = DroolsEvaluationService(attributesDao = attributes, rulesDefinitionDAO = definitions)

    val segments = service.group("1")
    Assertions.assertNotNull(segments)
  }

}
