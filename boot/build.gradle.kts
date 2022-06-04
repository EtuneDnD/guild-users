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
    testImplementation("org.springframework.security:spring-security-test")
}