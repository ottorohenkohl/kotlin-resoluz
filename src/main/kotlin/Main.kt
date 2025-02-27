package dev.rohenkohl

import dev.rohenkohl.domain.service.ParserService
import dev.rohenkohl.domain.service.ResolutionsService

fun main() {
    print("Es können Aussagen über eine DNF der Form (a+b)(!d+c) getroffen werden. ")
    print("Bitte gib dafür deine deine KNF an: ")

    val dnf = ParserService().parse(readln())

    println(if (ResolutionsService().isAntinomie(dnf)) "Sie ist eine Antinomie!" else "Sie ist erfüllbar!")
}
