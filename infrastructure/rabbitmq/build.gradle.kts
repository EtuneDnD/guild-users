java.sourceCompatibility = JavaVersion.VERSION_17

dependencies {
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-amqp
    implementation("org.springframework.boot:spring-boot-starter-amqp:2.7.0")
}

tasks.getByName<Jar>("jar") {
    enabled = true
}