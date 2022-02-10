package com.example.twoactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import com.example.twoactivity.databinding.ActivityAboutMeBinding

class AboutMeActivity : AppCompatActivity() {
    lateinit var binding: ActivityAboutMeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutMeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.returnBtn.setOnClickListener {
            toast(R.string.toast_message)
            backToMainActivity()
        }
    }

    private fun toast(mess: Int) {
        val toast = Toast.makeText(this, mess, Toast.LENGTH_SHORT).show()
    }

    private fun backToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        overridePendingTransition(R.anim.slide_left, R.anim.slide_out_left)
    }

}