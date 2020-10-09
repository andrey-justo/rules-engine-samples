package org.justo.engine.rule.dao

import org.justo.engine.rule.model.RuleAttributes

interface AttributesDAO {

  fun get(userId: String): RuleAttributes

}
