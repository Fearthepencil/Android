package com.example.levi9application.common

object Constants {
    const val email_key = "_email"
    const val name_key = "_name"
    const val password_key = "_password"
    const val email_regex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})\$"
    const val password_regex = "^(?=.*[A-Za-z])(?=.*\\d).{6,}$"
    const val name_regex = "^[a-zA-Z]+$"
}