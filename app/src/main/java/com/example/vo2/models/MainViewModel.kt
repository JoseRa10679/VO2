package com.example.vo2.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _miResultado: MutableLiveData<String>? by lazy { MutableLiveData<String>() }
    fun getmiResultado() = _miResultado as LiveData<String>
    fun setmiResultado(m: String, s:String, h: Boolean) {
        val i = miCalculo(m,s,h,false)
        _miResultado?.value = "VO2 max = $i"
    }

    private val _miMETs: MutableLiveData<String>? by lazy { MutableLiveData<String>() }
    fun getmiMETs() = _miMETs as LiveData<String>
    fun setmiMETs(m: String, s:String, h: Boolean){
        val i = miCalculo(m,s,h,true)
       _miMETs?.value = "$i METs"
    }

    private val _claseF: MutableLiveData<String>? by lazy { MutableLiveData<String>() }
    fun getclaseF() = _claseF as LiveData<String>
    fun setclaseF(m:String,s:String,h:Boolean){
        val calculos = Calculos()
        val mT: Double = calculos.miTiempo(m.toInt(),s.toInt())
        val i= if (h) {calculos.calculaHombre(mT)/3.5} else {calculos.calculoMujer(mT)/3.5}
       _claseF?.value = calculos.clase_F(i.toString())
    }

    private val _capFuncional: MutableLiveData<String>? by lazy { MutableLiveData<String>() }
    fun getcapFuncional() = _capFuncional as LiveData<String>
    fun setcapFuncional(m:String,s:String,h:Boolean){
        val calculos = Calculos()
        val mT: Double = calculos.miTiempo(m.toInt(),s.toInt())
        val i= if (h) {calculos.calculaHombre(mT)/3.5} else {calculos.calculoMujer(mT)/3.5}
       _capFuncional?.value = calculos.capacidad_F(i.toString())
    }

    private fun miCalculo(m: String, s: String, h:Boolean, mets:Boolean):String{
        val calcula = Calculos()
        val mT: Double = calcula.miTiempo(m.toInt(),s.toInt())
        val x: Double = if (mets) 3.5 else 1.0
        return if(h){String.format ("%,.2f", calcula.calculaHombre(mT)/x)
                    }else{String.format("%,.2f",calcula.calculoMujer(mT)/x)}
    }

}