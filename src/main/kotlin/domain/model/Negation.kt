package dev.rohenkohl.domain.model

data class Negation(var variable: Variable) : Literal {

    override fun isUmkehr(literal: Literal): Boolean = when (literal) {
        is Negation -> false
        is Variable -> variable == literal
    }

    override fun toString(): String {
        return "!$variable"
    }
}