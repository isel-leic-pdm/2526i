package pdm.demos.demoshostapplication

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

/**
 * The Instrumentation Test Runner for the Demos Host Application.
 * It uses [DemosHostTestApplication] as the Application class during tests. This allows for
 * injecting test-specific dependencies.
 */
@Suppress("unused")
class DemosHostInstrumentationTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, DemosHostTestApplication::class.java.name, context)
    }
}