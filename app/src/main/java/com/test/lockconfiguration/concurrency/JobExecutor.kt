package com.test.lockconfiguration.concurrency

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import java.util.PriorityQueue
import java.util.concurrent.Executors

object JobExecutor {
    private val jobs  = PriorityQueue<suspend () -> Unit>()
    private lateinit var callback: ((Pair<Any?, Throwable?>) -> Unit)
    private val mutex = Mutex()
    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, exception ->
            exception.printStackTrace()
            callback.invoke(Pair(null, exception))
        }
    val dispatcher = Executors
        .newFixedThreadPool(20)
        .asCoroutineDispatcher()
    val scope: CoroutineScope
        get() {
            return CoroutineScope(Job() + coroutineExceptionHandler)
        }

    private fun run() {
        //  if(mutex.isLocked) return
    synchronized(this) {
        var job = jobs.poll()
        while (job != null) {
            val tempJob = job
            scope.launch(dispatcher) {
                tempJob.invoke()
            }
            job = jobs.poll()
        }
    }
       // execute(runTest)

    }
    suspend fun runTest(name : String, arg : Array<Any>){

    }

    fun execute(task: suspend () -> Unit) /*: ReceiveChannel<Any>*/ {
       //val f = runTest()

        jobs.add(task)
        run()

    }

    fun registerForThrowable(t: (Throwable?) -> Unit) {
//        callback = t
    }
}
