import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.2.6.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	id("io.gitlab.arturbosch.detekt") version("1.8.0")
	kotlin("jvm") version "1.3.71"
	kotlin("plugin.spring") version "1.3.71"
}

group = "com.springBootApp"
version = "1.0"
java.sourceCompatibility = JavaVersion.VERSION_1_8

buildscript {
	repositories {
		jcenter()
	}
}

repositories {
	mavenCentral()
	jcenter()
}

dependencies {
	implementation("io.github.microutils:kotlin-logging:1.7.8")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	detekt("io.gitlab.arturbosch.detekt:detekt-formatting:1.8.0")
	detekt("io.gitlab.arturbosch.detekt:detekt-cli:1.8.0")
	testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
}

detekt {
	autoCorrect = true
	failFast = false
	config = files("detekt.yml")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
