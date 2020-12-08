package com.example.vo2.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _miResultado: MutableLiveData<String>? by lazy { MutableLiveData<String>() }
    fun getmiResultado() = _miResultado as LiveData<String>
    fun setmiResultado(m: String, s:String, h: Boolean) {
        val calcula = Calculos()
        val mT: Double = calcula.miTiempo(m.toInt(),s.toInt())
        val i: String = if(h){
                            String.format ("%,.2f", calcula.calculaHombre(mT))
                        }else{
                            String.format("%,.2f",calcula.calculoMujer(mT))
                        }

       _miResultado?.value = "VO2 max = $i"
    }
    

}