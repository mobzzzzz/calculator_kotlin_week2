fun main() {
    val calculator = Calculator()

    println("숫자 두개 입력")
    val num1 = readln().toInt()
    val num2 = readln().toInt()
    println("연산자 입력 (+, -, *, /)")
    val operator = readln()

    when (operator) {
        "+" -> println(calculator.add(num1, num2))
        "-" -> println(calculator.subtract(num1, num2))
        "*" -> println(calculator.multiply(num1, num2))
        "/" -> println(calculator.divide(num1, num2))
    }
}

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