package com.example.project

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.project.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener {
            var email = binding.edtEmailRegister.text.toString()
            var password = binding.edtPasswordRegister.text.toString()
// Ketentuan Register kalau register ga diisi email
            if(email.isEmpty()){
                binding.edtEmailRegister.error = "Email harus diisi"
                binding.edtEmailRegister.requestFocus()
                return@setOnClickListener
            }
// Ketentuan kalau ga ada huruf @
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.edtEmailRegister.error = "Email tidak vaid"
                binding.edtEmailRegister.requestFocus()
                return@setOnClickListener
            }
// Ketentuan kalau ga isi password
            if(password.isEmpty()){
                binding.edtPasswordRegister.error = "Password harus diisi"
                binding.edtPasswordRegister.requestFocus()
                return@setOnClickListener
            }
// Ketentuan password harus 8 huruf
            //kusus 8 karakter
            if(password.length < 8){
                    binding.edtPasswordRegister.error = "Password harus 8 karakter"
                    binding.edtPasswordRegister.requestFocus()
                    return@setOnClickListener
            }
            dataUserFirebase(email, password)
        }
        binding.tvRegister.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }




    }

    private fun dataUserFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if ( it.isSuccessful){
                    Toast.makeText(this, "Berhasil Membuat Akun", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }

    }

// Buat Toast

}