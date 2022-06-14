import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.lang.Thread.sleep


java.sourceCompatibility = JavaVersion.VERSION_17

plugins {
    id("org.springframework.boot") version "2.6.7"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("kapt") version "1.6.21"
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
    @Input
    var function: ((String, String) -> ProcessBuilder)? = null

    @TaskAction
    fun deployPostgresSQL() {

        val p = function?.invoke(
            "docker-compose -f docker/docker-compose.yaml up -d db",
            "docker-compose -f docker/docker-compose.yaml up -d db"
        )

        val results: List<String>? = p?.start()?.errorStream?.bufferedReader()?.readLines()

        results?.forEach {
            println(it)
        }
    }

}

abstract class BuildDockerImage : DefaultTask() {
    @Input
    lateinit var function: ((String, String) -> ProcessBuilder)

    @TaskAction
    fun buildDockerImage() {

        val p = function.invoke(
            "docker build -f docker/Dockerfile . -t guild-users:local",
            "docker build -f docker/Dockerfile . -t guild-users:local"
        )

        val results: List<String>? = p.start()?.errorStream?.bufferedReader()?.readLines()

        results?.forEach {
            println(it)
        }
    }
}

abstract class DeployDockerCompose : DefaultTask() {
    @Input
    lateinit var function: (String, String) -> ProcessBuilder

    @TaskAction
    fun deployDockerCompose() {

        val p = function.invoke(
            "docker-compose -f docker/docker-compose.yaml up -d",
            "docker-compose -f docker/docker-compose.yaml up -d"
        )

        p.start()
    }

}

abstract class DeployLocalEnv : DefaultTask() {
}

fun generateProcessDependingOnOS(win: String, other: String): ProcessBuilder {
    val os: String = System.getProperty("os.name", "generic").toLowerCase()

    return when {
        os.contains("win") -> ProcessBuilder(
            "cmd.exe",
            "/c",
            win
        )
        else ->
            ProcessBuilder(
                other
            )
    }
}

tasks.register<DeployPostgresSQL>("deployPostgresSQL") {
    function = ::generateProcessDependingOnOS
    doLast {
        sleep(20 * 1000) // TODO Read Output of docker deploy instead of sleep
    }
}

tasks.register<BuildDockerImage>("buildDockerImage") {
    function = ::generateProcessDependingOnOS
    val build = getTasksByName("build", true)
    dependsOn(build)
    doLast {
        sleep(10 * 1000)
    }
}

tasks.register<DeployDockerCompose>("deployDockerCompose") {
    function = ::generateProcessDependingOnOS
    val buildDockerImage = tasks["buildDockerImage"]

    dependsOn(buildDockerImage)
}

tasks.register<DeployLocalEnv>("deployLocalEnv") {
    val deployPostgresSQL = tasks["deployPostgresSQL"]
    val flywayMigrate = getTasksByName("flywayMigrate", true).toList()[0]
    val deployDockerCompose = tasks["deployDockerCompose"]

    dependsOn(deployPostgresSQL)
    dependsOn(flywayMigrate)
    dependsOn(deployDockerCompose)

    deployDockerCompose.mustRunAfter(flywayMigrate)
    flywayMigrate.mustRunAfter(deployPostgresSQL)
}