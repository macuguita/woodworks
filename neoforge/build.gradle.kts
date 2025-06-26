import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.github.johnrengelman.shadow")
}

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

    //modImplementation "com.macuguita.lib:macu_lib-neoforge:${project.macu_lib_version}-${project.minecraft_version}"
    modImplementation("maven.modrinth:macu-lib:${BuildConfig.macuLibVersion}-${BuildConfig.minecraftVersion}-neoforge")
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

tasks.withType<ShadowJar>().configureEach {
    configurations = listOf(project.configurations.getByName("shadowBundle"))
    archiveClassifier.set("dev-shadow")
}

tasks.remapJar {
    inputFile.set(tasks.shadowJar.flatMap { it.archiveFile })
}