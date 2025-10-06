package pdm.demos.demoshostapplication.usertask

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kotlinx.parcelize.Parcelize
import pdm.demos.demoshostapplication.ui.theme.DemosHostApplicationTheme

/**
 * Base class for the activities used in this demo. It contains common logic to log lifecycle events
 * and display the trace of activity launches.
 */
sealed class UserTaskDemoActivity : ComponentActivity() {

    class A : UserTaskDemoActivity()
    class B : UserTaskDemoActivity()

    companion object {
        fun navigate(ctx: ComponentActivity) {
            val intent = Intent(ctx, A::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
            }
            ctx.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v(
            "UserTaskDemo",
            "${javaClass.simpleName}.onCreate() ; hashCode = ${hashCode()})"
        )
        handleIncomingIntent(intent, reused = false)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.v(
            "UserTaskDemo",
            "${javaClass.simpleName}.onNewIntent() ; hashCode = ${hashCode()})"
        )
        this.intent = intent
        handleIncomingIntent(intent, reused = true)
    }

    override fun onStart() {
        super.onStart()
        Log.v(
            "UserTaskDemo",
            "${javaClass.simpleName}.onStart() ; hashCode = ${hashCode()})"
        )
    }

    override fun onStop() {
        super.onStop()
        Log.v(
            "UserTaskDemo",
            "${javaClass.simpleName}.onStop() ; hashCode = ${hashCode()})"
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(
            "UserTaskDemo",
            "${javaClass.simpleName}.onDestroy() ; hashCode = ${hashCode()})"
        )
    }

    private fun handleIncomingIntent(intent: Intent, reused: Boolean) {
        val trace = intent.getTrace() + TraceNode(
            className = javaClass.simpleName,
            instanceHash = System.identityHashCode(this),
            launchNote = getActivityLaunchNote(flags = intent.flags, reused)
        )

        setContent {
            DemosHostApplicationTheme {
                UserTaskScreen(
                    trace = trace,
                    onStartAIntent = { handleNavigation(trace, flags = it, target = A::class.java) },
                    onStartBIntent = { handleNavigation(trace, flags = it, target = B::class.java) },
                    onBackIntent = { finish() }
                )
            }
        }
    }

    private fun handleNavigation(trace: List<TraceNode>, flags: Int, target: Class<out UserTaskDemoActivity>) {
        val newIntent = Intent(this, target).apply {
            this.flags = flags
            putParcelableArrayListExtra(EXTRA_TRACE, ArrayList(trace))
        }
        startActivity(newIntent)
    }
}

/**
 * A node in the trace of activity launches.
 * @param className The simple name of the activity class being launched.
 * @param instanceHash The identity hash code of the activity instance.
 * @param launchNote A note about how the activity was launched.
 */
@Parcelize
data class TraceNode(val className: String, val instanceHash: Int, val launchNote: String) :
    Parcelable

/**
 * The key used to store the trace in the Intent extras.
 */
const val EXTRA_TRACE = "pdm.demos.demoshostapplication.usertask.EXTRA_TRACE"

/**
 * Helper function used to get the trace information from the Intent.
 */
private fun Intent.getTrace(): ArrayList<TraceNode> {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableArrayListExtra(EXTRA_TRACE, TraceNode::class.java) ?: arrayListOf()
    } else {
        @Suppress("DEPRECATION")
        getParcelableArrayListExtra(EXTRA_TRACE) ?: arrayListOf()
    }
}

/**
 * Helper function used to get a note about how the activity was launched based on the Intent flags.
 */
private fun getActivityLaunchNote(flags: Int, reused: Boolean): String {
    val parts =
        buildList {
            if ((flags and Intent.FLAG_ACTIVITY_SINGLE_TOP) != 0) add("SINGLE_TOP")
            if ((flags and Intent.FLAG_ACTIVITY_CLEAR_TOP) != 0) add("CLEAR_TOP")
            if ((flags and Intent.FLAG_ACTIVITY_REORDER_TO_FRONT) != 0) add("REORDER_TO_FRONT")
        }
            .ifEmpty { listOf("standard") }
            .joinToString(separator = " | ")

    return if (reused) "$parts (REUSED via onNewIntent)" else "$parts (NEW via onCreate)"
}
