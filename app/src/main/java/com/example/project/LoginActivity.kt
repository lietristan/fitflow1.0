package com.example.project

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.project.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    //Bikin kata"Klik disini jika belum punya akun" pindah ke halaman register(1)
    lateinit var binding: ActivityLoginBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
        val loginBtn = findViewById<Button>(R.id.btn_login)
        loginBtn.setOnClickListener{
            val pindahTempat = Intent(this, MainActivity::class.java)
            startActivity(pindahTempat)
            finish()
        }
    }
}


