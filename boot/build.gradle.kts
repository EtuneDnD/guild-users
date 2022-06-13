java.sourceCompatibility = JavaVersion.VERSION_17

dependencies {
    implementation(project(":infrastructure:rest"))
    implementation(project(":infrastructure:flyway"))
    implementation(project(":infrastructure:jooq"))
    implementation(project(":application"))
    implementation(project(":domain"))
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.2")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.boot:spring-boot-starter-amqp:2.7.0")
    testImplementation("org.springframework.security:spring-security-test")
}

tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = true
}

tasks.getByName<Jar>("jar") {
    enabled = true
}