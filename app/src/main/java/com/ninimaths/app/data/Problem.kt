package com.ninimaths.app.data

import kotlin.math.pow

data class Problem(
    val operandA: Int,
    val operandB: Int,
    val operation: Operation,
    val answer: Int
) {
    val displayA: String get() = operandA.toString()
    val displayB: String get() = operandB.toString()
    val displayOp: String get() = operation.symbol
}

fun generateProblem(settings: QuizSettings): Problem {
    val maxFirst = (10.0.pow(settings.firstDigits) - 1).toInt()
    val minFirst = (10.0.pow(settings.firstDigits - 1)).toInt()
    val maxSecond = (10.0.pow(settings.secondDigits) - 1).toInt()
    val minSecond = (10.0.pow(settings.secondDigits - 1)).toInt()

    val op = if (settings.operation == Operation.MIX) {
        listOf(Operation.ADD, Operation.SUBTRACT, Operation.MULTIPLY).random()
    } else {
        settings.operation
    }

    return when (op) {
        Operation.ADD -> {
            val a = (minFirst..maxFirst).random()
            val b = (minSecond..maxSecond).random()
            Problem(a, b, op, a + b)
        }
        Operation.SUBTRACT -> {
            val a = (minFirst..maxFirst).random()
            val bMax = minOf(maxSecond, a)
            val b = if (minSecond <= bMax) (minSecond..bMax).random() else 0
            Problem(a, b, op, a - b)
        }
        Operation.MULTIPLY -> {
            val a = (minFirst..maxFirst).random()
            val b = (minSecond..maxSecond).random()
            Problem(a, b, op, a * b)
        }
        Operation.DIVIDE -> {
            val b = (minSecond..maxSecond).random()
            val minQuotient = maxOf(1, minFirst / b)
            val maxQuotient = maxFirst / b
            val quotient = if (minQuotient <= maxQuotient) (minQuotient..maxQuotient).random() else 1
            Problem(quotient * b, b, op, quotient)
        }
        Operation.MIX -> {
            val a = (minFirst..maxFirst).random()
            val b = (minSecond..maxSecond).random()
            Problem(a, b, Operation.ADD, a + b)
        }
    }
}

fun generateProblems(settings: QuizSettings): List<Problem> {
    return List(settings.questionCount) { generateProblem(settings) }
}
