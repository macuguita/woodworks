plugins {
    id("dev.architectury.loom").version("1.13-SNAPSHOT").apply(false)
    id("architectury-plugin").version("3.4-SNAPSHOT")
    id("co.uzzu.dotenv.gradle").version("4.0.0")
    id("com.gradleup.shadow").version("8.3.6").apply(false)
    id("me.modmuss50.mod-publish-plugin").version("1.0.0")
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
        exclusiveContent {
            forRepository {
                maven {
                    name = "macuguita"
                    url = uri("https://maven.macuguita.com/releases/")
                }
            }
            filter {
                includeGroup("com.macuguita")
                includeGroup("folk.sisby")
            }
        }
        exclusiveContent {
            forRepository {
                maven {
                    name = "Xander Maven"
                    url = uri("https://maven.isxander.dev/releases/")
                }
            }
            filter {
                includeGroupAndSubgroups("dev.isxander")
                includeGroupAndSubgroups("org.quiltmc.parsers")
            }
        }
    }
}

subprojects {
    apply(plugin = "dev.architectury.loom")
    apply(plugin = "me.modmuss50.mod-publish-plugin")

    val loom = project.extensions.getByType<net.fabricmc.loom.api.LoomGradleExtensionAPI>()

    the<BasePluginExtension>().archivesName.set(BuildConfig.modId)

    dependencies {
        "minecraft"("net.minecraft:minecraft:${BuildConfig.minecraftVersion}")

        "mappings"(
            loom.layered {
                officialMojangMappings()
                BuildConfig.parchmentMappings?.let { parchment("org.parchmentmc.data:parchment-${BuildConfig.minecraftVersion}:$it@zip") }
            }
        )
    }

    var modLoader = name
    val changelogText: String = rootProject.file("CHANGELOG.md").readText()

    if (project.name != "common") {
        publishMods {
            changelog = changelogText
            file.set((tasks.named("remapJar").get() as net.fabricmc.loom.task.RemapJarTask).archiveFile)
            additionalFiles.from(
                (tasks.named("remapSourcesJar").get() as net.fabricmc.loom.task.RemapSourcesJarTask).archiveFile
            )
            displayName = BuildConfig.modName + " " + BuildConfig.modVersion + "-$modLoader"
            version = BuildConfig.modVersion + "-$modLoader"
            if (BuildConfig.modVersion.contains("beta")) {
                type = BETA
            } else {
                type = STABLE
            }
            if (modLoader == "fabric") {
                modLoaders.add("fabric")
                modLoaders.add("quilt")
            } else if (modLoader == "neoforge") {
                modLoaders.add("neoforge")
            }
            dryRun = providers.environmentVariable("MODRINTH_TOKEN")
                .getOrNull() == null || providers.environmentVariable("CURSEFORGE_TOKEN").getOrNull() == null
            modrinth {
                projectId = "NTFyR6MX"
                accessToken = providers.environmentVariable("MODRINTH_TOKEN")
                for (version in BuildConfig.supportedVersions)
                    minecraftVersions.add(version)
                if (modLoader == "fabric") {
                    requires("fabric-api")
                }
                requires("macu-lib")
                optional("every-compat")
            }
            curseforge {
                projectId = "1308420"
                changelogType = "markdown"
                accessToken = providers.environmentVariable("CURSEFORGE_TOKEN")
                for (version in BuildConfig.supportedVersions)
                    minecraftVersions.add(version)
                javaVersions.add(JavaVersion.VERSION_21)
                clientRequired = true
                serverRequired = true
                projectSlug = "guitas-woodworks"
                if (modLoader == "fabric") {
                    requires("fabric-api")
                }
                requires("macu-lib")
                optional("every-compat")
            }
        }
    }

    configure<JavaPluginExtension> {
        withSourcesJar()

        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    tasks.withType<JavaCompile>().configureEach {
        options.release.set(21)
    }
}
