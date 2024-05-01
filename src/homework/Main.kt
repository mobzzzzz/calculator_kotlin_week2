package homework

fun main() {
    lions()
}

fun lions() {
    var lion = Lion(1000, 4, "brown", 300)
    var lionRed = Lion(1000, 4, "red", 300)
    var defaultLion = Lion()

    info(lion)
    info(lionRed)
    info(defaultLion)
}

fun info(lion: Lion) {
    println(lion.furCount)
    println(lion.legCount)
    println(lion.color)
    println(lion.weight)
}

class Lion(var furCount: Int,
           var legCount: Int,
           var color: String,
           var weight: Int) {
    constructor() : this(0, 0, "default", 0)
}


// 질럿, 저글링, 마린
//체력 공격력 방어력
//걷기 공격하기 공격받기
//생성자로 체공방 조절하기 (영웅유닛처럼..)
// 매개변수 유무, 반환 유무로 4가지 행위 모두 만들기