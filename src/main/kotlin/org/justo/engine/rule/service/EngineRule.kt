package org.justo.engine.rule.service

abstract class EngineRule(val alg: EngineRuleAlg) {

  abstract fun group(userId: String): List<String>

}
