import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "dev.lupluv"
version = "0.3.1"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://repo.papermc.io/repository/maven-public/")
}

val shadowDependencies = listOf(
    "com.google.code.gson:gson:2.9.1",
    "org.danilopianini:khttp:1.3.1"
)

dependencies {
    testImplementation(kotlin("test"))

    compileOnly("io.papermc.paper:paper-api:1.20.2-R0.1-SNAPSHOT")

    shadowDependencies.forEach {
        implementation(it)
        shadow(it)
    }
}

tasks {
    build {
        dependsOn(shadowJar)
    }
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "20"
        kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
    }
    withType<ShadowJar> {
        mergeServiceFiles()
        configurations = listOf(project.configurations.shadow.get())
        archiveFileName.set("SimpleGamemode-${version}.jar")
    }
}