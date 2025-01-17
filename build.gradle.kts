plugins {
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
    "gson" to "2.8.9",
    "telegrambots-longpolling" to "8.0.0",
    "telegrambots-client" to "8.0.0",
    "junit-bom" to "5.10.0",
    "junit-jupiter" to "",
    "assertj-core" to "3.6.1",
    "mockito-core" to "5.14.2"
)

dependencies {
    implementation("org.projectlombok:lombok:${versions["lombok"]}")
    annotationProcessor("org.projectlombok:lombok:${versions["lombok"]}")

    implementation("org.slf4j:slf4j-api:${versions["slf4j-api"]}")
    implementation("ch.qos.logback:logback-classic:${versions["logback-classic"]}")
    implementation("org.javatuples:javatuples:${versions["javatuples"]}")
    implementation("com.google.code.gson:gson:${versions["gson"]}")
    implementation("org.telegram:telegrambots-longpolling:${versions["telegrambots-longpolling"]}")
    implementation("org.telegram:telegrambots-client:${versions["telegrambots-client"]}")

    testImplementation(platform("org.junit:junit-bom:${versions["junit-bom"]}"))
    testImplementation("org.junit.jupiter:junit-jupiter:${versions["junit-jupiter"]}")
    testImplementation("org.assertj:assertj-core:${versions["assertj-core"]}")
    testImplementation("org.mockito:mockito-core:${versions["mockito-core"]}")
}

tasks.test {
    useJUnitPlatform()
}