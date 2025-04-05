# [Managing Dependencies with Gradle’s Bom Support](https://docs.spring.io/spring-boot/gradle-plugin/managing-dependencies.html#managing-dependencies.gradle-bom-support)

```kotlin
dependencies {
	implementation(platform(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES))
}
```

## Customizing Managed Versions

```kotlin
configurations.all {
    resolutionStrategy.eachDependency {
        if (requested.group == "org.slf4j") {
            useVersion("1.7.20")
        }
    }
}
```

# Mockito is currently self-attaching to enable the inline-mock-maker. This will no longer work in future releases of the JDK. Please add Mockito as an agent to your build what is described in Mockito's documentation: https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#0.3

- [Broken links to javadoc.io](https://github.com/mockito/mockito/issues/3615)
- [What is the mockito-inline MockMaker](https://www.baeldung.com/mockito-core-vs-mockito-inline)

Stick with the dynamic agent loading for now and ignore the error message

## Fix with expiry date

- needs [mockito update to 15.16.1](https://github.com/mockito/mockito/pull/3551) or later to get rid of the message
- [Mockito:5.14.2 Warning: Mockito is currently self-attaching...](https://github.com/mockito/mockito/issues/3512)

```kotlin
val enableAgentLoadingForMockito = listOf("-XX:+EnableDynamicAgentLoading")
tasks.test {
    jvmArgs(enableAgentLoadingForMockito)
}
```

## Fix for long term

- [Fix from the Mockito docs](https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/Mockito.html#mockito-instrumentation)

- Message was still displayed
- not clear how to pull in mockito version pulled in by spring boot

```toml
[libraries]
mockito = { module = "org.mockito:mockito-core" }
```

```kotlin
val mockitoAgent = configurations.create("mockitoAgent")
dependencies {
    testImplementation(libs.mockito)
    mockitoAgent(platform(SpringBootPlugin.BOM_COORDINATES))
    @Suppress("UnstableApiUsage")
    mockitoAgent(libs.mockito) { isTransitive = false }
}
tasks {
    test {
        @Suppress("UNNECESSARY_SAFE_CALL")
        jvmArgs?.add("-javaagent:${mockitoAgent.asPath}")
    }
}
```
