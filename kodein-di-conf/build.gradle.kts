plugins {
    id("org.kodein.library.mpp")
}

kodein {
    kotlin {

        common {
            main.dependencies {
                api(project(":kodein-di-core"))
            }

            test.dependencies {
                implementation(project(":test-utils"))
                implementation(project(":kodein-di-erased"))
            }
        }

        add(kodeinTargets.jvm.jvm) {
            target.setCompileClasspath()
        }

        add(kodeinTargets.js.js)

        add(kodeinTargets.native.all)

    }
}

kodeinUpload {
    name = "Kodein-DI-Conf"
    description = "KODEIN Dependency Injection that can be configured / mutated"
}
