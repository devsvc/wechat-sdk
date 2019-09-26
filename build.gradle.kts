import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }

    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.3.50"))
    }
}

plugins {
    kotlin("jvm") version "1.3.50"
    signing
    `maven-publish`
}

group = "pro.devsvc"
version = "1.0-SNAPSHOT"

tasks.withType<KotlinCompile> {
    kotlinOptions.suppressWarnings = true
    kotlinOptions.jvmTarget = "1.8"
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    api(kotlin("stdlib-jdk8"))
    api("org.apache.httpcomponents:httpclient:4.5.10")

    api(kotlin("reflect"))
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}

tasks.register<Jar>("sourcesJar") {
    from(sourceSets.main.get().allJava)
    archiveClassifier.set("sources")
}

tasks.register<Jar>("javadocJar") {
    from(tasks.javadoc)
    archiveClassifier.set("javadoc")
}


publishing {
    publications {
        repositories {
            maven {
                println(project.)
                val releasesRepoUrl = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2")
                val snapshotsRepoUrl = uri("https://oss.sonatype.org/content/repositories/snapshots")
                url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
                credentials {
                }
            }
        }

        create<MavenPublication>("maven") {
            groupId = "pro.devsvc"
            artifactId = "wechat-sdk"
            version = "1.0"

            from(components["java"])
        }

        publications {
            create<MavenPublication>("mavenJava") {
                artifactId = "wechat-sdk"
                from(components["java"])
                artifact(tasks["sourcesJar"])
                artifact(tasks["javadocJar"])
                versionMapping {
                    usage("java-api") {
                        fromResolutionOf("runtimeClasspath")
                    }
                    usage("java-runtime") {
                        fromResolutionResult()
                    }
                }
                pom {
                    name.set("Wechat SDK")
                    description.set("An opensource wechat apis sdk for kotlin")
                    url.set("https://github.com/devsvc/wechat-sdk")
                    licenses {
                        license {
                            name.set("MIT License")
                            url.set("https://opensource.org/licenses/MIT")
                        }
                    }
                    developers {
                        developer {
                            id.set("devsvc")
                            name.set("devsvc")
                            email.set("open.dev@outlook.com")
                        }
                    }
                    scm {
                        connection.set("scm:git:https://github.com/devsvc/wechat-sdk.git")
                        developerConnection.set("scm:git:https://github.com/devsvc/wechat-sdk.git")
                        url.set("https://github.com/devsvc/wechat-sdk")
                    }
                }
            }
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}
