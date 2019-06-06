package com.example.carlos.forecastapp.internal

import kotlinx.coroutines.*


/**
 *
 * We extend from the existing lazy Delegated Property.
 * This will let us set up a unit of async work ahead of time,
 * but not actually invoke it until we interact with its result later on.
 *
 * So to do this, there are two things to do. One is to ensure the Promise
 * only executes its work when it has to, by setting the Coroutine
 * start property to lazy. The second is to wrap the object in a lazy-initializer itself.
 *
 */
fun <T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}