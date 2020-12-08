package com.example.vo2.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vo2.databinding.ActivityValoresReferenciaBinding

class ValoresReferencia : AppCompatActivity() {

    private val binding by lazy {
        ActivityValoresReferenciaBinding.inflate(layoutInflater)
    }

    private val archivo = "CAPACIDAD FUNCIONAL.pdf"

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.pdfView.fromAsset(archivo).show()
    }
}