package pdm.demos.demoshostapplication.usertask

import android.util.Log
import androidx.lifecycle.ViewModel

class UserTaskDemoViewModel : ViewModel() {

    init {
        Log.v(
            "UserTaskDemo",
            "${javaClass.simpleName}.init() ; hashCode = ${hashCode()})"
        )
    }

    fun doSomething() {
        Log.v(
            "UserTaskDemo",
            "doSomething() in ${javaClass.simpleName} ; hashCode = ${hashCode()})"
        )

    }
}