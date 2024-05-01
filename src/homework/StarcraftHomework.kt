package homework

fun main() {
    var zealot = Zealot(50, 10, 3, "질럿", "프로토스")
    var zergling = Zergling(30, 5, 1, "저글링", "저그")
    var marine = Marine(40, 7, 2, "마린", "테란")

    var fenix = Zealot(100, 30, 5, "피닉스", "프로토스")
    var devouringOne = Zergling(80, 15, 3, "디바우링 원", "저그")
    var jimRaynor = Marine(85, 22, 4, "짐 레이너", "테란")

//    unitInfo(zealot)
//    unitInfo(zergling)
//    unitInfo(marine)
//    unitInfo(fenix)
//    unitInfo(devouringOne)
//    unitInfo(jimRaynor)

    unitInfo(devouringOne)
    devouringOne.defense(fenix.attack())
    unitInfo(devouringOne)
}

fun unitInfo(zealot: Zealot) {
    println("이름: ${zealot.name}")
    println("종족: ${zealot.race}")
    println("현재 HP: ${zealot.hp}")
    println("공격력: ${zealot.attack}")
    println("방어력: ${zealot.defense}")
}

fun unitInfo(zergling: Zergling) {
    println("이름: ${zergling.name}")
    println("종족: ${zergling.race}")
    println("현재 HP: ${zergling.hp}")
    println("공격력: ${zergling.attack}")
    println("방어력: ${zergling.defense}")
}

fun unitInfo(marine: Marine) {
    println("이름: ${marine.name}")
    println("종족: ${marine.race}")
    println("현재 HP: ${marine.hp}")
    println("공격력: ${marine.attack}")
    println("방어력: ${marine.defense}")
}

class Zealot(
    var hp: Int,
    var attack: Int,
    var defense: Int,
    var name: String,
    var race: String
) {
    fun walk() {
        println("$name 걷는 중")
    }

    fun attack(): Int {
        return this.attack
    }

    fun defense(attack: Int) {
        this.hp = this.hp - attack + defense
    }

    fun infected(race: String): String {
        this.race = race

        return this.race
    }
}

class Zergling(
    var hp: Int,
    var attack: Int,
    var defense: Int,
    var name: String,
    var race: String
) {
    fun walk() {
        println("$name 걷는 중")
    }

    fun attack(): Int {
        return this.attack
    }

    fun defense(attack: Int) {
        this.hp = this.hp - attack + defense
    }

    fun infected(race: String): String {
        this.race = race

        return this.race
    }
}

class Marine(
    var hp: Int,
    var attack: Int,
    var defense: Int,
    var name: String,
    var race: String
) {
    fun walk() {
        println("$name 걷는 중")
    }

    fun attack(): Int {
        return this.attack
    }

    fun defense(attack: Int) {
        this.hp = this.hp - attack + defense
    }

    fun infected(race: String): String {
        this.race = race

        return this.race
    }
}