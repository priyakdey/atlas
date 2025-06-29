plugins {
    java
    application
}

dependencies {
    implementation(Deps.GUICE)
    implementation(Deps.GUICE_MULTIBINDINGS)

    runtimeOnly(Deps.LOG4J2)
}

application {
    mainClass.set("com.priyakdey.atlast.konfig.KonfigServerApplication")
}