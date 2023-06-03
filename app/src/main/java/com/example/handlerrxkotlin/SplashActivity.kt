package com.example.handlerrxkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log

class SplashActivity : AppCompatActivity() {
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        handler = Handler(Looper.getMainLooper())      // 1 - way
//        handler.postDelayed({
//            Log.d("@@@", " Hello ANdroid")
//            val intent = Intent(this,MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        },2000)

        object : CountDownTimer(2000,500){   // 2 - way
            override fun onTick(p0: Long) {
                Log.d("@@@", " Hello ANdroid")
            }

            override fun onFinish() {
            val intent = Intent(this@SplashActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
            }
        }.start()
    }
}