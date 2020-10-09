package org.justo.engine.rule

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext

@SpringBootApplication(scanBasePackages = ["org.justo.engine.rule"])
open class App: ApplicationContextInitializer<GenericApplicationContext> {
  // Register beans
  override fun initialize(applicationContext: GenericApplicationContext) {

  }
}

fun main(args: Array<String>) {
  runApplication<App>(*args)
}
