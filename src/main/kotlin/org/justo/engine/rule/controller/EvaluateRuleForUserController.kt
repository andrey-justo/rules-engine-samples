package org.justo.engine.rule.controller

import org.justo.engine.rule.service.Evaluate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/users/{userId}/segments")
class EvaluateRuleForUserController(@Autowired val evaluate: Evaluate) {

  @GetMapping
  fun get(@PathVariable userId: String, @RequestParam("algorithm") alg: String): SegmentsResponse {
    return SegmentsResponse(evaluate.group(userId, alg))
  }

}
