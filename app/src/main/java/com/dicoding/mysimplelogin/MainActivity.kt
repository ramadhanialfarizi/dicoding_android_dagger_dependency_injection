package com.dicoding.mysimplelogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.mysimplelogin.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var userRepository: UserRepository

    // For Check Singleton
    @Inject
    lateinit var userRepository2: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val sesi = SessionManager(this)
//        userRepository = UserRepository.getInstance(sesi)

        if (userRepository.isUserLogin()) {
            moveToHomeActivity()
        }

        binding.btnLogin.setOnClickListener {
            saveSession()
        }


        // For Check Singleton
        userRepository.checkInstance()
        userRepository2.checkInstance()

        // Result Log with Singleton
        /**
         * checkInstance: com.dicoding.mysimplelogin.UserRepository@c1cdb5a
         * checkInstance: com.dicoding.mysimplelogin.UserRepository@c1cdb5a
         *
         * **/

        // Result Log without Singleton
        /**
         * checkInstance: com.dicoding.mysimplelogin.UserRepository@c1cdb5a
         * checkInstance: com.dicoding.mysimplelogin.UserRepository@4bb018b
         *
         * **/
    }

    private fun saveSession() {
        userRepository.loginUser(binding.edUsername.text.toString())
        moveToHomeActivity()
    }

    private fun moveToHomeActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}
