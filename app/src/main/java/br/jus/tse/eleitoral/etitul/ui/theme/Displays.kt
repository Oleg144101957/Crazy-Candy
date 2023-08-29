package br.jus.tse.eleitoral.etitul.ui.theme

sealed class Displays(val endPoint: String) {
    object Display1Route : Displays("display1")
    object Display2Route : Displays("display2")
    object Display3Route : Displays("display3")
}