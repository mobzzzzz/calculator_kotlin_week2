fun main() {
    println("첫번째 숫자 입력")
    val num1 = readln().toInt()
    println("두번째 숫자 입력")
    val num2 = readln().toInt()
    println("연산자 입력 (+, -, *, /, %)")
    val operator = readln()

    Level2.Calculator(num1, num2, operator)
}

class Level2 {
    class Calculator {
        var num1: Int = 0
        var num2: Int = 0
        var operator: String = ""

        constructor(num1: Int, num2: Int, operator: String) {
            this.num1 = num1
            this.num2 = num2
            this.operator = operator

            when(operator) {
                "+" -> addResult()
                "-" -> subtractResult()
                "*" -> multiplyResult()
                "/" -> divideResult()
                "%" -> modularResult()
            }
        }

        fun addResult() {
            println("$num1 $operator $num2 = ${num1 + num2}")
        }

        fun subtractResult() {
            println("$num1 $operator $num2 = ${num1 - num2}")
        }

        fun multiplyResult() {
            println("$num1 $operator $num2 = ${num1 * num2}")
        }

        fun divideResult() {
            println("$num1 $operator $num2 = ${num1 / num2}")
        }

        fun modularResult() {
            println("$num1 $operator $num2 = ${num1 % num2}")
        }




        fun add(): Int {
            return num1 + num2
        }

        fun subtract(): Int {
            return num1 - num2
        }

        fun multiply(): Int {
            return num1 * num2
        }

        fun divide(): Int {
            return num1 / num2
        }

        fun modular(): Int {
            return num1 % num2
        }

        fun operate(): Int {
            return when (operator) {
                "+" -> add()
                "-" -> subtract()
                "*" -> multiply()
                "/" -> divide()
                "%" -> modular()
                else -> 0
            }
        }
    }
}