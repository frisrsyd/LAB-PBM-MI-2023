package com.frisrsyd.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.frisrsyd.login.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        supportActionBar?.hide()

        binding.btnLogin.setOnClickListener(View.OnClickListener {
            val email = binding.username.text.toString()
            val password = binding.password.text.toString()

            if(email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                if (password.isNotEmpty()) {
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnSuccessListener {
                            val intent = Intent(this, HomeActivity::class.java)
                            Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
                            startActivity(intent)
                            finish()
                        }
                        .addOnFailureListener() {
                            Toast.makeText(this, "Login Gagal " + it.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                } else {
                    binding.password.error = "Password tidak boleh kosong"
                }
            }else {
                binding.username.error = "Email tidak boleh kosong atau email tidak valid"
            }
        })

        binding.toRegister.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        })
    }
}