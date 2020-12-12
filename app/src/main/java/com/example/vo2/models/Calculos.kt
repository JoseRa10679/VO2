package com.example.vo2.models

import kotlin.math.*



class Calculos {

    companion object{
        private const val sesenta: Double = 60.0

        private const val uno_tres: Double = 1.379
        private const val cero_cuatro: Double = 0.451
        private const val cero_cerodoce: Double = 0.012
        private const val catorce_ocho: Double = 14.8

        private const val cuatro_treintayocho: Double = 4.38
        private const val tres_nueve: Double = 3.9
    }

    fun miTiempo(m: Int, s: Int):Double{
        return m + (s/ sesenta)
    }

    fun calculaHombre(h: Double): Double{

        val h0 = uno_tres*h
        val h2 = cero_cuatro*h.pow(2)
        val h3 = cero_cerodoce*h.pow(3)
        return catorce_ocho- h0 + h2- h3

    }

    fun calculoMujer(m: Double):Double{
        return (cuatro_treintayocho * m) - tres_nueve
    }

}