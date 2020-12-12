package com.example.vo2.ui

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.vo2.R
import com.example.vo2.databinding.ActivityMainBinding
import com.example.vo2.models.MainViewModel
import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val miViewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    //<editor-folder desc = "Menu">

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.version -> {
                lateinit var version: String
                var packageInfo: PackageInfo
                ? = null
                try {
                    packageInfo = packageManager.getPackageInfo(packageName, 0)
                } catch (e: PackageManager.NameNotFoundException) {
                    e.printStackTrace()
                }

                if (packageInfo != null) version = packageInfo.versionName
                Toast.makeText(
                        this@MainActivity,
                        " Bruce VO2 versión: $version \n" +
                                " @Josera. Marzo 2020 ", Toast.LENGTH_SHORT
                ).apply {
                    val toastView: View = view
                    toastView.setBackgroundColor(ContextCompat.getColor(toastView.context, R.color.yellow))
                    setGravity(Gravity.CENTER or Gravity.CENTER_HORIZONTAL, 0, 0)
                    show()
                }
            }
            R.id.clasificacion -> {
                val intent = Intent(this, ValoresReferencia::class.java)
                startActivity(intent)
            }
            R.id.acerca -> {
                Toast.makeText(this, " @JoseRa \n Noviembre 2020 \n quistabenasque@gmail.com ", Toast.LENGTH_SHORT).apply {
                    setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
                    val toastView: View = view
                    toastView.setBackgroundColor(ContextCompat.getColor(toastView.context, R.color.yellow))
                    show()
                }
            }


        }
        return super.onOptionsItemSelected(item)
    }

    //</editor-folder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.editTextMinutos.run{
            requestFocus()
            addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun afterTextChanged(s: Editable?) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val miS = if (s.isNullOrEmpty()) 0 else  s.toString().toInt()
                    if (miS > 30) {
                        Toast.makeText(applicationContext, " Probable Error:\n El valor es superior a 30 minutos ", Toast.LENGTH_SHORT).apply {
                            setGravity(Gravity.CENTER, 0, 0)
                            setPadding(50, 50, 50, 50)
                            view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.yellow)
                            )
                            show()
                        }
                        selectAll()
                    }
                }
            })
        }

        binding.editTextSegundos.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val miS = if (s.isNullOrEmpty()) 0 else s.toString().toInt()
                if (miS > 59) {
                    Toast.makeText(applicationContext, " Error: El valor no puede ser superior a 59 ", Toast.LENGTH_SHORT).apply {
                        setGravity(Gravity.CENTER, 0, 0)
                        view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.yellow))
                        show()
                    }
                    with(binding.editTextSegundos) {
                        setText("0")
                        selectAll()
                    }
                }
            }
            override fun afterTextChanged(s: Editable?) {}

        })

        miViewModel.getmiResultado().observe(this, {
            binding.textViewResultado.text = it
        })

        miViewModel.getmiMETs().observe(this,{
            binding.textViewResultado2.text = it
        })

        binding.button.setOnClickListener {
            with(binding) {
                if (editTextMinutos.text.isNotEmpty() && editTextSegundos.text.isNotEmpty()) {
                    miViewModel.setmiResultado(editTextMinutos.text.toString(),
                            editTextSegundos.text.toString(),
                            rbHombre.isChecked)
                    miViewModel.setmiMETs(editTextMinutos.text.toString(),
                            editTextSegundos.text.toString(),
                            rbHombre.isChecked)
                    UIUtil.hideKeyboard(this@MainActivity)
                }
            }
        }

    }


}