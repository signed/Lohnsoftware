import org.springframework.boot.gradle.plugin.SpringBootPlugin
import nl.littlerobots.vcu.plugin.resolver.VersionSelectors
import net.ltgt.gradle.errorprone.errorprone
import net.ltgt.gradle.nullaway.nullaway

plugins {
    java
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.version.catalog.update)
    alias(libs.plugins.errorprone)
    alias(libs.plugins.nullawy)
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
    errorprone(libs.nullaway)
    errorprone(libs.errorprone)
    implementation(libs.jspecify)
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

tasks.withType<JavaCompile>().configureEach {
    options.errorprone {
        disableWarningsInGeneratedCode.set(true)
        disable("UnicodeInCode")
    }
    options.errorprone.nullaway {
        // https://github.com/tbroyer/gradle-nullaway-plugin?tab=readme-ov-file#properties
        error()
        unannotatedSubPackages.add("com.foo.baz")
        excludedClasses = listOf("example.lohnsoftware.http.AktualisiereArbeitsstundenRequestDTO")
        excludedFieldAnnotations = listOf("org.junit.jupiter.api.io.TempDir")
    }
}

nullaway {
    annotatedPackages.add("example")
    // OR, starting with NullAway 0.12.3 and if you use JSpecify @NullMarked:
    // onlyNullMarked = true
}