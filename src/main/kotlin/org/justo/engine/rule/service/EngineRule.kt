package org.justo.engine.rule.service

abstract class EngineRule(alg: EngineRuleAlg) {

  abstract fun group(userId: String): List<String>

}
