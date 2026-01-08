plugins {
    id("com.gradleup.shadow")
}

version = BuildConfig.modVersion + "-neoforge"

architectury {
    minecraft = BuildConfig.minecraftVersion
    platformSetupLoomIde()
    neoForge()
}

configurations {
    create("common") {
        isCanBeResolved = true
        isCanBeConsumed = false
    }
    getByName("compileClasspath").extendsFrom(getByName("common"))
    getByName("runtimeClasspath").extendsFrom(getByName("common"))
    getByName("developmentNeoForge").extendsFrom(getByName("common"))

    create("shadowBundle") {
        isCanBeResolved = true
        isCanBeConsumed = false
    }
}

loom {
    runs {
        configureEach {
            if (name == "client") {
                programArgs.add("--username=Ladybrine")
                programArgs.add("--uuid=5d66606c-949c-47ce-ba4c-a1b9339ba3c8")
            }
        }
    }
}

repositories {
    maven {
        name = "NeoForged"
        url = uri("https://maven.neoforged.net/releases")
    }
}

dependencies {
    neoForge("net.neoforged:neoforge:${BuildConfig.neoforgeVersion}")

    modCompileOnly("dev.architectury:architectury-neoforge:${BuildConfig.architectureApiVersion}")

    "common"(project(":common", "namedElements")) {
        isTransitive = false
    }
    "shadowBundle"(project(":common", "transformProductionNeoForge"))

    modImplementation("com.macuguita:macu_lib-neoforge:${BuildConfig.macuLibVersion}+${BuildConfig.minecraftVersion}")

    // Modrinth
    modImplementation("maven.modrinth:every-compat:${BuildConfig.everyCompatVersion}-neoforge")

    val isMyPc = System.getenv("macuguita")?.equals("true", ignoreCase = true) == true
    if (isMyPc) {
        modImplementation("net.mehvahdjukaar:moonlight-neoforge:${BuildConfig.moonlightLibVersion}")
    } else {
        modImplementation("maven.modrinth:moonlight:${BuildConfig.moonlightLibVersion}-neoforge")
    }

    modRuntimeOnly("maven.modrinth:natures-spirit:${BuildConfig.naturesSpiritVersionNeoforge}")
    modImplementation("com.github.glitchfiend:TerraBlender-neoforge:${BuildConfig.minecraftVersion}-${BuildConfig.terrablenderVersion}")
    modApi("me.shedaniel:RoughlyEnoughItems-neoforge:${BuildConfig.reiVersion}")
}

tasks.processResources {
    filesMatching("META-INF/neoforge.mods.toml") {
        expand(
            "version" to BuildConfig.modVersion,
            "modId" to BuildConfig.modId,
            "modName" to BuildConfig.modName,
            "description" to BuildConfig.description,
            "license" to BuildConfig.license,
            "website" to BuildConfig.website,
            "source" to BuildConfig.source,
            "issues" to BuildConfig.issues,
            "loaderVersion" to BuildConfig.fabricLoaderVersion,
            "minecraftVersion" to BuildConfig.minecraftVersion,
            "minecraftVersionRange" to BuildConfig.minecraftVersionRange,
            "macuLibVersion" to BuildConfig.macuLibVersion
        )
    }
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>().configureEach {
    configurations = listOf(project.configurations.getByName("shadowBundle"))
    archiveClassifier.set("dev-shadow")
}

tasks.remapJar {
    val shadowJar = tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar")
    inputs.file(shadowJar.flatMap { it.archiveFile })
    doFirst {
        println("Remapping shadow jar: ${shadowJar.get().archiveFile.get().asFile}")
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = BuildConfig.modId
            artifactId = BuildConfig.modId + "-neoforge"
            version = BuildConfig.modVersion
            from(components["java"])
        }
    }
    repositories {
        mavenLocal()
        maven {
            name = "macuguita"
            url = uri("https://maven.macuguita.com/releases")

            credentials {
                username = env.MAVEN_USERNAME.orNull()
                password = env.MAVEN_KEY.orNull()
            }
        }
    }
}
