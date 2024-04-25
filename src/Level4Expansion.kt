fun main() {
    println("계산기입니다.")

    while (true) {
        println("계산 식을 입력해주세요. Q를 입력하면 종료됩니다.(괄호 및 사칙연산 우선순위 기능은 제공되지 않습니다.)")
        try {
            val input = readln()
            if (input == "Q") {
                println("이용해주셔서 감사합니다.")
                break
            }

            Level4Expansion.Calculator.operateWithPrint(input)
        } catch (e: Throwable) {
            println(e.message)
        }
    }
}

class Level4Expansion {
    class Calculator {
        companion object {
            fun operateWithPrint(input: String) {
                var result = 0.0

                Regex("""\d+|\S""").findAll(input).toList().map { it.value }.let { matchResults ->
                    // Guard clause
                    if (matchResults.count() < 3) {
                        throw Exception("수가 적어 연산이 불가능합니다.")
                    }
                    if (!matchResults.last().single().isDigit()) {
                        throw Exception("수식이 완성되지 않았습니다.")
                    }

                    result = matchResults.first().toDouble()

                    matchResults.forEachIndexed { index, value ->
                        // 피연산자는 패스
                        if (index % 2 == 0) {
                            return@forEachIndexed
                        }

                        val firstNum = result
                        val secondNum = matchResults[index + 1].toDouble()
                        result = when (value) {
                            "+" -> firstNum + secondNum
                            "-" -> firstNum - secondNum
                            "*" -> firstNum * secondNum
                            "/" -> firstNum / secondNum
                            "%" -> firstNum % secondNum
                            else -> throw Exception("${value}는 틀린 연산자입니다.")
                        }
                    }
                }

                println("$input 계산 결과는 $result 입니다.")
            }
        }
    }
}
