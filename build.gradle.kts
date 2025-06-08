import org.springframework.boot.gradle.plugin.SpringBootPlugin
import nl.littlerobots.vcu.plugin.versionSelector
import nl.littlerobots.vcu.plugin.resolver.VersionSelectors

plugins {
    java
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.version.catalog.update)
    id("com.diffplug.spotless") version "7.0.1"
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

versionCatalogUpdate {
    versionSelector(VersionSelectors.STABLE)
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

spotless {
    java {
        prettier(mapOf("prettier" to "3.4.2", "prettier-plugin-java" to "2.6.5"))
            .config(mapOf("parser" to "java", "tabWidth" to 4, "plugins" to listOf("prettier-plugin-java")))
            .configFile(file(".prettierrc.yaml"))

        toggleOffOn()
        removeUnusedImports()
        trimTrailingWhitespace()
        endWithNewline()
        formatAnnotations()
    }
}
