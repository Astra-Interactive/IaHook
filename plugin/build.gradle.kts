import ru.astrainteractive.gradleplugin.setupSpigotProcessor
import ru.astrainteractive.gradleplugin.setupSpigotShadow

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

dependencies {
    // Kotlin
    implementation(libs.bundles.kotlin)
    // AstraLibs
    implementation(libs.minecraft.astralibs.ktxcore)
    implementation(libs.minecraft.astralibs.orm)
    implementation(libs.klibs.kdi)
    implementation(libs.minecraft.astralibs.spigot.gui)
    implementation(libs.minecraft.astralibs.spigot.core)
    // Spigot dependencies
    compileOnly(libs.minecraft.paper.api)
    compileOnly("com.github.LoneDev6:api-itemsadder:3.5.0b")
    compileOnly(libs.minecraft.vaultapi)
    implementation(libs.minecraft.bstats)
    // Test
    testImplementation(platform(libs.tests.junit.bom))
    testImplementation(libs.bundles.testing.libs)
    testImplementation(libs.bundles.testing.kotlin)
    testImplementation(libs.minecraft.mockbukkit)
}

val localPluginsFolder = File("D:\\Minecraft Servers\\Servers\\esmp-configuration\\anarchy\\plugins")
if (localPluginsFolder.exists()) setupSpigotShadow(localPluginsFolder) else setupSpigotShadow()
setupSpigotProcessor()
