import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import kotlin.math.exp

plugins {
    id("com.github.johnrengelman.shadow")
}

version = BuildConfig.modVersion + "-forge"

architectury {
    minecraft = BuildConfig.minecraftVersion
    platformSetupLoomIde()
    forge()
}

configurations {
    create("common") {
        isCanBeResolved = true
        isCanBeConsumed = false
    }
    getByName("compileClasspath").extendsFrom(getByName("common"))
    getByName("runtimeClasspath").extendsFrom(getByName("common"))
    getByName("developmentForge").extendsFrom(getByName("common"))

    create("shadowBundle") {
        isCanBeResolved = true
        isCanBeConsumed = false
    }
}

loom {
    forge {
        mixinConfig("${BuildConfig.modId}.mixins.json")
    }
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
        name = "Forge"
        url = uri("https://files.minecraftforge.net")
    }
}

dependencies {
    forge("net.minecraftforge:forge:${BuildConfig.minecraftVersion}-${BuildConfig.forgeVersion}")

    compileOnly("io.github.llamalad7:mixinextras-common:0.4.1")
    annotationProcessor("io.github.llamalad7:mixinextras-common:0.4.1")
    implementation("io.github.llamalad7:mixinextras-forge:0.4.1")
    include("io.github.llamalad7:mixinextras-forge:0.4.1")

    modCompileOnly("dev.architectury:architectury-forge:${BuildConfig.architectureApiVersion}")

    "common"(project(":common", "namedElements")) {
        isTransitive = false
    }
    "shadowBundle"(project(":common", "transformProductionForge"))

    //modImplementation "com.macuguita.lib:macu_lib-neoforge:${project.macu_lib_version}-${project.minecraft_version}"

    // Modrinth
    modImplementation("maven.modrinth:macu-lib:${BuildConfig.macuLibVersion}-${BuildConfig.minecraftVersion}-forge")
    modImplementation("maven.modrinth:every-compat:${BuildConfig.everyCompatVersion}-forge")
    val isMyPc = System.getenv("macuguita")?.equals("true", ignoreCase = true) == true
    if (isMyPc) {
        modImplementation("net.mehvahdjukaar:moonlight-forge:${BuildConfig.moonlightLibVersion}")
    } else {
        modImplementation("maven.modrinth:moonlight:${BuildConfig.moonlightLibVersion}-forge")
    }
    modRuntimeOnly("maven.modrinth:natures-spirit:${BuildConfig.naturesSpiritVersionForge}")
    // Other Mavens
    modImplementation("com.github.glitchfiend:TerraBlender-forge:${BuildConfig.minecraftVersion}-${BuildConfig.terrablenderVersion}")

    modApi("me.shedaniel:RoughlyEnoughItems-forge:${BuildConfig.reiVersion}")
}

tasks.processResources {
    filesMatching("META-INF/mods.toml") {
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
    filesMatching("pack.mcmeta") {
        expand(
            "modName" to BuildConfig.modName,
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
