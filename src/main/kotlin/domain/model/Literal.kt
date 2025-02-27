package dev.rohenkohl.domain.model

sealed interface Literal {

    fun isUmkehr(literal: Literal): Boolean
}