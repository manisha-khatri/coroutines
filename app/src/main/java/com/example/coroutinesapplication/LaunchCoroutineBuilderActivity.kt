package com.example.coroutinesapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LaunchCoroutineBuilderActivity : AppCompatActivity() {

    companion object {
        const val TAG ="MainActivity"
    }

    var btn1 : Button ?= null
    var btn2 : Button ?= null
    var btn3 : Button ?= null
    var btn4 : Button ?= null
    var btn5 : Button ?= null
    var btn6 : Button ?= null
    var btn7 : Button ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        btn1?.setOnClickListener {
            workOnBackgroundThreadWay4()
        }
        btn2?.setOnClickListener {
            workOnBackgroundThreadWay1()
        }
        btn3?.setOnClickListener {
            workOnBackgroundThreadWay2()
        }
        btn4?.setOnClickListener {
            workOnBackgroundThreadWay3()
        }
        btn5?.setOnClickListener {
            val intent = Intent(this, AsyncCoroutineBuilderActivity::class.java)
            startActivity(intent)
        }
        btn6?.setOnClickListener {
            val intent = Intent(this, RunBlockingCoroutineBuilderActivity::class.java)
            startActivity(intent)
        }
        btn7?.setOnClickListener {
            cancelCoroutine()
        }
    }

    private fun initViews() {
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)
        btn6 = findViewById(R.id.btn6)
        btn7 = findViewById(R.id.btn7)
    }

    fun cancelCoroutine(){
        runBlocking {
            Log.d(TAG, "working on thread = " + Thread.currentThread().name)

            val job = launch {
                for (i in 0..100){
                    Log.d(TAG, "working on thread = " + Thread.currentThread().name + " count = " + i)
                }
            }
            job.join()
            Log.d(TAG, "working on thread = " + Thread.currentThread().name)
        }
    }

    fun workOnBackgroundThreadWay4(){
        runBlocking {
            Log.d(TAG, "working on thread = " + Thread.currentThread().name)

            GlobalScope.launch {
                Log.d(TAG, "working on thread = " + Thread.currentThread().name + "before delay")
                delay(100)
                Log.d(TAG, "working on thread = " + Thread.currentThread().name + "after delay")
            }
            delay(1000)
            Log.d(TAG, "working on thread = " + Thread.currentThread().name)

        }
    }

    fun workOnBackgroundThreadWay1(){

        // Way 1
            Log.d(TAG, "working on thread = " + Thread.currentThread().name)
            GlobalScope.launch {
                Log.d(TAG, "working on thread = " + Thread.currentThread().name + "before delay")
                delay(10000)
                Log.d(TAG, "working on thread = " + Thread.currentThread().name + "after delay")
            }
            Log.d(TAG, "working on thread = " + Thread.currentThread().name)

    }

    fun workOnBackgroundThreadWay2(){
        //Way 2 : runBlocking + GlobalScope
        runBlocking {
            Log.d(TAG, "working on thread = " + Thread.currentThread().name)
            GlobalScope.launch {
                Log.d(TAG, "working on thread = " + Thread.currentThread().name + "before delay")
                delay(10000)
                Log.d(TAG, "working on thread = " + Thread.currentThread().name + "after delay")
            }
            delay(1000)
            Log.d(TAG, "working on thread = " + Thread.currentThread().name)

        }

    }


    fun workOnBackgroundThreadWay3(){
        //Way 2 : runBlocking + GlobalScope
        runBlocking {
            Log.d(TAG, "working on thread = " + Thread.currentThread().name)
            val job = launch {
                Log.d(TAG, "working on thread = " + Thread.currentThread().name + "before delay")
                delay(10000)
                Log.d(TAG, "working on thread = " + Thread.currentThread().name + "after delay")
            }
            job.join()
            Log.d(TAG, "working on thread = " + Thread.currentThread().name)

        }

    }


}