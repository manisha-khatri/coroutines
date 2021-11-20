package com.example.coroutinesapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class AsyncCoroutineBuilderActivity : AppCompatActivity() {

    companion object{
        const val TAG = "AsyncCoroutineActivity"
    }

    var btn1: Button ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        initViews()

        btn1?.setOnClickListener {
            startAsync()
        }
    }

    private fun initViews() {
        btn1 = findViewById(R.id.btn1)
    }

    fun startAsync(){
        // Not returning any value
        runBlocking {
            Log.d(LaunchCoroutineBuilderActivity.TAG, "working on thread = " + Thread.currentThread().name)
            val job : Deferred<Unit> = async {
                Log.d(LaunchCoroutineBuilderActivity.TAG, "working on thread = " + Thread.currentThread().name + "before delay")
                delay(1000)
                Log.d(LaunchCoroutineBuilderActivity.TAG, "working on thread = " + Thread.currentThread().name + "after delay")
            }
        }

        //returning String
        runBlocking {
            Log.d(LaunchCoroutineBuilderActivity.TAG, "working on thread = " + Thread.currentThread().name)
            val job : Deferred<String> = async {
                Log.d(LaunchCoroutineBuilderActivity.TAG, "working on thread = " + Thread.currentThread().name + "before delay")
                delay(1000)
                Log.d(LaunchCoroutineBuilderActivity.TAG, "working on thread = " + Thread.currentThread().name + "after delay")
                "Aync task result"
            }
            val result = job.await()
            Log.d(TAG, "coroutine result = "+ result)

        }


    }


}