package br.com.spike.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.spike.ui.components.SpikeButton
import br.com.spike.ui.components.SpikeHorizontalSelector
import br.com.spike.ui.components.SpikeScreen
import br.com.spike.ui.components.SpikeTextField
import br.com.spike.ui.components.SpikeTopBar
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

object MatchForm : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        MatchFormContent(
            onNavigateBack = navigator::pop
        )
    }
}

@Composable
private fun MatchFormContent(
    onNavigateBack: () -> Unit
) {
    SpikeScreen(
        topBar = {
            SpikeTopBar(
                title = "Criar Partida",
                onNavigateBack = onNavigateBack
            )
        }
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            SpikeTextField(
                state = rememberTextFieldState(),
                label = "Título"
            )
            SpikeTextField(
                state = rememberTextFieldState(),
                label = "Data"
            )
            SpikeTextField(
                state = rememberTextFieldState(),
                label = "Hora de Início"
            )
            SpikeTextField(
                state = rememberTextFieldState(),
                label = "Fim"
            )
            SpikeHorizontalSelector(
                items = listOf("Areia", "Poliesportiva"),
                onSelectItem = {}
            )
            SpikeHorizontalSelector(
                items = listOf("Iniciante", "Intermediário", "Avançado"),
                onSelectItem = {}
            )
            SpikeHorizontalSelector(
                items = listOf("Misto", "Masculino", "Feminino"),
                onSelectItem = {}
            )
            SpikeHorizontalSelector(
                items = listOf("Pública", "Privada"),
                onSelectItem = {}
            )
            Spacer(Modifier.weight(1f))
            SpikeButton(
                label = "Criar",
                action = {},
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}