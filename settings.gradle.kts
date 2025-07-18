pluginManagement {
    repositories {
        maven {
            name = "Fabric"
            url = uri("https://maven.fabricmc.net/")
        }
        maven {
            name = "Architectury"
            url = uri("https://maven.architectury.dev/")
        }
        maven {
            name = "Forge"
            url = uri("https://files.minecraftforge.net/maven/")
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "woodworks"

include("common")
include("fabric")
include("neoforge")