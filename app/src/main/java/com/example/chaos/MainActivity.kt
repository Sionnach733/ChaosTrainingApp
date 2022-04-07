package com.example.chaos

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        //init random array
        val from = 1
        val to = 1200
        val random = Random()
        val myTimes  = IntArray(10) { random.nextInt(to - from) +  from }.asList()
        // test code to display random times
        //        val myTimes = arrayOf(1195,1190,1185,1180,1175,1170,1100,1090,1080,1070).asList()
        //        val randomText = findViewById<TextView>(R.id.textView3)
        //        randomText.text = myTimes.toString()
        val mediaPlayer = MediaPlayer.create(this@MainActivity, R.raw.tindeck_1)

        //start button
        val button: Button = findViewById(R.id.startButton)
        button.setOnClickListener() {
                startTimer(myTimes, mediaPlayer)
        }
    }

    fun displayRandomMovement(): String{
        val movements = listOf("20 sync goblet squat", "20 sync oh lunge", "10 sync ttb", "10 sync pull ups", "20 sync goblet squat", "RTGAB")
        return movements.random()
    }

    fun startTimer(myTimes: List<Int>, mediaPlayer: MediaPlayer) {
        val tv = findViewById<TextView>(R.id.textView1)
        val movementText = findViewById<TextView>(R.id.textView2)
        object : CountDownTimer(20 * 60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = (millisUntilFinished/1000).toInt()
                tv.text = SimpleDateFormat("mm:ss").format(Date(millisUntilFinished))
                if (myTimes.contains(secondsRemaining)){
                    mediaPlayer.start()
                    movementText.text = movementText.text.toString() + "\n" + displayRandomMovement() + " " + SimpleDateFormat("mm:ss").format(Date(millisUntilFinished))
                }
            }

            override fun onFinish() {
                tv.text = "Done!"
            }
        }.start()
    }

}