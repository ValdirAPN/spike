package br.com.spike.presentation.home

import br.com.spike.domain.repository.HomeRepository
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenModel(
    private val homeRepository: HomeRepository,
) : ScreenModel {

    init {
        fetchUserData()
    }

    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    fun fetchUserData() {
        screenModelScope.launch {
            val user = homeRepository.loadUserData()
            _state.update { oldState ->
                oldState.copy(
                    user = user
                )
            }
        }
    }
}