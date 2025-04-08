package com.example.kt.lesson1

/**
 * author : felix
 * date : 2024/10/17
 * description :
 */

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Proxy

internal inline fun <reified T : Any> noOpDelegate(): T {
    val javaClass = T::class.java
    return Proxy.newProxyInstance(
        javaClass.classLoader, arrayOf(javaClass), NO_OP_HANDLER
    ) as T
}

private val NO_OP_HANDLER = InvocationHandler { _, _, _ ->
    // no op
}