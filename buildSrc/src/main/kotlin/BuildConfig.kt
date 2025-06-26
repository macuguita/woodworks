object BuildConfig {
    val minecraftVersion: String = "1.21.1"
    val minecraftVersionRange: String = "~1.21.1"
    val yarnMappings: String = minecraftVersion + "+build.3"
    val yarnMappingsNeoforgePatchVersion: String = "1.21+build.4"

    val fabricLoaderVersion: String = "0.16.14"
    val neoforgeVersion: String = "21.1.185"
    val architectureApiVersion: String = "13.0.8"
    val enabledPlatforms: String = "fabric,neoforge"

    val modVersion: String = "0.0.1-" + minecraftVersion
    val mavenGroup: String = "com.macuguita.woodworks"
    val modId: String = "gwoodworks"
    val modName: String = "guita's Woodworks"
    val description: String = "Mod that adds all kinds of wood blocks! "
    val license: String = "MIT"
    val website: String = "https://macuguita.com/"
    val source: String = "https://github.com/macuguita/woodworks"
    val issues: String = "https://github.com/macuguita/woodworks/issues"

    val fabricVersion: String = "0.116.3+" + minecraftVersion
    val macuLibVersion: String = "1.0.5"
    val modmenuVersion: String = "11.0.3"
    val reiVersion: String = "16.0.799"
}
