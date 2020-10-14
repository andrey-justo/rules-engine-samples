package org.justo.engine.rule.dao

import org.justo.engine.rule.model.RuleDefinition

// Rules
// !=, ==, and, or
// >, <, >=, <=
// in (contains), not in
// reuse rules inside rules????? (replacing by name)
class RulesDefinitionInMemory(
  private val definitions: List<RuleDefinition> =
    listOf(
      RuleDefinition("Attributes in A", "status == 'A'"),
      RuleDefinition("Attributes in A and disabled", "status == 'A' and enabled == false"),
      RuleDefinition("Attributes in A and enabled", "status == 'A' and enabled == true"),
      RuleDefinition("Attributes in B", "status == 'B'"),
      RuleDefinition("Attributes in B and enabled", "status == 'B' and enabled == true"),
      RuleDefinition("Attributes in B and disabled", "status == 'B' and enabled == false"),
      RuleDefinition("Attributes in A or B", "status == 'A' or status == 'B'"),
      RuleDefinition("Attributes are not C", "status != 'C'"),
      //RuleDefinition("Attributes are in (C, B)", "status in ('C', 'B')"),
      RuleDefinition("Attributes with score between 1 and 51", "score > 0 and score < 51"),
      RuleDefinition("Attributes with score between 0 and 50", "score >= 0 and score <= 50"),
    )
) : RulesDefinitionDAO {

  override fun get(): List<RuleDefinition> {
    return definitions
  }
}
