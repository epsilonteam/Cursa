group = "net.spartanb312"
version = "1.0-SNAPSHOT"

plugins {
    java
    kotlin("jvm") version "2.1.0"
    id("fabric-loom") version "1.7-SNAPSHOT"
}

val loader_version: String by rootProject
val fkotlin_version: String by rootProject
val yarn_mappings: String by rootProject
val minecraft_version: String by rootProject
val fabric_version: String by rootProject

val kmogusVersion = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://maven.luna5ama.dev/")
    maven("https://mvnrepository.com/artifact/")
}

val library: Configuration by configurations.creating {
    configurations.implementation.get().extendsFrom(this)
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraft_version")
    mappings("net.fabricmc:yarn:$yarn_mappings:v2")

    modImplementation("net.fabricmc:fabric-loader:$loader_version")
    //modImplementation("net.fabricmc:fabric-language-kotlin:$fkotlin_version")
    modImplementation("net.fabricmc.fabric-api:fabric-api:$fabric_version")
    modImplementation("net.fabricmc.fabric-api:fabric-renderer-indigo:$fabric_version")

    library(kotlin("stdlib"))
    library("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    library("dev.luna5ama:kmogus-core:$kmogusVersion")
    library("dev.luna5ama:kmogus-struct-api:$kmogusVersion")
}

loom {
    accessWidenerPath.assign(file("src/main/resources/cursa.accesswidener"))
}

kotlin {
    jvmToolchain(21)
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(21)
    }

    compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf(
                "-opt-in=kotlin.RequiresOptIn",
                "-opt-in=kotlin.contracts.ExperimentalContracts",
                "-Xlambdas=indy",
                "-Xjvm-default=all",
                "-Xbackend-threads=0",
                "-Xno-source-debug-extension"
            )
        }
        compilerOptions {
            languageVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_1)
            apiVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_1)
        }
    }

    processResources {
        inputs.property("version", project.version)
        filesMatching("fabric.mod.json") {
            expand(getProperties())
            expand(mutableMapOf("version" to project.version))
        }
    }

    jar {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE

        from(library.map {
            if (it.isDirectory) {
                it
            } else {
                zipTree(it)
            }
        })

        exclude("META-INF/*.RSA", "META-INF/*.DSA", "META-INF/*.SF")
    }

}