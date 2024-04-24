import kotlin.math.round

fun main() {
    val calculator = Level3.Calculator()

    println("무슨 계산을 하고 싶으세요? 현재 숫자1: ${calculator.num1} 현재 숫자2: ${calculator.num2}")
    var operator = ""

    while (true) {
        println("(+, -, *, /, %) 끝내려면 Q 입력")
        operator = readln()

        try {
            val operationResult = when (operator) {
                "+" -> calculator.operation(Level3.Calculator.AddOperation())
                "-" -> calculator.operation(Level3.Calculator.SubtractOperation())
                "*" -> calculator.operation(Level3.Calculator.MultiplyOperation())
                "/" -> calculator.operation(Level3.Calculator.DivideOperation())
                "%" -> calculator.operation(Level3.Calculator.ModuloOperation())
                "Q" -> {
                    println("계산기 종료 위잉")
                    break
                }
                else -> throw IllegalArgumentException("Unknown operator: $operator")
            }

            println("${calculator.num1} $operator ${calculator.num2} 결과는 $operationResult 입니다")
        } catch (e: Throwable) { println("에러 발생!! ${e.message}") }
    }
}

private class Level3 {
    class Calculator {
        var num1: Double
        var num2: Double

        init {
            println("계산기에 저장할 첫번째 숫자 입력")
            num1 = readln().toDouble()
            println("계산기에 저장할 두번째 숫자 입력")
            num2 = readln().toDouble()
        }

        class AddOperation {
            fun operate(num1: Double, num2: Double): Double = num1 + num2
        }

        class SubtractOperation {
            fun operate(num1: Double, num2: Double): Double = (num1 - num2)
        }

        class MultiplyOperation {
            fun operate(num1: Double, num2: Double): Double = (num1 * num2)
        }

        class DivideOperation {
            fun operate(num1: Double, num2: Double): Double {
                if (num2 == 0.0) throw IllegalArgumentException("Division by zero")
                return num1 / num2
            }
        }

        class ModuloOperation {
            fun operate(num1: Double, num2: Double): Double {
                if (num2 == 0.0) throw IllegalArgumentException("Modulo by zero")
                return num1 % num2
            }
        }

        fun operation(operation: AddOperation): Double {
            return round(operation.operate(num1, num2) * 100.0) / 100.0
        }

        fun operation(operation: SubtractOperation): Double {
            return round(operation.operate(num1, num2) * 100.0) / 100.0
        }

        fun operation(operation: MultiplyOperation): Double {
            return round(operation.operate(num1, num2) * 100.0) / 100.0
        }

        fun operation(operation: DivideOperation): Double {
            return round(operation.operate(num1, num2) * 100.0) / 100.0
        }

        fun operation(operation: ModuloOperation): Double {
            return round(operation.operate(num1, num2) * 100.0) / 100.0
        }
    }
}
