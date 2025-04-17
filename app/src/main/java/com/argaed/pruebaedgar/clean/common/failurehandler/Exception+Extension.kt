package com.araged.failurehandler


val Exception.tag: String get() = javaClass.simpleName

/**
 * Creates a safe message from exception. If exception does not contains a message then, returns
 * an exception tag declared above.
 */
fun Exception.message(): String = message ?: tag