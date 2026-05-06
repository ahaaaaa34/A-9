package com.ninimaths.app.data

enum class Operation(val symbol: String, val label: String) {
    ADD("+", "足し算"),
    SUBTRACT("-", "引き算"),
    MULTIPLY("×", "掛け算"),
    DIVIDE("÷", "割り算"),
    MIX("◎", "ミックス")
}

data class QuizSettings(
    val firstDigits: Int = 3,
    val secondDigits: Int = 1,
    val operation: Operation = Operation.MULTIPLY,
    val questionCount: Int = 100
) {
    val summaryText: String
        get() {
            val opLabel = operation.label
            return "${firstDigits}桁 と ${secondDigits}桁 の $opLabel を ${questionCount}問"
        }
}

val QUESTION_COUNT_OPTIONS = listOf(10, 25, 50, 75, 100)
val DIGIT_OPTIONS = listOf(1, 2, 3, 4, 5)
