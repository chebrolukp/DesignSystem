package com.instagram.designsystem.core.designsystem.components.topBar

sealed interface TopBarVariant {
    data class Simple(
        val title: String
    ) : TopBarVariant

    data class Expanded(
        val title: String
    ) : TopBarVariant

    data class Back(
        val title: String,
        val onBackClick: () -> Unit
    ) : TopBarVariant

    data class Action(
        val title: String,
        val onActionClick: () -> Unit
    ) : TopBarVariant
}