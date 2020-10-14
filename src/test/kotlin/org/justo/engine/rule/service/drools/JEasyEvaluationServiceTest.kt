package org.justo.engine.rule.service.drools

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.justo.engine.rule.dao.AttributesInMemory
import org.justo.engine.rule.dao.RulesDefinitionInMemory
import org.justo.engine.rule.service.DroolsEvaluationService
import org.justo.engine.rule.service.EasyRuleEvaluationService

class JEasyEvaluationServiceTest {

  val definitions = RulesDefinitionInMemory()
  val attributes = AttributesInMemory()
  val service = EasyRuleEvaluationService(attributesDao = attributes, rulesDefinitionDao = definitions)

  @Test
  fun `basic jeasy test`() {
    val segments = service.group("1")
    println(segments)
    Assertions.assertNotNull(segments)
  }

  @Test
  fun `user 4, should not be in not in Segment C`() {
    val segments = service.group("4")
    println(segments)
    Assertions.assertNotNull(segments)
    Assertions.assertTrue(segments.none { it == "Attributes are not C" })
  }

  @Test
  fun `user 5, should be in a contains segment`() {
    val segments = service.group("5")
    println(segments)
    Assertions.assertNotNull(segments)
    Assertions.assertTrue(segments.any { it == "Attributes are in (C, AB)" })
  }
}
