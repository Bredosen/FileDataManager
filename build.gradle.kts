plugins {
    id("java")
}

group = "bredo.cmd.mc.filedatamanager"
version = "1.0-SNAPSHOT"

repositories {
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")

    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://oss.sonatype.org/content/repositories/central")
    mavenLocal()
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.20.2-R0.1-SNAPSHOT")
    compileOnly("org.spigotmc:spigot:1.20.2-R0.1-SNAPSHOT")
}