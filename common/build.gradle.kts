architectury {
    common(BuildConfig.enabledPlatforms.split(','))
}

version = BuildConfig.modVersion + "-common"

sourceSets {
    main {
        resources.srcDir("src/main/generated")
        // optional: exclude the datagen cache files from the mod jar
        resources.exclude(".cache")
    }
}

dependencies {
    // We depend on Fabric Loader here to use the Fabric @Environment annotations,
    // which get remapped to the correct annotations on each platform.
    // Do NOT use other classes from Fabric Loader.
    modImplementation("net.fabricmc:fabric-loader:${BuildConfig.fabricLoaderVersion}")

    // Architectury API. This is optional, and you can comment it out if you don't need it.
    modCompileOnly("dev.architectury:architectury:${BuildConfig.architectureApiVersion}")

    modImplementation("com.macuguita:macu_lib-fabric:${BuildConfig.macuLibVersion}+${BuildConfig.minecraftVersion}")

    // Modrinth
    modImplementation("maven.modrinth:every-compat:${BuildConfig.everyCompatVersion}-fabric")
    val isMyPc = System.getenv("macuguita")?.equals("true", ignoreCase = true) == true
    if (isMyPc) {
        modImplementation("net.mehvahdjukaar:moonlight:${BuildConfig.moonlightLibVersion}")
    } else {
        modImplementation("maven.modrinth:moonlight:${BuildConfig.moonlightLibVersion}-fabric")
    }
}
