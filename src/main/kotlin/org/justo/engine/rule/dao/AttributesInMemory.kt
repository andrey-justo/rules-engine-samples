package org.justo.engine.rule.dao

import org.justo.engine.rule.model.RuleAttributes

class AttributesInMemory(
  private val attrs: Map<String, RuleAttributes> =
    mapOf(
      "1" to RuleAttributes(status = "A", enabled = true, score = 1.0),
      "2" to RuleAttributes(status = "B", enabled = false, score = 51.0),
      "3" to RuleAttributes(status = "B", enabled = true, score = 100.0),
      "4" to RuleAttributes(status = "C", enabled = true, score = 0.0)
    )
) : AttributesDAO {

  override fun get(userId: String): RuleAttributes {
    return attrs[userId]!!
  }

}
