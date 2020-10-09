plugins {
  // Apply the Kotlin JVM plugin to add support for Kotlin.
  id("org.jetbrains.kotlin.jvm") version "1.4.10"
  id("org.jlleitschuh.gradle.ktlint-idea").version("9.2.1")
  id("jacoco")
  id("org.springframework.boot").version("2.3.4.RELEASE")
  id("io.spring.dependency-management").version("1.0.10.RELEASE")

  // Apply the application plugin to add support for building a CLI application.
  application
}

val junitVersion = "5.6.2"
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "1.8"
  }
}
repositories {
  mavenCentral()
}

application {
  // Define the main class for the application.
  mainClassName = "org.justo.engine.rule.AppKt"
}


dependencies {
  // Align versions of all Kotlin components
  implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.0")
  developmentOnly("org.springframework.boot:spring-boot-devtools")
  implementation("commons-io:commons-io:2.6")

  implementation("com.google.guava:guava:29.0-jre")

  implementation("io.github.resilience4j:resilience4j-circuitbreaker:1.5.0")

  implementation("ch.qos.logback:logback-classic:1.2.3")

  // Rules Engine
  // Drools
  implementation("org.kie:kie-ci:7.43.1.Final")
  implementation("org.drools:drools-compiler:7.43.1.Final")
  implementation("org.drools:drools-core:7.43.1.Final")
  implementation("org.drools:drools-decisiontables:7.43.1.Final")
  // Easy Rule
  implementation("org.jeasy:easy-rules-core:4.0.0")
  implementation("org.jeasy:easy-rules-mvel:4.0.0")


  // Use the Kotlin JDK 8 standard library.
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

  // Use the Kotlin test library.
  testImplementation("org.jetbrains.kotlin:kotlin-test")

  // Use the Kotlin JUnit integration.
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
  testImplementation("org.mockito:mockito-junit-jupiter:2.23.0")
  testImplementation("org.mockito:mockito-core:3.4.3")
  testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
  testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
  testImplementation("com.github.tomakehurst:wiremock:2.27.2")
}


jacoco {
  toolVersion = "0.8.5"
  reportsDir = file("$buildDir/codeCoverageReport")
}

tasks.test {
  finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
  dependsOn(tasks.test) // tests are required to run before generating the report
}

tasks.test {
  useJUnitPlatform {
    filter {
      excludeTags("integration-test")
    }
    includeEngines("junit-jupiter")
  }
  testLogging {
    events("passed", "skipped", "failed")
  }
}
