java.sourceCompatibility = JavaVersion.VERSION_17

dependencies {
    implementation("com.google.firebase:firebase-admin:8.1.0")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.2")
}

tasks.getByName<Jar>("jar") {
    enabled = true
}