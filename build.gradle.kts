plugins {
    kotlin("jvm") version "2.1.10"
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
}

group = "dev.rohenkohl"
version = "1.0-SNAPSHOT"