import kotlin.math.round

fun main() {
    val calculator = Level4.Calculator()

    println("무슨 계산을 하고 싶으세요? 현재 숫자1: ${calculator.num1} 현재 숫자2: ${calculator.num2}")
    var operator = ""

    while (true) {
        println("(+, -, *, /, %) 끝내려면 Q 입력")
        operator = readln()

        try {
            val operation = when (operator) {
                "+" -> Level4.Calculator.AddOperation()
                "-" -> Level4.Calculator.SubtractOperation()
                "*" -> Level4.Calculator.MultiplyOperation()
                "/" -> Level4.Calculator.DivideOperation()
                "%" -> Level4.Calculator.ModuloOperation()
                "Q" -> {
                    println("계산기 종료 위잉")
                    break
                }
                else -> throw IllegalArgumentException("Unknown operator: $operator")
            }

            println("${calculator.num1} $operator ${calculator.num2} 결과는 ${calculator.operation(operation)} 입니다")
        } catch (e: Throwable) { println("에러 발생!! ${e.message}") }
    }

    // Abstract
    val abstractCalculator = Level4Abstract.Calculator(Level4Abstract.Calculator.AddOperation())
    println(abstractCalculator.operate())

    abstractCalculator.changeOperator(Level4Abstract.Calculator.SubtractOperation())
    println(abstractCalculator.operate())

    abstractCalculator.changeOperator(Level4Abstract.Calculator.MultiplyOperation())
    println(abstractCalculator.operate())

    abstractCalculator.changeOperator(Level4Abstract.Calculator.DivideOperation())
    println(abstractCalculator.operate())

    abstractCalculator.changeOperator(Level4Abstract.Calculator.ModuloOperation())
    println(abstractCalculator.operate())
}

class Level4 {
    class Calculator {
        var num1: Double
        var num2: Double

        init {
            println("계산기에 저장할 첫번째 숫자 입력")
            num1 = readln().toDouble()
            println("계산기에 저장할 두번째 숫자 입력")
            num2 = readln().toDouble()
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

        fun operation(operation: Operation): Double {
            return round(operation.operate(num1, num2) * 100.0) / 100.0
        }
    }
}

class Level4Abstract {
    class Calculator(var operation: Operation) {
        var num1: Double
        var num2: Double

        init {
            println("계산기에 저장할 첫번째 숫자 입력")
            num1 = readln().toDouble()
            println("계산기에 저장할 두번째 숫자 입력")
            num2 = readln().toDouble()
        }

        abstract class Operation {
            abstract fun operate(num1: Double, num2: Double): Double
        }

        class AddOperation: Operation() {
            override fun operate(num1: Double, num2: Double): Double = num1 + num2
        }

        class SubtractOperation: Operation() {
            override fun operate(num1: Double, num2: Double): Double = (num1 - num2)
        }

        class MultiplyOperation: Operation() {
            override fun operate(num1: Double, num2: Double): Double = (num1 * num2)
        }

        class DivideOperation: Operation() {
            override fun operate(num1: Double, num2: Double): Double {
                if (num2 == 0.0) throw IllegalArgumentException("Division by zero")
                return num1 / num2
            }
        }

        class ModuloOperation: Operation() {
            override fun operate(num1: Double, num2: Double): Double {
                if (num2 == 0.0) throw IllegalArgumentException("Modulo by zero")
                return num1 % num2
            }
        }

        fun changeOperator(operation: Operation) {
            this.operation = operation
        }

        fun operate(): Double {
            return round(operation.operate(num1, num2) * 100.0) / 100.0
        }
    }
}


/*
// Abstract 클래스 예시
abstract class Character(var hp: Int) {
    fun takeDamage(damage: Int) {
        hp -= damage
        if (hp <= 0) {
            die()
        }
    }

    abstract fun die()
}

class Warrior(hp: Int) : Character(hp) {
    override fun die() {
        println("Warrior died!")
    }
}

// Interface 예시
interface Damageable {
    var hp: Int
    fun takeDamage(damage: Int)
    fun die()
}

class Mage(override var hp: Int) : Damageable {
    override fun takeDamage(damage: Int) {
        hp -= damage
        if (hp <= 0) {
            die()
        }
    }

    override fun die() {
        println("Mage died!")
    }
}
*/
