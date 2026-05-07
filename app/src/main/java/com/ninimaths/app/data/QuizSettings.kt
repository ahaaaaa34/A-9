package com.ninimaths.app.data

enum class Operation(val symbol: String, val label: String, val id: String) {
    ADD("+", "足し算", "add"),
    SUBTRACT("−", "引き算", "subtract"),
    MULTIPLY("×", "掛け算", "multiply"),
    DIVIDE("÷", "割り算", "divide"),
    MIX("◎", "ミックス", "mix")
}

data class QuizSettings(
    val firstDigits: Int = 3,
    val secondDigits: Int = 1,
    val operation: Operation = Operation.MULTIPLY,
    val questionCount: Int = 100
) {
    val summaryText: String
        get() = "${firstDigits}桁 と ${secondDigits}桁 の ${operation.label} を ${questionCount}問"
}

val QUESTION_COUNT_OPTIONS = listOf(10, 25, 50, 75, 100)
val DIGIT_OPTIONS = listOf(1, 2, 3, 4, 5)
