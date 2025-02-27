package dev.rohenkohl.domain.service

import dev.rohenkohl.domain.model.Literal
import dev.rohenkohl.domain.model.Negation
import dev.rohenkohl.domain.model.Variable

class ParserService {

    private val knf = Regex("^\\((!?\\w+\\+)*!?\\w+\\)(\\((!?\\w+\\+)*!?\\w+\\))*$")

    fun parse(input: String): Set<Set<Literal>> {
        val syntaktischReduziert = input.replace("*", "").replace(" ", "")

        if (!knf.matches(syntaktischReduziert)) throw IllegalArgumentException("Syntax error in KNF")

        val aussenEntklammert = syntaktischReduziert.removePrefix("(").removeSuffix(")")
        val aufgeteilteKlauseln = aussenEntklammert.split(")(")
        val einzelneBezeichnungen = aufgeteilteKlauseln.map { it.split("+") }

        return einzelneBezeichnungen.map { klausel ->
            klausel.map literal@{ literal ->
                val variable = Variable(literal.removePrefix("!"))
                return@literal if (literal.startsWith("!")) Negation(variable) else variable
            }.toSet()
        }.toSet()
    }
}