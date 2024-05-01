package homework
fun main() {
    var bird = Bird("새")
    var chicken = Chicken("닭")
    var sparrow = Sparrow("참새")
    var pigeon = Pigeon("비둘기")

    bird.fly()
    chicken.fly()
    sparrow.fly()
    pigeon.fly()
}

open class Bird(
    var name: String
) {
    fun fly() {
        println("${name} 날아요~")
    }

}

class Chicken(name: String) : Bird(name) {

}

class Sparrow(name: String) : Bird(name) {

}

class Pigeon(name: String) : Bird(name) {

}