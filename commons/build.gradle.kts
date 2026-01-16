tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}

apply(plugin = "java-test-fixtures")

dependencies {
    "testFixturesImplementation"("org.springframework.boot:spring-boot-starter-test")
    "testFixturesImplementation"("org.jetbrains.kotlin:kotlin-test-junit5")
    "testFixturesImplementation"("io.rest-assured:rest-assured:5.3.0")
    "testFixturesImplementation"("org.springframework.cloud:spring-cloud-starter-contract-stub-runner")
    "testFixturesImplementation"(project(":transaction"))
    "testFixturesImplementation"(project(":account"))
}