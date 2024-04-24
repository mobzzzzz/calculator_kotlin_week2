fun main() {

    val calculator = Level2.Calculator()


    println("무슨 계산을 하고 싶으세요? 현재 숫자1: ${calculator.num1} 현재 숫자2: ${calculator.num2}")
    println("(+, -, *, /, %)")
    val operator = readln()

    when (operator) {
        "+" -> calculator.showAddResult()
        "-" -> calculator.showSubsctractResult()
        "*" -> calculator.showMultiplyResult()
        "/" -> calculator.showDivideResult()
        "%" -> calculator.showModularResult()
    }
}

class Level2 {
    class Calculator {
        var num1: Int
        var num2: Int

        init {
            println("계산기에 저장할 첫번째 숫자 입력")
            num1 = readln().toInt()
            println("계산기에 저장할 두번째 숫자 입력")
            num2 = readln().toInt()
        }

        fun showAddResult() {
            println("$num1 + $num2 = ${num1 + num2}")
        }

        fun showSubsctractResult() {
            println("$num1 - $num2 = ${num1 - num2}")
        }

        fun showMultiplyResult() {
            println("$num1 * $num2 = ${num1 * num2}")
        }

        fun showDivideResult() {
            println("$num1 / $num2 = ${num1 / num2}")
        }

        fun showModularResult() {
            println("$num1 % $num2 = ${num1 % num2}")
        }
    }
}