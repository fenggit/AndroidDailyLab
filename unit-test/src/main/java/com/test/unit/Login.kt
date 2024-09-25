package com.test.unit

import android.util.Log

/**
 * author : felix
 * date : 2024/2/29
 * description :
 */
class Login(var info: UserInfo) {
    fun login() {
        Log.e("Login", "login: ${info.getName()} ${info.getAge()}")
    }

    fun logout() {
        Log.e("Login", "logout: ${info.getName()} ${info.getAge()}")
    }

    fun clear(){
        Log.e("Login", "clear: ${info.getName()} ${info.getAge()}")
    }
}