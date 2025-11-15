package br.com.spike.presentation.home

import br.com.spike.domain.model.Match
import br.com.spike.domain.model.User
import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeScreenModel : ScreenModel {

    private val _state = MutableStateFlow(HomeScreenState(
        username = "Matheus Carlos",
        avatarUrl = "https://plus.unsplash.com/premium_photo-1682144187125-b55e638cf286?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8cG9ydHJhaXQlMjBtYW58ZW58MHx8MHx8fDA%3D&fm=jpg&q=60&w=3000",
        upcomingMatches = listOf(
            Match(
                id = "match-1",
                title = "Vôlei no Ninho",
                spots = 18,
                users = listOf(
                    User(
                        id = "player-1",
                        name = "Matheus Carlos",
                        username = "matc",
                        avatarUrl = "https://plus.unsplash.com/premium_photo-1682144187125-b55e638cf286?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8cG9ydHJhaXQlMjBtYW58ZW58MHx8MHx8fDA%3D&fm=jpg&q=60&w=3000",
                    )
                ),
                organizer = User(
                    id = "player-1",
                    name = "Matheus Carlos",
                    username = "matc",
                    avatarUrl = "https://plus.unsplash.com/premium_photo-1682144187125-b55e638cf286?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8cG9ydHJhaXQlMjBtYW58ZW58MHx8MHx8fDA%3D&fm=jpg&q=60&w=3000",
                )
            ),
            Match(
                id = "match-2",
                title = "Vôlei na Rio Branco",
                spots = 18,
                users = listOf(
                    User(
                        id = "player-1",
                        name = "Matheus Carlos",
                        username = "matc",
                        avatarUrl = "https://plus.unsplash.com/premium_photo-1682144187125-b55e638cf286?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8cG9ydHJhaXQlMjBtYW58ZW58MHx8MHx8fDA%3D&fm=jpg&q=60&w=3000",
                    )
                ),
                organizer = User(
                    id = "player-1",
                    name = "Matheus Carlos",
                    username = "matc",
                    avatarUrl = "https://plus.unsplash.com/premium_photo-1682144187125-b55e638cf286?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8cG9ydHJhaXQlMjBtYW58ZW58MHx8MHx8fDA%3D&fm=jpg&q=60&w=3000",
                )
            )
        )
    ))
    val state = _state.asStateFlow()
}