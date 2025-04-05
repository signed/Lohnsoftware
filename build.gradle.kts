import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    java
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.versions)
    alias(libs.plugins.versions.latest)
}

group = "example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation(libs.vavr)
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation(libs.assertj.vavr)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

configurations.all {
    resolutionStrategy.eachDependency {
        if (requested.group == "org.mockito") {
            useVersion("5.17.0")
        }
    }
}

tasks {
    test {
        jvmArgs(
            jvmArgsList()
        )
    }
}

fun jvmArgsList(): List<String> {
    val mockitoAgent = configurations.testRuntimeClasspath.get().find { it.name.contains("mockito-core") }
    return listOf("-javaagent:$mockitoAgent")
}
