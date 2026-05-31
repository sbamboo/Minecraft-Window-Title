rootProject.name = "Minecraft-Window-Title"

pluginManagement {
	repositories {
		gradlePluginPortal()
		maven(url = "https://maven.neoforged.net/releases") { name = "NeoForge" }
		maven(url = "https://maven.fabricmc.net/") { name = "Fabric" }
	}
	
	plugins {
		if (settings.extra.has("neoModDevVersion")) {
			id("net.neoforged.moddev") version settings.extra["neoModDevVersion"] as String
		}
		
		if (settings.extra.has("loomVersion")) {
			id("net.fabricmc.fabric-loom") version "${settings.extra["loomVersion"]}-SNAPSHOT"
		}
	}
}

if (settings.extra.has("neoForgeVersion")) {
	include("NeoForge")
}

if (settings.extra.has("fabricVersion")) {
	include("Fabric")
}
