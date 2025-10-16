package pt.isel.pdm.pokemonoftheday.ui


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import pt.isel.pdm.pokemonoftheday.ui.about.AboutActivity

abstract class BaseActivity() : ComponentActivity() {

    protected val activityTag: String = this.javaClass.simpleName

    inline fun <reified T> navigate(
        noinline apply: ((Intent) -> Unit)? = null
    ) {
        val intent = Intent(this, T::class.java)

        if (apply != null)
            apply(intent)

        this.startActivity(intent)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(activityTag, "onCreate")
        super.onCreate(savedInstanceState)
    }


    override fun onStart() {
        Log.d(activityTag, "onStart")
        super.onStart()
    }


    override fun onPause() {
        Log.d(activityTag, "onPause")
        super.onPause()
    }


    override fun onResume() {
        Log.d(activityTag, "onResume")
        super.onResume()
    }

    override fun onStop() {
        Log.d(activityTag, "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(activityTag, "onDestroy")
        super.onDestroy()
    }
}
