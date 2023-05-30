package com.example.handlerrxkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var handler: Handler
    private var gameOn = false    // 1
    private var startTime = 0L    // 1
    var doubleBackToExitPressOnce = false  // 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startTime = System.currentTimeMillis()                 // 1 - way  start
        handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                if (gameOn) {
                    Log.d("Time", "${System.currentTimeMillis() - startTime} ")
                }
                handler.sendEmptyMessageDelayed(0, 1000)
            }
        }
        gameOn = true
        handler.sendEmptyMessage(0)         // 1 - way finish
    }

    override fun onBackPressed() {   // 2 - way
        if (doubleBackToExitPressOnce) {
            super.onBackPressed()
            return
        }
        handler = Handler(Looper.getMainLooper())
        this.doubleBackToExitPressOnce = true
        Toast.makeText(this, "Please click Back again to exit !!", Toast.LENGTH_SHORT).show()
        handler.postDelayed({
            doubleBackToExitPressOnce = false
        }, 2000)
    }


//
//    override fun onPause() {    // 1 - way
//        super.onPause()
//        handler.removeMessages(0)
//    }
//
//    override fun onResume() {   // 1 - way
//        super.onResume()
//        handler.removeMessages(0)
//        handler.sendEmptyMessage(0)
//    }
}