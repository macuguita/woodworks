import net.fabricmc.loom.api.LoomGradleExtensionAPI

plugins {
    id("dev.architectury.loom") version "1.10-SNAPSHOT" apply false
    id("architectury-plugin") version "3.4-SNAPSHOT"
    id("com.github.johnrengelman.shadow") version "8.1.1" apply false
}

architectury {
    minecraft = BuildConfig.minecraftVersion
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "architectury-plugin")
    apply(plugin = "maven-publish")

    group = BuildConfig.mavenGroup

    repositories {
        mavenCentral()
        mavenLocal()
        flatDir {
            dirs("mods")
        }
        maven {
            name = "Modrinth maven"
            url = uri("https://api.modrinth.com/maven")
        }
        maven {
            name = "Terraformers MC"
            url = uri("https://maven.terraformersmc.com/releases/")
        }
        maven {
            name = "Shedaniel's maven"
            url = uri("https://maven.shedaniel.me/")
        }
        maven {
            name = "Forge"
            url = uri("https://maven.minecraftforge.net/")
        }
        exclusiveContent {
            forRepository {
                maven {
                    name = "Curse maven"
                    url = uri("https://cursemaven.com")
                }
            }
            filter {
                includeGroup("curse.maven")
            }
        }
    }
}

subprojects {
    apply(plugin = "dev.architectury.loom")

    val loom = project.extensions.getByType<LoomGradleExtensionAPI>()

    the<BasePluginExtension>().archivesName.set(BuildConfig.modId)

    dependencies {
        "minecraft"("net.minecraft:minecraft:${BuildConfig.minecraftVersion}")

        "mappings"(
            loom.layered {
                mappings("net.fabricmc:yarn:${BuildConfig.yarnMappings}:v2")
                mappings("dev.architectury:yarn-mappings-patch-neoforge:${BuildConfig.yarnMappingsNeoforgePatchVersion}")
            }
        )
    }

    configure<JavaPluginExtension> {
        withSourcesJar()

        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    tasks.withType<JavaCompile>().configureEach {
        options.release.set(21)
    }

    configure<PublishingExtension> {
        publications {
            create<MavenPublication>("mavenJava") {
                artifactId = the<BasePluginExtension>().archivesName.get()
                from(components.getByName("java"))
            }
        }

        repositories {}
    }
}
