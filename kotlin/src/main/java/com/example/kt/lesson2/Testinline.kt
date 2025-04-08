package com.example.kt.lesson2

/**
 * author : felix
 * date : 2024/11/5
 * description :
 */


// 1. inline function
inline fun getName(): String {
    return "Tom"
}

// 2. inline class
class TestInlineObject {
    inline fun getNameByObj(): String {
        return "Tom"
    }
}