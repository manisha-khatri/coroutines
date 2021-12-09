package com.example.coroutinesapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.coroutines.*

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
    var btn8 : Button ?= null
    var btn9 : Button ?= null
    var btn10 : Button ?= null
    var btn11 : Button ?= null
    var btn12 : Button ?= null
    var btn13 : Button ?= null
    var btn14 : Button ?= null
    var btn15 : Button ?= null
    var isCancelCoroutine : Boolean = false


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
            notCancellableCoroutine()
        }
        btn8?.setOnClickListener {
            cancellableCoroutineWithDelay()
        }
        btn9?.setOnClickListener {
            cancellableCoroutineWithYield()
        }
        btn10?.setOnClickListener {
            cancellableCoroutineWithIsActiveFlag()
        }
        btn11?.setOnClickListener {
            cancellableCoroutineExceptionHandling()
        }
        btn12?.setOnClickListener {
            exceptionHandlingFinally()
        }
        btn13?.setOnClickListener {
            withTimeoutFunctions()
        }
        btn14?.setOnClickListener {
            withTimeoutOrNullFunctions()
        }
        btn15?.setOnClickListener {
            val intent = Intent(this, CoroutineConcepts::class.java)
            startActivity(intent)
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
        btn8 = findViewById(R.id.btn8)
        btn9 = findViewById(R.id.btn9)
        btn10 = findViewById(R.id.btn10)
        btn11 = findViewById(R.id.btn11)
        btn12 = findViewById(R.id.btn12)
        btn13 = findViewById(R.id.btn13)
        btn14 = findViewById(R.id.btn14)
        btn15 = findViewById(R.id.btn15)
    }

    var job : Job ?= null
    fun notCancellableCoroutine(){
        runBlocking {
            Log.d(TAG, "working on thread = " + Thread.currentThread().name)

            if(isCancelCoroutine == false) {
                isCancelCoroutine = true
                job = GlobalScope.launch {
                    for (i in 0..100) {
                        Log.d(TAG, " count = " + i)
                        Thread.sleep(100)
                    }
                }
                Log.d(TAG, "working on thread = " + Thread.currentThread().name)
            }else{
                isCancelCoroutine = false
                job?.cancel()
                Log.d(TAG, "Cancelling coroutine = " + Thread.currentThread().name)
            }

        }
    }

    var job2 : Job ?= null
    fun cancellableCoroutineWithDelay(){
        runBlocking {
            Log.d(TAG, "working on thread = " + Thread.currentThread().name)

            if(isCancelCoroutine == false) {
                isCancelCoroutine = true
                job2 = GlobalScope.launch {
                    for (i in 0..100) {
                        Log.d(TAG, " count = " + i)
                        delay(100)  // making it cancellable with delay
                    }
                }
                Log.d(TAG, "working on thread = " + Thread.currentThread().name)
            }else{
                isCancelCoroutine = false
                job2?.cancel()
                Log.d(TAG, "Cancelling coroutine = " + Thread.currentThread().name)
            }

        }
    }

    var job3 : Job ?= null
    fun cancellableCoroutineWithYield(){
        runBlocking {
            Log.d(TAG, "working on thread = " + Thread.currentThread().name)

            if(isCancelCoroutine == false) {
                isCancelCoroutine = true
                job3 = GlobalScope.launch {
                    for (i in 0..100) {
                        Log.d(TAG, " count = " + i)
                        yield()  // making it cancellable with yield
                    }
                }
                Log.d(TAG, "working on thread = " + Thread.currentThread().name)
            }else{
                isCancelCoroutine = false
                job3?.cancel()
                Log.d(TAG, "Cancelling coroutine = " + Thread.currentThread().name)
            }

        }
    }

    var job4 : Job ?= null
    fun cancellableCoroutineWithIsActiveFlag(){
        runBlocking {
            Log.d(TAG, "working on thread = " + Thread.currentThread().name)

            job4 = launch(Dispatchers.Default) {
                for (i in 0..100) {
                    Log.d(TAG, " count = " + i)
                    if(!isActive)
                        return@launch //break;
                    Thread.sleep(1)
                }
            }
            delay(10)
            Log.d(TAG, "working on thread = " + Thread.currentThread().name)
            job4?.cancelAndJoin()
            Log.d(TAG, "Cancelling coroutine = " + Thread.currentThread().name)

        }
    }

    var job5 : Job ?= null
    fun cancellableCoroutineExceptionHandling(){
        runBlocking {
            Log.d(TAG, "working on thread = " + Thread.currentThread().name)

            job5 = launch(Dispatchers.Default) {
                try {
                    for (i in 0..500) {
                        Log.d(TAG, " count = " + i)
                        delay(5)
                    }
                }catch (e:CancellationException){
                    Log.d(TAG, e.message.toString())
                }finally {
                    Log.d(TAG, " finally ")
                }

            }
            delay(10)
            Log.d(TAG, "working on thread = " + Thread.currentThread().name)
            job5?.cancelAndJoin()
            Log.d(TAG, "Cancelling coroutine = " + Thread.currentThread().name)

        }
    }

    var job6 : Job ?= null
    fun exceptionHandlingFinally(){
        runBlocking {
            Log.d(TAG, "working on thread = " + Thread.currentThread().name)

            job6 = launch(Dispatchers.Default) {
                try {
                    for (i in 0..500) {
                        Log.d(TAG, " count = " + i)
                        delay(5)
                    }
                }catch (e:CancellationException){
                    Log.d(TAG, e.message.toString())
                }finally {
                    //delay(100) // don't use scope functions in the finally block

                    Log.d(TAG, " finally starts ------------ ")
                    withContext(NonCancellable){        //Coroutine builder, which will create a new coroutine
                        delay(100)
                        Log.d(TAG, " finally ends ------------------ ")
                    }
                }

            }
            delay(10)
            Log.d(TAG, "working on thread = " + Thread.currentThread().name)
            job6?.cancelAndJoin()
            Log.d(TAG, "Cancelling coroutine = " + Thread.currentThread().name)

        }
    }

    fun withTimeoutFunctions(){
        runBlocking {
            withTimeout(2000){
                try {
                    for (i in 1..100){
                        Log.d(TAG, "i="+i)
                        delay(500)
                    }
                }catch (e:TimeoutCancellationException){
                    Log.d(TAG, "exception handling block")
                }finally {
                    Log.d(TAG, "finally block")
                }
            }

        }
    }

    fun withTimeoutOrNullFunctions(){
        runBlocking {
            val result: String?= withTimeoutOrNull(2000){
                for (i in 1..100){
                    Log.d(TAG, "starts")
                    Log.d(TAG, "i="+i)
                    delay(500)
                }
                "I am done"
            }
            Log.d(TAG, "end without any exception, result =" + result)

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