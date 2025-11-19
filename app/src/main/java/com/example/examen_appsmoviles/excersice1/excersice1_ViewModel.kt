package com.example.examen_appsmoviles.excersice1

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UsersViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UsersUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                users = listOf(
                    User(1, "Henrry Coronado", "url_foto_1", false),
                    User(2, "Maria Belen", "url_foto_2", true),
                    User(3, "Juan Perez", "url_foto_3", false)
                )
            )
        }
    }

    fun processIntent(intent: UsersIntent) {
        when(intent) {
            is UsersIntent.ToggleFollowUser -> toggleFollow(intent.userId)
        }
    }

    private fun toggleFollow(userId: Int) {
        _uiState.update { currentState ->
            val updatedUsers = currentState.users.map { user ->
                if (user.id == userId) {
                    user.copy(isFollowing = !user.isFollowing)
                } else {
                    user
                }
            }
            currentState.copy(users = updatedUsers)
        }
    }
}