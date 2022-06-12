java.sourceCompatibility = JavaVersion.VERSION_17

dependencies {
    implementation("com.google.firebase:firebase-admin:8.1.0")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.2")
    implementation(project(":application"))
    implementation(project(":domain"))
}

tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}