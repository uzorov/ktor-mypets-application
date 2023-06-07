package com.example.utils

import com.example.data.database.employees.Employees

fun String.isPhoneNumberValid(): Boolean {
    return ((this.length == 12 && this.startsWith("+7")) || (this.length == 11 && this.startsWith("8")))
            && this.drop(1).all { it.isDigit() }
}
fun String.isPasswordValid(): Boolean {
    return this.length > 6
            && this.any { it.isDigit() }
            && this.any { it.isLetter() }
            && this.any { !it.isLetterOrDigit() }
}


fun String.isAdmin(): Boolean {
    val employeesNumbers = Employees.fetchAllEmployeePhoneNumbers()
    return this in employeesNumbers
}
