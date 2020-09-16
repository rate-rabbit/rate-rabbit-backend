package com.raterabbit

import io.micronaut.runtime.Micronaut.*

fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("com.raterabbit")
		.start()
}

