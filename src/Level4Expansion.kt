fun main() {
    try {
        Level4Expansion.Calculator()
    } catch (e: Throwable) { e.printStackTrace() }
}

class Level4Expansion {
    class Calculator {
        init {
            initCalculator()
        }

        private fun initCalculator() {
            println("계산기입니다.")
            println("계산 식을 입력해주세요.")
            val input = readln()

            // 숫자 저장소 (연산 처리 전후 전부)
            val numbersInResult = mutableListOf<Double>()
            Regex("""\d+|\S""").findAll(input).forEach { result ->
                // 숫자면 저장소에 담고 return
                result.value.toDoubleOrNull()?.let { numbersInResult.add(it); return }

                val firstNum = numbersInResult.removeLast()
                val secondNum = numbersInResult.removeLast()
                val operator = result.value

                numbersInResult.add(when (operator) {
                    "+" -> firstNum + secondNum
                    "-" -> firstNum - secondNum
                    "*" -> firstNum * secondNum
                    "/" -> firstNum / secondNum
                    "%" -> firstNum % secondNum
                    else -> throw IllegalArgumentException("${operator}는 틀린 연산자입니다.")
                })
            }

            println(numbersInResult.removeLast())
        }

        interface Operation {
            fun operate(num1: Double, num2: Double): Double
        }

        class AddOperation: Operation {
            override fun operate(num1: Double, num2: Double): Double = num1 + num2
        }

        class SubtractOperation: Operation {
            override fun operate(num1: Double, num2: Double): Double = (num1 - num2)
        }

        class MultiplyOperation: Operation {
            override fun operate(num1: Double, num2: Double): Double = (num1 * num2)
        }

        class DivideOperation: Operation {
            override fun operate(num1: Double, num2: Double): Double {
                if (num2 == 0.0) throw IllegalArgumentException("Division by zero")
                return num1 / num2
            }
        }

        class ModuloOperation: Operation {
            override fun operate(num1: Double, num2: Double): Double {
                if (num2 == 0.0) throw IllegalArgumentException("Modulo by zero")
                return num1 % num2
            }
        }
    }
}
