object BuildConfig {
    val minecraftVersion: String = "1.21.8"
    val minecraftVersionRange: String = "~1.21.8"
    val yarnMappings: String = minecraftVersion + "+build.1"
    val yarnMappingsNeoforgePatchVersion: String = "1.21+build.4"

    val fabricLoaderVersion: String = "0.17.2"
    val neoforgeVersion: String = "21.8.46"
    val architectureApiVersion: String = "17.0.8"
    val enabledPlatforms: String = "fabric,neoforge"

    val modVersion: String = "1.1.0-" + minecraftVersion
    val mavenGroup: String = "com.macuguita.woodworks"
    val modId: String = "gwoodworks"
    val modName: String = "guita's Woodworks"
    val description: String = "Decorations that have some sort of chopped charm!"
    val license: String = "MIT"
    val website: String = "https://macuguita.com/"
    val source: String = "https://github.com/macuguita/woodworks"
    val issues: String = "https://github.com/macuguita/woodworks/issues"

    val fabricVersion: String = "0.133.4+" + minecraftVersion
    //val everyCompatVersion: String = "1.21-2.10.11"
    //val moonlightLibVersion: String = "1.21-2.19.5"
    val macuLibVersion: String = "1.0.5"
    val modmenuVersion: String = "15.0.0"
    val reiVersion: String = "20.0.811"

    //val naturesSpiritVersionNeoforge: String = "sUhwOqbY"
    //val naturesSpiritVersionFabric: String = "tkagouhV"
    //val terrablenderVersion: String = "4.1.0.8"
}
