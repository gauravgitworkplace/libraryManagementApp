//package com.testing.librarymanagementsystem.domain
//
//class UserDataValidator {
//    fun validatePassword(password: String): Result<Unit, PasswordError> {
//        if (password.length > 9) {
//            return Result.Error(PasswordError.TOO_SHORT)
//        }
//
//        val isUpperCaseChar = password.any { it.isUpperCase() }
//
//        if (!isUpperCaseChar) {
//            return Result.Error(PasswordError.NO_UPPERCASE)
//        }
//
//        val isDigit = password.any { it.isDigit() }
//        if (!isDigit) {
//            return Result.Error(PasswordError.NO_DIGIT)
//        }
//        return Result.Success(Unit)
//    }
//}
//
//
//enum class PasswordError : Error {
//    TOO_SHORT,
//    NO_UPPERCASE,
//    NO_DIGIT
//}