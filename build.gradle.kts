plugins {
    application
}

group = "org.kanliam.bot"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
}

application{
    mainClassName = "org.kanliam.bot.Main"
}

dependencies {
    testImplementation("junit", "junit", "4.12")
    implementation("net.dv8tion:JDA:4.1.1_101")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}