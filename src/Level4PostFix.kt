fun main() {
    println("계산기입니다.")

    while (true) {
        println("계산 식을 입력해주세요. Q를 입력하면 종료됩니다.")
        try {
            val input = readln()
            if (input == "Q") {
                println("이용해주셔서 감사합니다.")
                break
            }

            println("$input 연산 결과는 ${Level4PostFix.Calculator().resultFromInput(input)}")
        } catch (e: Throwable) {
            println(e.message)
        }
    }
}

class Level4PostFix {
    class Calculator {
        /**
         * 입력받은 input 을 처리하고 반환함
         *
         */
        fun resultFromInput(input: String): Double {
            if (input.isEmpty()) { throw IllegalArgumentException("연산식을 입력해 주세요.") }
            if (input.trim().last().let { !it.isDigit() && it != ')' }) { throw IllegalArgumentException("연산식을 제대로 지어주세요.") }

            return this.calculateWithPostFix(this.findNumberOrOperators(input).toPostFixList())
        }

        /**
         * 정규표현식으로 숫자 또는 문자만 찾아 그 값을 String으로만 저장해 List<String> 으로 반환
         *
         * @return 입력 받은 값을 숫자, 연산자만 저장한 List
         */
        private fun findNumberOrOperators(input: String): List<String> {
            return Regex("""\d+|\S""").findAll(input).map { it.value }.toList()
        }

        /**
         * 중위 표현식이 담긴 List<String> 을 후위 표현식으로 바꾼 List<String> 으로 반환하는 계산용 함수
         * 자료구조 참고: https://chanos.tistory.com/entry/%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0-%EC%8A%A4%ED%83%9D-%EC%98%88%EC%A0%9C-%EC%A4%91%EC%9C%84-%ED%91%9C%EA%B8%B0infix%EB%A5%BC-%ED%9B%84%EC%9C%84-%ED%91%9C%EA%B8%B0postfix%EB%A1%9C-%EB%B3%80%ED%99%98%ED%95%98%EA%B8%B0
         *
         * @return 중위 표기식을 후위 표기식으로 바꾼 List
         */
        private fun List<String>.toPostFixList(): List<String> {
            // 후위 연산 저장중인 List, 최종적으로 반환됨
            val tempPostFixList = mutableListOf<String>()
            // 우선 순위에 맞춰서 관리중인 Stack
            val operatorList = mutableListOf<String>()

            if (this.count() < 3) throw IllegalArgumentException("연산 식이 부족합니다")

            // 숫자와 연산자를 적절하게 tempPostFixList, operatorList 에 저장함
            this.forEach { value ->
                when (value) {
                    // 우선순위 높은 연산자는 그냥 바로 추가
                    "*", "/", "%", "(" -> {
                        operatorList.add(value)
                    }
                    // 우선순위가 떨어지는 연산자는
                    // 1. 연산자 리스트가 비어있으면 바로 추가
                    // 2. 연산자 리스트가 차있으면 처음 만나는 고우선순위 연산자를 후위 표기 리스트로 빼고 그 자리를 채움
                    "+", "-" -> {
                        if (operatorList.isEmpty()) operatorList.add(value)
                        else {
                            if (operatorList.last().let { it == "*" || it == "/" || it == "%" }) {
                                tempPostFixList.add(operatorList.removeLast())
                            }

                            operatorList.add(value)
                        }
                    }
                    // 괄호가 닫히면 열린 괄호를 찾을 때 까지의 연산자를 다 후위 표기 리스트로 넣어버림
                    ")" -> {
                        while (operatorList.last() != "(") {
                            tempPostFixList.add(operatorList.removeLast())
                        }
                        // 처리하고 남은 "(" 도 제거함
                        operatorList.removeLast()
                    }

                    else -> tempPostFixList.add(value)
                }
            }

            // operatorList에 남아있던 마지막 연산자들을 추가해줌
            while (operatorList.isNotEmpty()) {
                tempPostFixList.add(operatorList.removeLast())
            }

            return tempPostFixList
        }

        /**
         * 후위 표현식을 계산합니다.
         *
         * @param postFixList: 후위 표현식으로 숫자, 연산자를 저장한 리스트
         * @return 후위 표현식을 계산 완료한 값
         */
        private fun calculateWithPostFix(postFixList: List<String>): Double {
            val tempPostFixList = postFixList.toMutableList()
            val stackLikeForCalculate = mutableListOf<Double>()

            tempPostFixList.forEach {
                when (it) {
                    "+", "-", "*", "/", "%" -> {
                        val num1 = stackLikeForCalculate.removeLast()
                        val num2 = stackLikeForCalculate.removeLast()

                        stackLikeForCalculate.add(
                            when (it) {
                                "+" -> num1 + num2
                                "-" -> num1 - num2
                                "*" -> num1 * num2
                                "/" -> {
                                    if (num2 == 0.0) throw Exception("Division by zero")
                                    num1 / num2
                                }
                                "%" -> {
                                    if (num2 == 0.0) throw Exception("Modulo by zero")
                                    num1 % num2
                                }
                                else -> throw Exception("연산자 처리 에러")
                            }
                        )
                    }
                    else -> stackLikeForCalculate.add(it.toDouble())
                }
            }

            return stackLikeForCalculate.removeLast()
        }
    }
}
