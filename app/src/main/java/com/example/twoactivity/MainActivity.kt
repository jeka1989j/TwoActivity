package com.example.twoactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
            openActivityAboutMe()
        }
    }

    private fun openActivityAboutMe() {
        // get user input data
        val userName = binding.userName.text.toString()
        val userCity = binding.userCity.text.toString()

        // Create intent
        val intent = Intent(this, AboutMeActivity::class.java)
        // put data in Intent
        intent.putExtra(EXTRA_NAME, userName)
        intent.putExtra(EXTRA_City, userCity)
        // start our activity with intent with data, which we pass into intent
//        startActivity(intent)

        Log.i("TAG", "Call intent from MainActivity")
        startActivityForResult(intent, POSITIVE_REQUEST_CODE)
        overridePendingTransition(R.anim.slide_right, R.anim.slide_out_right)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == POSITIVE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                val resultAboutMeName = data?.getStringExtra("resultName")
                val resultAboutMeCity = data?.getStringExtra("resultCity")

                Log.i("TAG", "$resultAboutMeName, $resultAboutMeCity")
                if (resultAboutMeName.equals("") && resultAboutMeCity.equals("")){
                    binding.activityOne.text = resources.getString(R.string.hello_i_m_activity_one)
                }
                else if (resultAboutMeName.equals("") || resultAboutMeCity.equals("")){
                    when {
                        resultAboutMeName.equals("") ->
                            binding.activityOne.text = resources
                                .getString(R.string.resultAboutMeNameCity,
                                    "Nobody", resultAboutMeCity)
                        resultAboutMeCity.equals("") ->
                            binding.activityOne.text = resources
                                .getString(R.string.resultAboutMeNameCity,
                                     resultAboutMeName, "NewerLand")
                    }
                } else {
                    binding.activityOne.text = resources
                        .getString(R.string.resultFromAboutMe, resultAboutMeName, resultAboutMeCity)
                }
            }
        }
    }

    companion object {
        const val EXTRA_NAME = "Name"
        const val EXTRA_City = "City"

        const val POSITIVE_REQUEST_CODE = 1
    }
}