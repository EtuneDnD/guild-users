import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

java.sourceCompatibility = JavaVersion.VERSION_17

plugins {
    id("org.springframework.boot") version "2.6.7"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("kapt") version  "1.6.21"
}

tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = false
}

allprojects {

    group = "com.guild"
    version = "0.0.1-SNAPSHOT"

    apply {
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("kotlin-kapt")
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        kapt("org.mapstruct:mapstruct-processor:1.5.1.Final")
        implementation(kotlin("stdlib-jdk8"))
        implementation("org.mapstruct:mapstruct:1.5.1.Final")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("dev.forkhandles:fabrikate4k:2.2.0.0")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("io.strikt:strikt-jvm:0.34.1")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

abstract class DeployPostgresSQL : DefaultTask() {
    @TaskAction
    fun deployPostgresSQL() {
        val p = ProcessBuilder(
            "cmd.exe",
            "/c",
            "docker-compose -f docker/docker-compose.yaml up -d db"
        ).start()

        val results: List<String> = p.errorStream.bufferedReader().readLines()

        results.forEach{
            println(it)
        }
    }

}

abstract class BuildDockerImage : DefaultTask() {
    @TaskAction
    fun buildDockerImage() {
        val p = ProcessBuilder(
            "cmd.exe",
            "/c",
            "docker build -f docker/Dockerfile . -t guild-users:local"
        ).start()

        val results: List<String> = p.errorStream.bufferedReader().readLines()

        results.forEach{
            println(it)
        }
    }
}

abstract class DeployDockerCompose : DefaultTask() {
    @TaskAction
    fun deployDockerCompose() {
        val p = ProcessBuilder(
            "cmd.exe",
            "/c",
            "docker-compose -f docker/docker-compose.yaml up -d"
        ).start()

        val results: List<String> = p.errorStream.bufferedReader().readLines()

        results.forEach{
            println(it)
        }
    }

}

abstract class Asd : DefaultTask() {
    @TaskAction
    fun asd() {
    }

}

tasks.register<DeployPostgresSQL>("deployPostgresSQL"){
}

tasks.register<BuildDockerImage>("buildDockerImage"){
    val build = getTasksByName("build", true)
    dependsOn(build)
}

tasks.register<DeployDockerCompose>("deployDockerCompose"){
    val buildDockerImage = tasks["buildDockerImage"]

    dependsOn(buildDockerImage)
}

tasks.register<Asd>("asd"){
    val b = tasks["deployPostgresSQL"]
    val flywayMigrate = getTasksByName("flywayMigrate", true)
    val a = tasks["deployDockerCompose"]

    dependsOn(flywayMigrate)
    dependsOn(a)
    dependsOn(b)

    a.mustRunAfter(flywayMigrate)
    flywayMigrate.
}


// Levantar POSTGRESS CONTAINER
// Correr Flyway
// Build
// Crear imagen de guild-users
// Levantar docker compose