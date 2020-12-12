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

    private fun miCalculo(m: String, s: String, h:Boolean, mets:Boolean):String{
        val calcula = Calculos()
        val mT: Double = calcula.miTiempo(m.toInt(),s.toInt())
        val x: Double = if (mets) 3.5 else 1.0
        return if(h){String.format ("%,.2f", calcula.calculaHombre(mT)/x)
                    }else{String.format("%,.2f",calcula.calculoMujer(mT)/x)}
    }

}