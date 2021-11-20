package com.example.coroutinesapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.delay

class RunBlockingCoroutineBuilderActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object{
        suspend fun myOwnSuspendingFunc(){
            delay(100)
        }
    }

}