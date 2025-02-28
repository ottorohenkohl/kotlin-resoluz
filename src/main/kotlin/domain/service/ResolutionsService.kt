package dev.rohenkohl.domain.service

import dev.rohenkohl.domain.model.Literal

class ResolutionsService {

    fun isAntinomie(klauseln: Set<Set<Literal>>): Boolean {
        val geordnet = klauseln.toList()

        if (geordnet.size < 2) return false

        val indices = mutableListOf(0)
        val aktuelle = mutableListOf(geordnet[0])

        while (true) {
            val vereinigung = aktuelle.last().union(geordnet[indices.last()])
            val eliminierbar = vereinigung.filter { vereinigung.any(it::isUmkehr) }.toSet()

            if (eliminierbar.size == 2) {
                aktuelle.addLast(vereinigung.minus(eliminierbar))
                indices.addLast(0)

                if (aktuelle.last().isEmpty()) return true else continue
            }

            while (indices.last() == geordnet.lastIndex) {
                if (indices.size == 1) return false

                indices.removeLast()
                aktuelle.removeLast()
            }

            indices.addLast(indices.removeLast() + 1)
        }
    }
}