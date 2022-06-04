java.sourceCompatibility = JavaVersion.VERSION_17

tasks.getByName<Jar>("jar") {
    enabled = true
}

dependencies {
    implementation(project(":domain"))
}