package com.example.project

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.Toast
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

        auth = FirebaseAuth.getInstance()
        binding.btnLogin.setOnClickListener {
            var email = binding.edtEmailLogin.toString()
            var password = binding.edtPasswordLogin.toString()
// Ketentuan Register kalau register ga diisi email
            if (email.isEmpty()) {
                binding.edtEmailLogin.error = "Email harus diisi"
                binding.edtEmailLogin.requestFocus()
                return@setOnClickListener
            }
// Ketentuan kalau ga ada huruf @
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.edtEmailLogin.error = "Email tidak vaid"
                binding.edtEmailLogin.requestFocus()
                return@setOnClickListener
            }
// Ketentuan kalau ga isi password
            if (password.isEmpty()) {
                binding.edtPasswordLogin.error = "Password harus diisi"
                binding.edtPasswordLogin.requestFocus()
                return@setOnClickListener
            }
// Ketentuan password harus 8 huruf
            //kusus 8 karakter
            if (password.length < 8) {
                binding.edtPasswordLogin.error = "Password harus 8 karakter"
                binding.edtPasswordLogin.requestFocus()
                return@setOnClickListener
            }
            dataUserFirebase(email, password)
        }
       val loginBtn = findViewById<Button>(R.id.btn_login)
        loginBtn.setOnClickListener{
                  val pindahTempat = Intent(this, MainActivity::class.java)
                 startActivity(pindahTempat)
        finish()
             }
    }

    private fun dataUserFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "$email Berhasil Login", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "$email Gagal Login", Toast.LENGTH_SHORT).show()
                }
            }
    }
}


