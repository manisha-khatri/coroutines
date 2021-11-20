package com.example.coroutinesapplication

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class RunBlockingCoroutineBuilderActivityTest{

    @Test
    fun myFirstTest()= runBlocking{
        RunBlockingCoroutineBuilderActivity.myOwnSuspendingFunc()
        Assert.assertEquals(10, 5+5)
    }

}