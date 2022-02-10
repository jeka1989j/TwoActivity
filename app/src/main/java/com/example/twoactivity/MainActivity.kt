package com.example.twoactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.twoactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.firstActivityBtn.setOnClickListener {
            openActivityAboutMe(it)
        }
    }

    private fun openActivityAboutMe(view: View) {
        // get user input data
        val userName = binding.userName.text.toString()
        val userCity = binding.userCity.text.toString()

        // Create intent
        val intent = Intent(this, AboutMeActivity::class.java)
        // put data in Intent
        intent.putExtra(EXTRA_NAME, userName)
        intent.putExtra(EXTRA_City, userCity)
        // start our activity with intent with data, which we pass into intent
        startActivity(intent)

        overridePendingTransition(R.anim.slide_right, R.anim.slide_out_right)
    }

    companion object {
        const val EXTRA_NAME = "Name"
        const val EXTRA_City = "City"
    }
}