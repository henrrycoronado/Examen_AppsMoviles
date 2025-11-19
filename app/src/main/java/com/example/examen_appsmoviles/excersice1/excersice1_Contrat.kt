package com.example.examen_appsmoviles.excersice1

data class User(
    val id: Int,
    val name: String,
    val photoUrl: String,
    val isFollowing: Boolean
)

data class UsersUiState(
    val users: List<User> = emptyList(),
    val isLoading: Boolean = false
)

sealed interface UsersIntent {
    data class ToggleFollowUser(val userId: Int) : UsersIntent
}