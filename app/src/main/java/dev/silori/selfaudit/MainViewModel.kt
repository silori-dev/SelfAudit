package dev.silori.selfaudit

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.silori.selfaudit.data.dataStore.DataStoreManager
import dev.silori.selfaudit.navigation.Destination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
     val repository: DataStoreManager
) : ViewModel() {

    private val _startDestinationRoute: MutableState<String> =
        mutableStateOf(Destination.OnBoardingScreen.route)

    val startDestinationRoute: State<String> = _startDestinationRoute

    fun saveStartDestination(destination: Destination) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveStartDestination(destination)
        }
    }

    init {
        viewModelScope.launch {
            repository.readStartDestination().collect { destination ->
                _startDestinationRoute.value = destination.route
            }
        }
    }

}