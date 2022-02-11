package com.example.twoactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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


        retrieveFromMainActivity()

        binding.returnBtn.setOnClickListener {
            toast(R.string.toast_message)
//            toast(resources.getString(R.string.toast_message))

//            // set data from intent MainActivity
//            val result = retrieveFromMainActivity()
//            Log.i("About", "$result")
//            // instantiate empty intent
//            val resultIntent = Intent()
//            // put data in intent which we instantiate before
//            resultIntent.putExtra("result", result)
//
//            // return back to MainActivity with values
//            setResult(RESULT_OK, resultIntent)

            backToMainActivity()
//            finish()
        }
    }

    private fun toast(mess: Int) {
        val toast = Toast.makeText(this, mess, Toast.LENGTH_SHORT).show()
    }

    private fun backToMainActivity() {
//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
        val userName = intent.getStringExtra(MainActivity.EXTRA_NAME)?.uppercase()
        val userCity = intent.getStringExtra(MainActivity.EXTRA_City)?.uppercase()
        // set data from intent MainActivity
        val resultName = userName
        val resultCity = userCity
        Log.i("About", "$resultName, $resultCity")
        // instantiate empty intent
        val resultIntent = Intent()
        // put data in intent which we instantiate before
        resultIntent.putExtra("resultName", resultName)
        resultIntent.putExtra("resultCity", resultCity)

        // return back to MainActivity with values
        setResult(RESULT_OK, resultIntent)

        finish()
        overridePendingTransition(R.anim.slide_left, R.anim.slide_out_left)
    }

    private fun retrieveFromMainActivity() {
        // retrieve data from MainActivity intent and put in AboutMeActivity intent
        val userName = intent.getStringExtra(MainActivity.EXTRA_NAME)?.uppercase()
        val userCity = intent.getStringExtra(MainActivity.EXTRA_City)?.uppercase()

        // get instance of TextView where we will pass data
        val setUserName = binding.setUserName
        val setUserCity = binding.setUserCity

        // set view visible & pass data to them from MainActivity intent
        setUserName.changeVisibility()
        setUserName.text = userName
//        setUserCity.visibility = View.VISIBLE
        setUserCity.changeVisibility()
        setUserCity.text = userCity
    }

    // create extension function, to refuse from code duplication
    private fun View.changeVisibility() {
        if (visibility == View.GONE) {
            visibility = View.VISIBLE
        }
    }
}