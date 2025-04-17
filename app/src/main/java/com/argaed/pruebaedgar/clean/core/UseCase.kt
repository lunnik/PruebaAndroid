package com.araged.clean

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means than any use
 * case in the application should implement this contract).
 *
 * By convention each [UseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 */
abstract class UseCase<out Type, in Params, out Failure> where Type : Any {

    /**
     *
     * @param params
     * @return [Either]
     */
    abstract suspend fun run(params: Params): Either<Failure, Type>

    /**
     *
     * @param params
     * @param onResult
     */
    operator fun invoke(
        params: Params,
        onResult: (Either<Failure, Type>) -> Unit = {}
    ) = runBlocking {
        val job = async { run(params) }
        launch(Dispatchers.IO) { onResult(job.await()) }
    }

    /**
     * Used only to identify that your "in" or "out" type of Use Case its not required.
     */
    object None

}
