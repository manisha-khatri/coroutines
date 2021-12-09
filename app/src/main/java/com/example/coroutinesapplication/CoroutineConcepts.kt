package com.example.coroutinesapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

class CoroutineConcepts : AppCompatActivity(){

    companion object{
        const val TAG = "CoroutineConcepts"
    }

    var btn1: Button ?= null
    var btn2: Button ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        initViews()

        btn1?.setOnClickListener {
            sequentialExecutionOfSuspendFunc()
        }
        btn2?.setOnClickListener {
            concurrentExecutionOfSuspendFunc()
        }

    }

    fun initViews(){
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
    }


    fun sequentialExecutionOfSuspendFunc(){
        runBlocking {
            val timeTaken = measureTimeMillis {
                val m1 = message1()
                val m2 = message2()
                Log.d(TAG, "Merged Result = "+ (m1+m2))
            }
            Log.d(TAG, "Total time taken = "+ timeTaken)

        }
    }

    fun concurrentExecutionOfSuspendFunc(){
        runBlocking {
            val timeTaken = measureTimeMillis {
                val m1 = async { message1() }
                val m2 = async { message2() }
                Log.d(TAG, "Merged Result = "+ (m1.await() +  m2.await()))
            }
            Log.d(TAG, "Total time taken = "+ timeTaken)
        }
    }

    suspend fun message1():String{
        delay(100L)
        return "Hello"

    }

    suspend fun message2(): String{
        delay(100L)
        return "World"
    }


}