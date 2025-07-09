architectury {
    common(BuildConfig.enabledPlatforms.split(','))
}

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

    //modImplementation "com.macuguita.lib:macu_lib-common:${project.macu_lib_version}-${project.minecraft_version}"
//    modImplementation("net.mehvahdjukaar:moonlight:${BuildConfig.moonlightLibVersion}")

        // Modrinth
    modImplementation("maven.modrinth:macu-lib:${BuildConfig.macuLibVersion}-1.21.4-fabric")
    modImplementation("maven.modrinth:every-compat:${BuildConfig.everyCompatVersion}-fabric")
    modImplementation("maven.modrinth:moonlight:${BuildConfig.moonlightLibVersion}-fabric")
}
