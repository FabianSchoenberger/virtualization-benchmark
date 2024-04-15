plugins {
    application
    kotlin("jvm") version "1.9.22"
}

application {
    mainClass = "MainKt"
}

group = "org.example"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.http4k:http4k-bom:5.15.0.0"))
    implementation("org.http4k:http4k-core")
}

kotlin {
    jvmToolchain(21)
}
