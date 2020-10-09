package org.justo.engine.rule.service

class CompositeEngineRules(val engineRules: Map<EngineRuleAlg, EngineRule>): Evaluate {
  override fun group(userId: String, algorithm: String): List<String> {
    val alg = EngineRuleAlg.valueOf(algorithm.toUpperCase())
    return engineRules[alg]!!.group(userId)
  }
}
