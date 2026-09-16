package com.dip.aiassistant.utils

object CalculatorUtil {

    fun calculate(expression: String): Double {
        return try {
            val sanitized = expression.replace(" ", "")
            evaluateExpression(sanitized)
        } catch (e: Exception) {
            Double.NaN
        }
    }

    private fun evaluateExpression(expression: String): Double {
        return when {
            expression.isEmpty() -> 0.0
            expression.contains("+") -> {
                val parts = expression.split("+")
                parts.sumOf { evaluateExpression(it) }
            }
            expression.contains("-") && !expression.startsWith("-") -> {
                val parts = expression.split("-", limit = 2)
                evaluateExpression(parts[0]) - evaluateExpression(parts[1])
            }
            expression.contains("*") -> {
                val parts = expression.split("*")
                parts.map { evaluateExpression(it) }.fold(1.0) { a, b -> a * b }
            }
            expression.contains("/") -> {
                val parts = expression.split("/", limit = 2)
                val dividend = evaluateExpression(parts[0])
                val divisor = evaluateExpression(parts[1])
                if (divisor == 0.0) Double.NaN else dividend / divisor
            }
            expression.contains("%") -> {
                val parts = expression.split("%", limit = 2)
                evaluateExpression(parts[0]) % evaluateExpression(parts[1])
            }
            else -> expression.toDoubleOrNull() ?: 0.0
        }
    }

    fun formatResult(result: Double): String {
        return when {
            result.isNaN() -> "Error"
            result.isInfinite() -> "Infinity"
            result == result.toLong().toDouble() -> result.toLong().toString()
            else -> String.format("%.10g", result)
        }
    }
}
