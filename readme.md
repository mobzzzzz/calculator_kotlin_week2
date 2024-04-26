# Kotlin 계산기

## ✨ 240422-240426 내일배움캠프 2주차에 만든 계산기

과제에 맞추어 각기 다른 스타일로 작성해 총 6개의 파일로 분리되어 있습니다.
<br/>
<h3>목차</h3>
> 1. [Level 1 - 간단한 연산 Class](#level-1)
> 2. [Level 2 - 나머지를 추가하고 Class 구조 개선](#level-2)
> 3. [Level 3 - 연산을 분리하고 오버로딩으로 구분](#level-3)
> 4. [Level 4 - 연산간의 관계 확립](#level-4)
> 5. [기능 확장 - 문자열로 수식 받아 연산하기](#기능-확장---문자열로-수식-받아-연산하기)
> 6. [기능 확장 - 수식을 후위 표현식으로 변경해 연산하기](#기능-확장---수식을-후위-표현식으로-변경해-연산하기)

<br/>
<br/>
<br/>
<br/>

### Level 1

>- 더하기, 빼기, 나누기, 곱하기 연산을 수행할 수 있는 Calculator 클래스를 만들기
>- 생성한 클래스를 이용하여 연산을 진행하고 출력하기

<details><summary>코드 보기</summary>

[Level1.kt](src/Level1.kt)
```kotlin
class Calculator {
    fun add(a: Int, b: Int): Int {
        return a + b
    }

    /* fun subtract(a: Int, b: Int): Int ... */
}
```
</details>

---

### Level 2

>- Lv1에서 만든 Calculator 클래스에 “나머지 연산”이 가능하도록 코드를 추가하고, 연산 진행 후 출력

추가로 필드 사용을 위해 숫자를 Calculator로 이동, 반복적으로 연산 선택 가능 

<details><summary>코드 보기</summary>

[Level2.kt](src/Level2.kt)
```kotlin
// 연산 기능 구현부
class Calculator {
  var num1: Int
  var num2: Int

  init {
    println("계산기에 저장할 첫번째 숫자 입력")
    num1 = readln().toInt()
    println("계산기에 저장할 두번째 숫자 입력")
    num2 = readln().toInt()
  }

  fun showAddResult() { println("$num1 + $num2 = ${num1 + num2}") }

  /* fun showSubtractResult() { ... } */

  fun operate(operator: String) {
    when (operator) {
      "+" -> showAddResult()
      /* ... */
    }
  }
}
```
```kotlin
// 출력부 (fun main)
val calculator = Level2.Calculator()
var operator = ""

while (operator != "Q") {
  println("(+, -, *, /, %) 끝내려면 Q 입력")
  operator = readln()

  calculator.operate(operator)
}
```
</details>

---

### Level 3

>- 아래 각각 클래스들을 만들고 클래스간의 관계를 고려하여 Calculator 클래스와 관계 맺기
>  - AddOperation(더하기)
>  - SubstractOperation(빼기)
>  - MultiplyOperation(곱하기)
>  - DivideOperation(나누기)
>  - 관계를 맺은 후 필요하다면 Calculator 클래스의 내부코드를 변경

단일 표현식으로 코드를 간소화하고 과제 구현상 관계가 없기 때문에 오버로딩으로 함수를 구별

간단한 예외처리 추가

<details><summary>코드 보기</summary>

[Level3.kt](src/Level3.kt)
```kotlin
// 구현부
class Calculator {
  class AddOperation {
    fun operate(num1: Double, num2: Double): Double = num1 + num2
  }
  
  /* class SubtractOperation { ... } */

  fun operation(operation: AddOperation): Double = round(operation.operate(num1, num2) * 100.0) / 100.0

  /* fun operation(operation: SubtractOperation): Double = ... */
}
```

```kotlin
// 출력부
try {
  val operationResult = when (operator) {
    "+" -> calculator.operation(Level3.Calculator.AddOperation())
    /* "-" -> { calculator.operation( ... ) } */
    else -> throw IllegalArgumentException("Unknown operator: $operator")
  }

  println("${calculator.num1} $operator ${calculator.num2} 결과는 $operationResult 입니다")
} catch (e: Throwable) { println("에러 발생!! ${e.message}") }
```

```kotlin
throw IllegalArgumentException("Division by zero")
throw IllegalArgumentException("Modulo by zero")
```
</details>

---

### Level 4

> - 아래 연산 클래스들을 AbstractOperation라는 클래스명으로 만들어 사용하여 추상 클래스로 정의하고 Calculator 클래스의 내부 코드를 변경합니다.
>  - AddOperation(더하기)
>  - SubtractOperation(빼기)
>  - MultiplyOperation(곱하기)
>  - DivideOperation(나누기)

Abstract를 통한 추상화도 작성하긴 했지만 부모를 참조할 일이 없어 Interface를 메인으로 진행

<details><summary>코드 보기</summary>

[Level4.kt](src/Level4.kt)
```kotlin
class Calculator {
  interface Operation { fun operate(num1: Double, num2: Double): Double }
  
  class AddOperation: Operation { override fun operate(num1: Double, num2: Double): Double = num1 + num2 }
  /* class SubtractOperation: Operation { ... } */
  
  fun operation(operation: Operation): Double = round(operation.operate(num1, num2) * 100.0) / 100.0
}
```
</details>

---

### 기능 확장 - 문자열로 수식 받아 연산하기

> - 문자열로 입력받은 수식을 한 번에 계산하기
> - 앞에서부터 순서대로 연산할 뿐이라 연산자 우선순위, 괄호 처리는 되어있지 않음

<details><summary>코드 보기</summary>

[Level4Expansion.kt](src/Level4Expansion.kt)
```kotlin
fun operateWithPrint(input: String) {
  var result = 0.0

  Regex("""\d+|\S""").findAll(input).toList().map { it.value }.let { matchResults ->
    // Guard clause
    if (matchResults.count() < 3) { throw Exception("수가 적어 연산이 불가능합니다.") }
    if (!matchResults.last().single().isDigit()) { throw Exception("수식이 완성되지 않았습니다.") }

    result = matchResults.first().toDouble()

    matchResults.forEachIndexed { index, value ->
      // 피연산자는 패스
      if (index % 2 == 0) { return@forEachIndexed }

      val firstNum = result
      val secondNum = matchResults[index + 1].toDouble()
      result = when (value) {
        "+" -> firstNum + secondNum
        /* "-" -> { ... } */
        else -> throw Exception("${value}는 틀린 연산자입니다.")
      }
    }
  }

  println("$input 계산 결과는 $result 입니다.")
}
```
</details>

---

### 기능 확장 - 수식을 후위 표현식으로 변경해 연산하기

> - 연산자 우선순위 및 괄호를 포함해 수식 계산이 가능한 후위표현식 계산기 만들기
> - 각 함수의 주석을 Javadoc 형태로 작성

<details><summary>코드 보기</summary>

[Level4PostFix.kt](src/Level4PostFix.kt)
```kotlin
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
            "*", "/", "%", "(" -> operatorList.add(value)
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
    while (operatorList.isNotEmpty()) { tempPostFixList.add(operatorList.removeLast()) }

    return tempPostFixList
}
```
```kotlin
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
                        /* "-" -> { ... } */
                        else -> throw Exception("연산자 처리 에러")
                    }
                )
            }
            else -> stackLikeForCalculate.add(it.toDouble())
        }
    }

    return stackLikeForCalculate.removeLast()
}
```
</details>

---

### 추가할 기능

- Function 매개변수 안정성을 높이기 위해 Interface 설계 또는 연산자 Enum class 관리하기 (like swift enum)



### 개발 환경

- Language : Kotlin 1.9
- IDE : IntelliJ
- JDK : openjdk-22.0.1
