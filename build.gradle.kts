import org.springframework.boot.gradle.plugin.SpringBootPlugin
import nl.littlerobots.vcu.plugin.resolver.VersionSelectors
import net.ltgt.gradle.errorprone.errorprone
import net.ltgt.gradle.nullaway.nullaway

plugins {
    java
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.version.catalog.update)
    id("net.ltgt.errorprone") version "4.3.0"
    id("net.ltgt.nullaway") version "2.3.0"
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
    errorprone("com.uber.nullaway:nullaway:0.12.9")
    errorprone("com.google.errorprone:error_prone_core:2.41.0")
    implementation("org.jspecify:jspecify:1.0.0")
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
        error()
        unannotatedSubPackages.add("com.foo.baz")
    }
}

nullaway {
    annotatedPackages.add("example")
    // OR, starting with NullAway 0.12.3 and if you use JSpecify @NullMarked:
    // onlyNullMarked = true
}