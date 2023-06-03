package com.example.handlerrxkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast
import com.example.handlerrxkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var handler: Handler
    private var gameOn = false
    private var startTime = 0L
    var doubleBackToExitPressOnce = false
    var mProgress = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Thread(myThread).start()

        startTime = System.currentTimeMillis()
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
        handler.sendEmptyMessage(0)
    }

    private val myThread : Runnable = object : Runnable {
        override fun run() {
            while (mProgress < 100){
                try {
                    myHandler.sendMessage(myHandler.obtainMessage())
                    Thread.sleep(200)
                }catch (t:Throwable){

                }
            }
        }
        var myHandler: Handler  = object : Handler(Looper.getMainLooper()){
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                mProgress++
                binding.progressBar.progress = mProgress
            }
        }
    }


    override fun onBackPressed() {
        if (doubleBackToExitPressOnce) {
            super.onBackPressed()
            return
        }
        handler = Handler(Looper.getMainLooper())
        this.doubleBackToExitPressOnce = true
        Toast.makeText(this, "Please click back again to exit", Toast.LENGTH_SHORT).show()
        handler.postDelayed({
            doubleBackToExitPressOnce = false
        }, 2000)
    }

    override fun onPause() {
        super.onPause()
        handler.removeMessages(0)
    }

    override fun onResume() {
        super.onResume()
        handler.removeMessages(0)
        handler.sendEmptyMessage(0)
    }
}