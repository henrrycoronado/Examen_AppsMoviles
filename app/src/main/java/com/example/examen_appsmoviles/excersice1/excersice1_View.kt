package com.example.examen_appsmoviles.excersice1

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.examen_appsmoviles.excersice1.components.UserCard

@Composable
fun UsersScreen(
    viewModel: UsersViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(state.users) { user ->
                UserCard(
                    name = user.name,
                    photoUrl = user.photoUrl,
                    isFollowing = user.isFollowing,
                    onFollowClick = {
                        viewModel.processIntent(UsersIntent.ToggleFollowUser(user.id))
                    }
                )
            }
        }
    }
}