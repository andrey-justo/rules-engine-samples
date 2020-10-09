package org.justo.engine.rule.dao

import org.justo.engine.rule.model.RuleAttributes

class AttributesInMemory(
  private val attrs: Map<String, RuleAttributes> =
    mapOf(
      "1" to RuleAttributes("A", true),
      "2" to RuleAttributes("B", false),
      "3" to RuleAttributes("B", true)
    )
) : AttributesDAO {

  override fun get(userId: String): RuleAttributes {
    return attrs[userId]!!
  }

}
