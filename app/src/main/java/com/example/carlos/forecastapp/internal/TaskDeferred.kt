package com.example.carlos.forecastapp.internal

import com.google.android.gms.tasks.Task
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred


/**
 *
 * Converts the task to an instance of [Deferred].
 * So we can call the function that returns the task from a Coroutine.
 *
 */
fun <T> Task<T>.asDeferredAsync(): Deferred<T> {

    val deferred = CompletableDeferred<T>()

    deferred.invokeOnCompletion {
        if (deferred.isCancelled) {
            // Optional, handle Coroutine cancellation here
        }
    }

    this.addOnSuccessListener { result ->
        deferred.complete(result)
    }

    this.addOnFailureListener { exception ->
        deferred.completeExceptionally(exception)
    }

    return deferred

}