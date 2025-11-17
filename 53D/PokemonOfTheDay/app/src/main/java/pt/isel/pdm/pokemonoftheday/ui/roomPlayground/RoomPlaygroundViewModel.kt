package pt.isel.pdm.pokemonoftheday.ui.roomPlayground

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import pt.isel.pdm.pokemonoftheday.domain.database.AppDatabase
import pt.isel.pdm.pokemonoftheday.domain.database.entities.User

class RoomPlaygroundViewModel(
    private val appDatabase: AppDatabase //never do this
) : ViewModel() {


    val users = appDatabase.userDao().getAllUsers()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )


    fun createUser() {
        viewModelScope.launch {

            appDatabase.userDao().insert(
                User(name = "User ${users.value.size}", age = users.value.size)
            )
        }
    }

    fun deleteUser(u: User) {
        viewModelScope.launch {
            appDatabase.userDao().delete(u)
        }
    }
}