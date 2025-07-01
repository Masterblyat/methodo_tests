import org.gradle.testing.jacoco.tasks.JacocoReport

plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.5.3"
	id("io.spring.dependency-management") version "1.1.7"
	id("info.solidsoft.pitest") version "1.19.0-rc.1"
	id("jacoco")
}

group = "com.example"
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
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
	testImplementation("io.kotest:kotest-assertions-core:5.9.1")
	testImplementation("io.kotest:kotest-property:5.9.1")
	testImplementation("io.mockk:mockk:1.14.4")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
	finalizedBy("jacocoTestReport")
}

tasks.named<JacocoReport>("jacocoTestReport") {
	dependsOn("test")
	reports {
		xml.required.set(true)
		html.required.set(true)
		csv.required.set(false)
	}
}

configure<JacocoPluginExtension> {
	toolVersion = "0.8.13"
}

pitest {
	testPlugin = "junit5"
	avoidCallsTo = setOf("kotlin.jvm.internal")
	threads = 4
	outputFormats = setOf("XML", "HTML")
	timestampedReports = false
	targetClasses = setOf("com.example.demo.*")
	targetTests = setOf("com.example.demo.*")
	excludedClasses = setOf("com.example.demo.DemoApplication*")
}
