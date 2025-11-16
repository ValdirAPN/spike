package br.com.spike.presentation.profile

import br.com.spike.domain.repository.ProfileRepository
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileScreenModel(
    private val profileRepository: ProfileRepository,
) : ScreenModel {

    init {
        fetchUserData()
    }

    private val _state = MutableStateFlow(ProfileScreenState())
    val state = _state.asStateFlow()

    private fun fetchUserData() {
        screenModelScope.launch {
            val user = profileRepository.fetchUserData()
            _state.update { oldState -> oldState.copy(user = user) }
        }
    }

    fun signOut() {
        profileRepository.signOut()
    }
}