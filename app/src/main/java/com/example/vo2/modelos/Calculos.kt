package com.example.vo2.modelos

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

//        Capacidad funcional
        private const val cf_baja = "Capacidad Funcional Baja"
        private const val cf_intermedia = "Capacidad Funcional Intermedia"
        private const val cf_alta = "Capacidad Funcional Alta"

//        Clase Funcional
        private const val cf_I = "Clase Funcional I"
        private const val cf_II = "Clase Funcional II"
        private const val cf_III = "Clase Funcional III"
        private const val cf_IV = "Clase Funcional IV"

    }

    fun miTiempo(m: Int, s: Int):Double{
        return m + (s/ sesenta)
    }

    fun calculoHombre(h: Double): Double{

        val h0 = uno_tres*h
        val h2 = cero_cuatro*h.pow(2)
        val h3 = cero_cerodoce*h.pow(3)
        return catorce_ocho- h0 + h2- h3

    }

    fun calculoMujer(m: Double):Double{
        return (cuatro_treintayocho * m) - tres_nueve
    }

    fun claseF(cf: String): String{
        val c = cf.toDouble()
        return when{
            c>6 -> cf_I
            c<=6 && c>4 -> cf_II
            c<=4 && c>2 -> cf_III
            else -> cf_IV
        }
    }

    fun capacidadF(cf: String): String{
        return when{
            cf.toDouble()<4.5 -> cf_baja
            cf.toDouble() in 4.5..6.0 -> cf_intermedia
            else -> cf_alta
        }
    }

}