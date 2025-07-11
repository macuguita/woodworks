import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.github.johnrengelman.shadow")
}

version = BuildConfig.modVersion + "-fabric"

architectury {
    minecraft = BuildConfig.minecraftVersion
    platformSetupLoomIde()
    fabric()
}

configurations {
    create("common") {
        isCanBeResolved = true
        isCanBeConsumed = false
    }
    getByName("compileClasspath").extendsFrom(getByName("common"))
    getByName("runtimeClasspath").extendsFrom(getByName("common"))
    getByName("developmentFabric").extendsFrom(getByName("common"))

    create("shadowBundle") {
        isCanBeResolved = true
        isCanBeConsumed = false
    }
}

loom {
    runs {
        register("datagen") {
            client()
            name = "Data Generation"

            vmArg("-Dfabric-api.datagen")
            vmArg("-Dfabric-api.datagen.modid=${BuildConfig.modId}")
            vmArg("-Dfabric-api.datagen.output-dir=${project(":common").file("src/main/generated")}")
            runDir("build/datagen")

            ideConfigGenerated(true)
        }
        configureEach {
            if (name == "client") {
                programArgs.add("--username=Ladybrine")
                programArgs.add("--uuid=5d66606c-949c-47ce-ba4c-a1b9339ba3c8")
            }
        }
    }
}

dependencies {
    modImplementation("net.fabricmc:fabric-loader:${BuildConfig.fabricLoaderVersion}")

    modImplementation("net.fabricmc.fabric-api:fabric-api:${BuildConfig.fabricVersion}")

    modCompileOnly("dev.architectury:architectury-fabric:${BuildConfig.architectureApiVersion}")

    "common"(project(":common", "namedElements")) {
        isTransitive = false
    }
    "shadowBundle"(project(":common", "transformProductionFabric"))

    //modImplementation "com.macuguita.lib:macu_lib-neoforge:${project.macu_lib_version}-${project.minecraft_version}"

    // Modrinth
    modImplementation("maven.modrinth:macu-lib:${BuildConfig.macuLibVersion}-${BuildConfig.minecraftVersion}-fabric")
    modImplementation("maven.modrinth:every-compat:${BuildConfig.everyCompatVersion}-fabric")
    val isMyPc = System.getenv("macuguita")?.equals("true", ignoreCase = true) == true
    if (isMyPc) {
        modImplementation("net.mehvahdjukaar:moonlight-fabric:${BuildConfig.moonlightLibVersion}")
    } else {
        modImplementation("maven.modrinth:moonlight:${BuildConfig.moonlightLibVersion}-fabric")
    }
    modRuntimeOnly("maven.modrinth:natures-spirit:${BuildConfig.naturesSpiritVersionFabric}")

    // Other Mavens
    modImplementation("com.github.glitchfiend:TerraBlender-fabric:${BuildConfig.minecraftVersion}-${BuildConfig.terrablenderVersion}")

    modApi("com.terraformersmc:modmenu:${BuildConfig.modmenuVersion}")
    modApi("me.shedaniel:RoughlyEnoughItems-fabric:${BuildConfig.reiVersion}")
}

tasks.processResources {
    filesMatching("fabric.mod.json") {
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
