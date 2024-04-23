class Level1 { // 이건 껍데기임
    class Calculator {
        fun add(a: Int, b: Int): Int {
            return a + b
        }

        fun subtract(a: Int, b: Int): Int {
            return a - b
        }

        fun multiply(a: Int, b: Int): Int {
            return a * b
        }

        fun divide(a: Int, b: Int): Int {
            return a / b
        }
    }
}

fun main() {
    println("첫번째 숫자 입력")
    val num1 = readln().toInt()
    println("두번째 숫자 입력")
    val num2 = readln().toInt()
    println("연산자 입력 (+, -, *, /)")
    val operator = readln()

    println(
        "$num1 $operator $num2 = ${
            when (operator) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "*" -> num1 * num2
                "/" -> num1 / num2
                else -> "에러입니다"
            }
        }"
    )

    val calculator = Level1.Calculator()

    println(
        if (operator == "+") calculator.add(num1, num2)
        else if (operator == "-") calculator.subtract(num1, num2)
        else if (operator == "*") calculator.multiply(num1, num2)
        else if (operator == "/") calculator.divide(num1, num2)
        else "에러입니다"
    )
}

