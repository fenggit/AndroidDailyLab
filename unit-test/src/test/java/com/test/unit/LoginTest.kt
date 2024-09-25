package com.test.unit

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

/**
 * author : felix
 * date : 2024/2/29
 * description :
 */
class LoginTest {
    lateinit var login: Login

    @Before
    fun setUp() {
        val info = Mockito.mock(UserInfo::class.java)
        login = Login(info)
    }

    @Test
    fun login() {
        login.login()
        Mockito.verify(login).login()
    }

    @Test
    fun logout() {
        login.logout()
        Mockito.verify(login).logout()
    }

    @After
    fun release() {
        login.clear()
    }
}