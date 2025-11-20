package pt.isel.pdm.pokemonoftheday

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner


class DemoPokemonAppInstrumentationTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, DemoPokemonTestApplication::class.java.name, context)
    }
}

