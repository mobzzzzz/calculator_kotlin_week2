package Homework

import kotlin.Exception

fun main() {

}

class Answer1 {
    fun main() {
        println("메뉴를 입력해주세요.")
        println("1. 게임 시작")
        println("2. 게임 종료")

        val selectedMenu = readLine()!!.toInt()

        when(selectedMenu) {
            1 -> startGame()
            2 -> endGame()
        }
    }

    fun startGame() {
        println("게임이 시작되었습니다")
    }

    fun endGame() {
        println("게임이 종료되었습니다")
    }
}

class Answer2 {
    fun main() {
        println("메뉴를 입력해주세요.")
        println("1. 게임 시작")
        println("2. 게임 종료")

        val selectedMenu = readLine()!!.toInt()

        when(selectedMenu) {
            1 -> startGame()
            2 -> endGame()
        }
    }

    fun startGame() {
        println("게임이 시작되었습니다")
    }

    fun endGame() {
        println("게임이 종료되었습니다")
    }
}

class Answer3 {
    fun main() {
        println("메뉴를 입력해주세요.")
        println("1. 게임 시작")
        println("2. 캐릭터 추가")
        println("3. 게임 종료")

        val selectedMenu = readLine()!!.toInt()

        when(selectedMenu) {
            1 -> startGame()
            2 -> createCharacter()
            3 -> endGame()
        }
    }

    fun startGame() {
        println("게임이 시작되었습니다")
    }

    fun createCharacter() {
        val characterList: MutableList<Map<String, String>> = mutableListOf()

        var isNeedMoreCharacter = "Y"

        while (isNeedMoreCharacter == "Y") {
            println("추가할 캐릭터의 이름을 입력해주세요.")
            val name = readLine() ?: ""

            println("추가할 캐릭터의 직업을 입력해주세요.")
            val job = readLine() ?: ""

            val character = mapOf("name" to name, "job" to job)

            characterList.add(character)

            println("사용자를 더 추가하시겠습니까? (Y: 추가, N: 그만두기)")
            isNeedMoreCharacter = readLine() ?: "N"
        }

        characterList.forEach {
            println(it)
        }
    }

    fun endGame() {
        println("게임이 종료되었습니다")
    }
}

class Answer4 {
    fun main() {
        var isValidMenu = false
        var selectedMenu = 0

        while (isValidMenu == false) {
            try {
                selectedMenu = getMenuNumber()
                isValidMenu = true
            } catch (e: Exception) {
                isValidMenu = false
            }
        }

        when(selectedMenu) {
            1 -> startGame()
            2 -> createCharacter()
            3 -> endGame()
        }
    }

    fun getMenuNumber(): Int {
        println("메뉴를 입력해주세요.")
        println("1. 게임 시작")
        println("2. 캐릭터 추가")
        println("3. 게임 종료")

        val userInput = readLine()!!.toInt()

        if (userInput < 1 || userInput > 3) {
            throw Exception("입력이 잘못되었습니다.")
        }

        return userInput
    }

    fun startGame() {
        println("게임이 시작되었습니다")
    }

    fun createCharacter() {
        val characterList: MutableList<Map<String, String>> = mutableListOf()

        var isNeedMoreCharacter = "Y"

        while (isNeedMoreCharacter == "Y") {
            println("추가할 캐릭터의 이름을 입력해주세요.")
            val name = readLine() ?: ""

            println("추가할 캐릭터의 직업을 입력해주세요.")
            val job = readLine() ?: ""

            val character = mapOf("name" to name, "job" to job)

            characterList.add(character)

            println("사용자를 더 추가하시겠습니까? (Y: 추가, N: 그만두기)")
            isNeedMoreCharacter = readLine() ?: "N"
        }

        characterList.forEach {
            println(it)
        }
    }

    fun endGame() {
        println("게임이 종료되었습니다")
    }
}