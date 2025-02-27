package dev.rohenkohl.domain.model

data class Variable(val bezeichnung: String) : Literal {

    override fun isUmkehr(literal: Literal): Boolean = when (literal) {
        is Variable -> false
        is Negation -> this == literal.variable
    }

    override fun toString(): String {
        return bezeichnung
    }
}