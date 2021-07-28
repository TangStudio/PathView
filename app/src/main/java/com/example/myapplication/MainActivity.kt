package com.example.myapplication

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(),View.OnClickListener{
    private var lineView: LineView? = null
    private var relativeLayout: RelativeLayout? = null
    private var btnStart: Button? = null
    private var btnStop: Button? = null
    private var btnPick: Button? = null
    private val maxWidth: Int = 1300
    private val maxHeight: Int = 800
    private val firstX: Float =150F
    private val firstY: Float =150F
    private var timer: Timer = Timer()
    private var adapter: MainAdapter?=null
    private var chronometers: Chronometer? = null
    private val mList =mutableListOf<String>()
    private var task: TimerTask? = null
    private var recyclerView:RecyclerView?=null

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
        btnPick=findViewById(R.id.btn_pick)
        btnPick?.setOnClickListener(this)
        btnStart?.setOnClickListener(this)
        btnStop?.setOnClickListener(this)
        adapter = MainAdapter(this, mList)
        recyclerView = findViewById<RecyclerView>(R.id.list)
        recyclerView?.apply {
            itemAnimator = SlideInLeftAnimator()
            adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun addPlayView() {
        lineView = LineView(this)
        relativeLayout?.addView(lineView)
        lineView!!.setFirstXY1(firstX-50, firstY-60)
        lineView!!.setFirstXY2(firstX+30, firstY+280)
        lineView!!.setFirstXY3(firstX+130, firstY+300)
        lineView!!.setFirstXY4(firstX+380, firstY+300)
        lineView!!.setFirstXY5(firstX+500, firstY-90)
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.btn_start -> {
                /**
                 * Change this place to your own way of implementation. Generally speaking,
                 * high frequency and dense data are needed for transmission in order to show
                 * a coherent effect on canvas. For example, the back end uses socket to transmit
                 * high-frequency X and Y coordinate values, and the APP end also uses socket to receive data
                 */
//                startTask()
                chronometers?.start()
            }
            R.id.btn_stop -> {
                chronometers?.stop()
                endTask()
                lineView?.clearCanvas()
                addPlayView()
            }
            R.id.btn_pick -> {
                if (mList.size > 4) {
                    recyclerView?.post(Runnable { recyclerView?.scrollToPosition(0) })
                }
                adapter?.add(chronometers?.text as String, 0)
            }
        }
    }

    private fun startTask(){
        if(task==null){
            task = Task().task
            timer.schedule(task, 500, 500);
        }
    }

    private fun endTask() {
        task?.cancel()
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

    override fun onDestroy() {
        super.onDestroy()
        chronometers?.stop()
        chronometers=null
        task = null
    }
}
