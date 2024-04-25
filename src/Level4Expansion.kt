fun main() {
    try {
        Level4Expansion.Calculator()
    } catch (e: Throwable) {
        e.printStackTrace()
    }
}

// 문자열 연산 처리 (전위 표기나 후위 표기법 계산은 나중에)
class Level4Expansion {
    class Calculator {
        init {
            initCalculator()
        }

        private fun initCalculator() {
            println("계산기입니다.")
            println("계산 식을 입력해주세요. (괄호 및 사칙연산 우선순위 기능은 제공되지 않습니다.)")
            val input = readln()

            // 숫자 저장소 (연산 처리 전후 전부)
            var result = 0.0
            Regex("""\d+|\S""").findAll(input).toList().map { it.value }.let { matchResults ->

                // Guard clause
                if (matchResults.count() <= 3) {
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
                        else -> throw IllegalArgumentException("${value}는 틀린 연산자입니다.")
                    }

                }
            }

            println("$input 계산 결과는 $result 입니다.")
        }
    }
}
