import com.matthewprenger.cursegradle.CurseArtifact
import com.matthewprenger.cursegradle.CurseProject
import com.matthewprenger.cursegradle.CurseRelation
import com.matthewprenger.cursegradle.Options

plugins {
    id("dev.architectury.loom") version "1.10-SNAPSHOT" apply false
    id("architectury-plugin") version "3.4-SNAPSHOT"
    id("com.github.johnrengelman.shadow") version "8.1.1" apply false
    id("com.modrinth.minotaur") version "2.+"
    id("com.matthewprenger.cursegradle") version "1.4.0"
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
        maven {
            name = "Ladysnake Mods"
            url = uri("https://maven.ladysnake.org/releases")
        }
    }
}

subprojects {
    apply(plugin = "dev.architectury.loom")
    apply(plugin = "com.modrinth.minotaur")
    apply(plugin = "com.matthewprenger.cursegradle")

    the<BasePluginExtension>().archivesName.set(BuildConfig.modId)

    dependencies {
        "minecraft"("net.minecraft:minecraft:${BuildConfig.minecraftVersion}")

        "mappings"("net.fabricmc:yarn:${BuildConfig.yarnMappings}:v2")
    }

    var modLoader = name
    val changelogText: String = File("CHANGELOG.md").readText()

    modrinth {
        token.set(System.getenv("MODRINTH_TOKEN"))
        projectId.set("NTFyR6MX")
        versionNumber.set(BuildConfig.modVersion + "-" + modLoader)
        versionName.set("guita's Woodworks " + versionNumber.get())
        versionType.set("release")
        uploadFile.set(tasks.named("remapJar").get())
        additionalFiles.add(tasks.named("remapSourcesJar").get())
        changelog.set(changelogText)
        gameVersions.addAll(BuildConfig.minecraftVersion)
        if (modLoader == "fabric") {
            loaders.addAll("fabric", "quilt")
            dependencies {
                required.project("fabric-api")
            }
        } else if (modLoader == "forge") {
            loaders.add("forge")
        }
        required.project("macu-lib")
    }

    curseforge {
        options(closureOf<Options> {
            forgeGradleIntegration = false
        })

        project(closureOf<CurseProject> {
            apiKey = System.getenv("CURSEFORGE_TOKEN")
            id = "1308420"
            releaseType = "release"
            addGameVersion(BuildConfig.minecraftVersion)
            if (modLoader == "fabric") {
                addGameVersion("Fabric")
                addGameVersion("Quilt")
            } else if (modLoader == "forge") {
                addGameVersion("Forge")
            }
            addGameVersion("Java 17")

            changelogType = "markdown"
            changelog = changelogText

            mainArtifact(tasks.named("remapJar").get(), closureOf<CurseArtifact> {
                displayName = "${BuildConfig.modId}-${BuildConfig.modVersion}-${modLoader}"
            })

            addArtifact(tasks.named("remapSourcesJar").get())

            relations(closureOf<CurseRelation> {
                if (modLoader == "fabric") {
                    requiredDependency("fabric-api")
                }
                requiredDependency("macu-lib")
            })
        })
    }

    configure<JavaPluginExtension> {
        withSourcesJar()

        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    tasks.withType<JavaCompile>().configureEach {
        options.release.set(17)
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
