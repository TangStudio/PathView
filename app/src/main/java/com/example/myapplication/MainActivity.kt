package com.example.myapplication

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(),View.OnClickListener{
    private var lineView: LineView? = null
    private var relativeLayout: RelativeLayout? = null
    private var btnStart: Button? = null
    private var btnStop: Button? = null
    private val maxWidth: Int = 1300
    private val maxHeight: Int = 800
    private val firstX: Float =150F
    private val firstY: Float =150F
    private var timer: Timer = Timer()
    private var chronometers: Chronometer? = null

    private var task: TimerTask? = null

    val handler : Handler = Handler{
        when(it.what){
            0 -> {
                try {
//                    var t = it.data.get("data")
                    lineView!!.drawLine(
                        Random.nextInt(maxWidth).toFloat(),
                        Random.nextInt(maxHeight).toFloat(),
                        "0"
                    )
                    lineView!!.drawLine(
                        Random.nextInt(maxWidth).toFloat(),
                        Random.nextInt(maxHeight).toFloat(),
                        "1"
                    )
                    lineView!!.drawLine(
                        Random.nextInt(maxWidth).toFloat(),
                        Random.nextInt(maxHeight).toFloat(),
                        "2"
                    )
                    lineView!!.drawLine(
                        Random.nextInt(maxWidth).toFloat(),
                        Random.nextInt(maxHeight).toFloat(),
                        "3"
                    )
                    lineView!!.drawLine(
                        Random.nextInt(maxWidth).toFloat(),
                        Random.nextInt(maxHeight).toFloat(),
                        "4"
                    )
                } catch (ex: Throwable) {
                    ex.printStackTrace()
                }
            }
            else -> {
            }
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        addPlayView()
    }

    private fun initView() {
        chronometers = findViewById(R.id.chronometer)
        relativeLayout=findViewById(R.id.rl)
        btnStart=findViewById(R.id.btn_start)
        btnStop=findViewById(R.id.btn_stop)
        btnStart?.setOnClickListener(this)
        btnStop?.setOnClickListener(this)
    }

    private fun addPlayView() {
        lineView = LineView(this)
        relativeLayout?.addView(lineView)
        lineView!!.setFirstXY1(firstX, firstY)
        lineView!!.setFirstXY2(firstX, firstY)
        lineView!!.setFirstXY3(firstX, firstY)
        lineView!!.setFirstXY4(firstX, firstY)
        lineView!!.setFirstXY5(firstX, firstY)

//        chronometers?.setOnChronometerTickListener {
//            it.
//        }
    }

    private fun startTimer(){
        timer.schedule(object : TimerTask() {
            override fun run() {
                handler.sendEmptyMessage(0)
            }
        }, 500, 500)
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.btn_start -> {
                startTask()
                chronometers?.start()
            }
            R.id.btn_stop -> {
                Log.e("sdf", chronometers?.text as String)
                Log.e("sdf2", chronometers?.base.toString())
                endTask()
                lineView?.clearCanvas()
                addPlayView()
            }
        }
    }

    private fun startTask(){
        task = Task().task
        timer.schedule(task, 500, 500);
    }

    private fun endTask() {
        task!!.cancel()
        task = null
    }
    inner class Task {
        val task: TimerTask
            get() = object : TimerTask() {
                override fun run() {
                    handler.sendEmptyMessage(0)
                }
            }
    }

}
