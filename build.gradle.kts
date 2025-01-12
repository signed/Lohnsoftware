plugins {
    java
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
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
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("io.vavr:vavr:0.10.5")
    implementation("org.assertj:assertj-vavr:0.4.3")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val enableAgentLoadingForMockito = listOf("-XX:+EnableDynamicAgentLoading")
tasks.test {
    jvmArgs(enableAgentLoadingForMockito)
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
