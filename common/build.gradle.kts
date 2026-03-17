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

    modImplementation("com.macuguita:macu_lib-fabric:${BuildConfig.macuLibVersion}+${BuildConfig.minecraftVersion}")
    modImplementation("net.mehvahdjukaar:everycomp:${BuildConfig.everyCompatVersion}")
}
