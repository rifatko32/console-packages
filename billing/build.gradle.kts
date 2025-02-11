plugins {
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
    id("java")
}

group = "ru.hofftech"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
val versions = mapOf(
    "lombok" to "1.18.30",
    "slf4j-api" to "2.0.9",
    "logback-classic" to "1.4.12",
    "javatuples" to "1.2",
    "gson" to "2.11.0",
    "junit-bom" to "5.10.0",
    "junit-jupiter" to "",
    "assertj-core" to "3.6.1",
    "mockito-core" to "5.14.2",
    "mapstruct" to "1.6.3",
    "lombokMapstructBindingVersion" to "0.2.0",
    "webmvc" to "2.8.3",
    "cloud" to "2024.0.0",
    "caffeine" to "3.2.0",
    "test-containers" to "1.17.6",
    "test-containers-boot" to "3.4.1",
    "postgres-test-container" to "1.17.6"
)


dependencies {
    annotationProcessor("org.mapstruct:mapstruct-processor:${versions["mapstruct"]}")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:${versions["lombokMapstructBindingVersion"]}")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.projectlombok:lombok:${versions["lombok"]}")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.mapstruct:mapstruct:${versions["mapstruct"]}")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${versions["webmvc"]}")

    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")
    implementation(platform("org.springframework.cloud:spring-cloud-dependencies:${versions["cloud"]}"))
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-starter-stream-kafka")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-database-postgresql")

    annotationProcessor("org.projectlombok:lombok:${versions["lombok"]}")

    implementation("org.slf4j:slf4j-api:${versions["slf4j-api"]}")
    implementation("ch.qos.logback:logback-classic:${versions["logback-classic"]}")
    implementation("org.javatuples:javatuples:${versions["javatuples"]}")
    implementation("com.google.code.gson:gson:${versions["gson"]}")

    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("com.github.ben-manes.caffeine:caffeine:${versions["caffeine"]}")

    runtimeOnly ("org.postgresql:postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(platform("org.junit:junit-bom:${versions["junit-bom"]}"))
    testImplementation("org.junit.jupiter:junit-jupiter:${versions["junit-jupiter"]}")
    testImplementation("org.assertj:assertj-core:${versions["assertj-core"]}")
    testImplementation("org.mockito:mockito-core:${versions["mockito-core"]}")
    testImplementation("org.testcontainers:postgresql:${versions["postgres-test-container"]}")
    testImplementation("org.testcontainers:junit-jupiter:${versions["test-containers"]}")
    testImplementation("org.springframework.boot:spring-boot-testcontainers:${versions["test-containers-boot"]}")
    testImplementation("org.testcontainers:kafka")
}

tasks.test {
    useJUnitPlatform()
}