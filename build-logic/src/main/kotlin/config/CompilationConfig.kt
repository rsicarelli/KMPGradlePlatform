package config

import org.gradle.api.JavaVersion

data class CompilationConfig(
    val javaVersion: JavaVersion = JavaVersion.VERSION_11,
    val jvmTarget: String = "11",
    val allWarningsAsErrors: Boolean = false,
    val featureOptInSequence: Sequence<FeatureOptIn> = sequenceOf(),
) {

    val extraFreeCompilerArgs: List<String>
        get() = featureOptInSequence.map { "-opt-in=${it.flag}" }.toList()

    enum class FeatureOptIn(val flag: String) {

        KotlinRequiresOptIn(flag = "kotlin.RequiresOptIn"),
        KotlinExperimental(flag = "kotlin.Experimental"),
        KotlinxCoroutinesExperimentalCoroutinesApi(flag = "kotlinx.coroutines.ExperimentalCoroutinesApi"),
        KotlinxCoroutinesFlowPreview(flag = "kotlinx.coroutines.FlowPreview")
    }
}


