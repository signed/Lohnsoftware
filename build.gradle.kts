plugins {
    java
    id("org.springframework.boot") version "3.4.1"
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
    implementation(platform(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES))
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("io.vavr:vavr:0.10.5")
    implementation("org.assertj:assertj-vavr:0.4.3")
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
