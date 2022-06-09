java.sourceCompatibility = JavaVersion.VERSION_17

dependencies {
    implementation("org.postgresql:postgresql:42.3.4")
    implementation("org.flywaydb:flyway-core")
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

plugins {
    id("org.flywaydb.flyway") version "8.5.12"
}

flyway {
    url = "jdbc:postgresql://localhost:5432/test"
    user = "postgres"
    password = "example"
    schemas = listOf("public").toTypedArray()
}