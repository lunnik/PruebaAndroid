package com.araged.failurehandler

fun <T> tryOrNull(throwableAction: () -> T): T? =
	try {
		throwableAction()
	} catch (ignore: Exception) {
		null
	}