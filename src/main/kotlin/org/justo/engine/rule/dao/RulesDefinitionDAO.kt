package org.justo.engine.rule.dao

import org.justo.engine.rule.model.RuleDefinition

interface RulesDefinitionDAO {

  fun get(): List<RuleDefinition>

}
