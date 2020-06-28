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
    implementation("com.sedmelluq:lavaplayer:1.3.50")
    implementation("commons-validator:commons-validator:1.6")
    implementation("com.google.android:android:4.1.1.4")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}