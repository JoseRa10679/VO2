package com.example.vo2.models

import kotlin.math.*

class Calculos {

    fun miTiempo(m: Int, s: Int):Double{
        return m + (s/60.0)
    }

    fun calculaHombre(h: Double): Double{

        val h0 = 1.379*h
        val h2 = 0.451*h.pow(2)
        val h3 = 0.012*h.pow(3)
        return 14.8- h0 + h2- h3
    }

    fun calculoMujer(m: Double):Double{
        return (4.38 * m) - 3.9
    }
}