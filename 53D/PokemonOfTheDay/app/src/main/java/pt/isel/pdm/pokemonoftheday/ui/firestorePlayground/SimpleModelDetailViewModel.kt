package pt.isel.pdm.pokemonoftheday.ui.firestorePlayground

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import pt.isel.pdm.pokemonoftheday.domain.SimpleModel
import pt.isel.pdm.pokemonoftheday.services.FirestoreTestService

class SimpleModelDetailViewModel(
    private val service: FirestoreTestService,
    modelInfo: SimpleModel
) : ViewModel() {

    val state = service
        .getByIdFlow(modelInfo.id)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            modelInfo)
}