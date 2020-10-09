package org.justo.engine.rule.service

interface Evaluate {

  fun group(userId: String, algorithm: String): List<String>

}
