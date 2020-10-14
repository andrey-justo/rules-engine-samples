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
      RuleDefinition(name = "Attributes in A", query = "status == 'A'", executableName = "a"),
      RuleDefinition(
        name = "Attributes in A and disabled",
        query = "status == 'A' and enabled == false",
        executableName = "a_disabled"
      ),
      RuleDefinition(
        name = "Attributes in A and enabled",
        query = "status == 'A' and enabled == true",
        executableName = "a_enabled"
      ),
      RuleDefinition(name = "Attributes in B", query = "status == 'B'", executableName = "b"),
      RuleDefinition(
        name = "Attributes in B and enabled",
        query = "status == 'B' and enabled == true",
        executableName = "b_enabled"
      ),
      RuleDefinition(
        name = "Attributes in B and disabled",
        query = "status == 'B' and enabled == false",
        executableName = "b_disabled"
      ),
      RuleDefinition(
        name = "Attributes in A or B",
        query = "status == 'A' or status == 'B'",
        executableName = "a_or_b"
      ),
      RuleDefinition(name = "Attributes are not C", query = "status != 'C'", executableName = "not_c"),
      RuleDefinition(
        name = "Attributes are in (C, AB)",
        query = "{'C', 'AB'} contains status",
        executableName = "in_c-ab"
      ),
      RuleDefinition(
        name = "Attributes with score between 1 and 51",
        query = "score > 0 and score < 51",
        executableName = "1_to_50"
      ),
      RuleDefinition(
        name = "Attributes with score between 0 and 50",
        query = "score >= 0 and score <= 50",
        executableName = "0_to_50"
      ),
      RuleDefinition(
        name = "Attributes not are in (C, AB)",
        query = "!({'C', 'AB'} contains status)",
        executableName = "not_in_c-ab"
      ),
//      RuleDefinition(
//        name = "Composed rule",
//        query = "\$rules[\"not_in_c-ab\"] and $0_to_50",
//        executableName = "compose"
//      ),
    )
) : RulesDefinitionDAO {

  override fun get(): List<RuleDefinition> {
    return definitions
  }
}
