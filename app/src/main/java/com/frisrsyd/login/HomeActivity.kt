package com.frisrsyd.login

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.frisrsyd.login.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        loadFragment(HomeFragment())

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.message -> {
                    loadFragment(SearchFragment())
                    true
                }
                R.id.settings -> {
                    loadFragment(InboxFragment())
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
//            R.id.action_logout -> {
////                val intent = Intent(this, MainActivity::class.java)
////                startActivity(intent)
//                finish()
//            }

            R.id.action_logout -> {
                auth.signOut()
                val intent = Intent(this, MainActivity::class.java)
                Toast.makeText(this, "Logout Berhasil", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                finish()
            }

            R.id.action_about_me -> {
                val intent = Intent(this, AccountActivity::class.java)
                startActivity(intent)
            }

//            R.id.action_logout_2 -> {
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
////                finish()
//            }

        }
        return super.onOptionsItemSelected(item)
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
}