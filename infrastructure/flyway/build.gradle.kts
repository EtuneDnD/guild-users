java.sourceCompatibility = JavaVersion.VERSION_17

dependencies {
    implementation("org.flywaydb:flyway-core")
}

tasks.getByName<Jar>("jar") {
    enabled = true
}