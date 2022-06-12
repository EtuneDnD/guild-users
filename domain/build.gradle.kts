java.sourceCompatibility = JavaVersion.VERSION_17

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.2")
}

tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}