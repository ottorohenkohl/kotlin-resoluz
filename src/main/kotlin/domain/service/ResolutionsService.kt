package dev.rohenkohl.domain.service

import dev.rohenkohl.domain.model.Literal

class ResolutionsService {

    private fun isEliminierbar(aktuell: Set<Literal>, naechstes: Set<Literal>): Boolean {
        val schnittmenge = aktuell.intersect(naechstes)
        val sdifferenz = aktuell.union(naechstes).minus(schnittmenge)

        if (aktuell.size != naechstes.size) return false
        if (aktuell.size - 1 != schnittmenge.size) return false

        return sdifferenz.toList()[0].isUmkehr(sdifferenz.toList()[1])
    }

    fun isAntinomie(klauseln: Set<Set<Literal>>): Boolean {
        val geordnet = klauseln.toList()

        if (geordnet.size < 2) return false

        val indices = mutableListOf(0)
        val aktuelle = mutableListOf(geordnet[0])

        while (true) {
            if (isEliminierbar(aktuelle.last(), geordnet[indices.last()])) {
                aktuelle.addLast(aktuelle.last().intersect(geordnet[indices.last()]))
                indices.addLast(0)

                if (aktuelle.last().isEmpty()) return true else continue
            }

            if (indices.last() == geordnet.lastIndex) {
                if (indices.size == 1) return false

                indices.removeLast()
                aktuelle.removeLast()
            }

            indices.addLast(indices.removeLast() + 1)
        }
    }
}