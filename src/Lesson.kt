/**
 * Main
 *
 */
fun main() {
//    print()
//    condition()
//    iterator()
//    casting()
    FunctionLesson().sumMain()
    FunctionLesson().weatherMain()
    FunctionLesson().scoreMain()
}

/**
 * Print
 *
 */
fun print() {
    println("안녕하세요")
    println("나는 코틀린이 좋아요")
    print("나는 코틀린으로 앱을 만들거예요")
    println("고마워요!")
    println("굿럭!")

    val name: String = "김르탄"

    println(name)
    println("제 이름은 ${name}이에요")

    val dataString = readLine()
    println("나는 ${dataString}를 입력했어요!")

    //Int만 입력한게 아니면 toInt() 에서 NumberFormatException 에러남
    val dataNumber = readLine()!!.toInt()
    val sum = dataNumber + 3
    println("입력은 ${dataNumber}인데 3을 더해서 ${sum}이예요!")
}

/**
 * 2-4 조건식의 사용
 */
fun condition() {
    var koreanScore = readLine()!!.toInt() // 국어점수 입력
    var englishScore = readLine()!!.toInt() // 영어점수 입력
    var mathScore = readLine()!!.toInt() // 수학점수 입력
    var average = (koreanScore + englishScore + mathScore) / 3

    if (average >= 90) {
        println("당신의 등급은 A입니다")
    } else if (average >= 80) {
        println("당신의 등급은 B입니다")
    } else if (average >= 70) {
        println("당신의 등급은 C입니다")
    } else {
        println("당신의 등급은 F입니다")
    }

//    // if-elseif 문을 when으로 바꾸기
//    when {
//        average >= 90 -> println("당신의 등급은 A입니다")
//        average >= 80 -> println("당신의 등급은 B입니다")
//        average >= 70 -> println("당신의 등급은 C입니다")
//        else -> println("당신의 등급은 F입니다")
//    }

//    // When을 표현식으로 사용하는 방법
//    // println 안에 When 으로 골라낸 String 을 넣는다는 느낌
//    // Alt + Enter (수정 제안) 기능으로 같은 기능을 다른 옵션으로 체험해보기
////    println(
////        when {
////            average >= 90 -> "당신의 등급은 A입니다"
////            average >= 80 -> "당신의 등급은 B입니다"
////            average >= 70 -> "당신의 등급은 C입니다"
//            // 이 else 가 없으면 예외가 날 수 있어 코드 실행이 되지 않음
//            else -> "당신의 등급은 F입니다"
//        }
//    )
}

/**
 * 2-5 반복문의 사용
 */
fun iterator() {
    // 내 이름을 10번 출력해요 (for)의 예시입니다
    // 1부터 10까지 변화하는 i변수
    for (i in 1..10) {
        println("${i}번째: 내 이름은 참새")
    }

//    // 두 개의 for문 실행 중 it은 같다
//    // 하지만 a 는 IntRange, b는 Array<Int> 로 사실 다르긴 함
//    val a = (1..10)
//    val b = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

//    for (it in a) {
//        println(it)
//    }
//    for (it in b) {
//        println(it)
//    }

    var i = 0

    while (i < 10) {
        println("${i}번째: 내 이름은 참새")
        i++
    }

    var infos = arrayOf("꿩", "닭", "참새", "오목눈이", "공작새")

    for (info in infos) {

        if (info == "참새") {
            println("찾았다 참새!")
            break
        } else {
            continue
        }
    }
}

/**
 * 2-6 형변환
 */
fun casting() {
    val intString = "123"

    val int = intString.toInt()

    println(int + 123)

    val value: Any = "this is any type value"

    if (value is String) {
        // value가 String 타입이면 자동으로 String으로 형변환된다.
        // 이 if문 안에서 value 는 에디터에서 String 으로 인식함
        println(value.length)
    }
}

/**
 * 2-7 함수
 */
class FunctionLesson {
    fun sumMain() {
        var num1 = readLine()!!.toInt()
        var num2 = readLine()!!.toInt()

        // sum이라는 이름의 메소드를 호출!
        sum(num1, num2)
    }

    private fun sum(num1: Int, num2: Int) {
        var result = num1 + num2
        println("num1과 num2의 덧셈결과는 ${result}입니다.")
    }

    fun weatherMain() {
        // displayInfo라는 이름의 메소드를 호출!
        displayInfo()
    }

    private fun displayInfo() {
        println("오늘의 날씨는 화창합니다")
        println("오늘은 검정색을 조심하세요")
    }

    fun scoreMain() {
//        var myMathScore = readLine()!!.toInt()

        val student1Score = 95
        val student2Score = 27
        val student3Score = 88

        val student1Rank = checkRank(student1Score)
        val student2Rank = checkRank(student2Score)
        val student3Rank = checkRank(student3Score)

        println("학생 1의 등급은: $student1Rank")
        println("학생 2의 등급은: $student2Rank")
        println("학생 3의 등급은: $student3Rank")
    }

    private fun checkRank(score: Int): String {
        return when (score) {
            in 90..100 -> "A"
            in 80..89 -> "B"
            in 70..79 -> "C"
            else -> "D"
        }
    }

        // Kotlin 컨벤션을 따르지 않은 형태
//    fun checkRank(score: Int): String {
//        if (score >= 90) {
//            return "A"
//        } else if (score >= 80) {
//            return "B"
//        } else if (score >= 70) {
//            return "C"
//        } else {
//            return "D"
//        }
//    }
}