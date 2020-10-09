package org.justo.engine.rule.dao

import org.justo.engine.rule.model.RuleDefinition

// Rules
// !=, ==, and, or
// >, <, >=, <=
// in (contains)
// reuse rules inside rules????? (replacing by name)
class RulesDefinitionInMemory(
  private val definitions: List<RuleDefinition> =
    listOf(
      RuleDefinition("Attributes in A", "status == 'A'"),
      RuleDefinition("Attributes in A and disabled", "status == 'A' and enabled == false"),
      RuleDefinition("Attributes in A and enabled", "status == 'A' and enabled == true"),
      RuleDefinition("Attributes in B", "status == 'B'"),
      RuleDefinition("Attributes in B and enabled", "status == 'B' and enabled == true"),
      RuleDefinition("Attributes in B and disabled", "status == 'B' and enabled == false")
    )
) : RulesDefinitionDAO {

  override fun get(): List<RuleDefinition> {
    return definitions
  }
}
