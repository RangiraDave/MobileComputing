class MastermindGame {
    private val colors = listOf('R', 'B', 'Y', 'G', 'O', 'W')
    private val secretCode = generateCode()
    private var hintsGiven = 0

    private fun generateCode(): List<Char> {
        return List(4) { colors.random() }
    }

    fun playGame() {
        var attempts = 0

        println("Welcome to the GUESS THE COLORS!\n" +
                "In this game, you are challenged to guess a sequence of 4 colors.\n" +
                "The available colors to choose from are: R, B, Y, G, O, W\n" +
                "You can also surrender any time by entering 'quit'.\n" +
                "Additionally, after 10 attempts, you can get a hint by entering 'hint'.\n")

        while (true) {
            attempts++
            print("Attempt $attempts: Enter your guess (e.g., RBYG): ")
            val input = readLine()?.toUpperCase() ?: ""

            if (input == "QUIT") {
                println("The secret code was: ${secretCode.joinToString("")}")
                break
            } else if (input == "HINT" && attempts > 10 && hintsGiven < 1) {
                hintsGiven++
                println("Hint: The first color is ${secretCode[0]}")
                continue
            }

            val guess = input.toList()

            if (guess.size != 4 || guess.any { it !in colors }) {
                println("Invalid input\n" +
                        "Please try again and this time choose exactly 4 colors from 'R, B, Y, G, O, W' or enter 'quit' to surrender.")
                continue
            }

            val (correctPos, correctCol) = evaluateGuess(secretCode, guess)

            if (correctPos == 4) {
                println("Congratulations!\nYou've guessed the sequence ${secretCode.joinToString("")} after $attempts attempts!")
                break
            } else {
                println("Correct colors in correct positions: $correctPos\nCorrect colors in wrong positions: $correctCol")
            }

            if (attempts >= 30) {
                println("You've reached 30 attempts. The secret code was: ${secretCode.joinToString("")}")
                break
            }
        }
    }

    private fun evaluateGuess(code: List<Char>, guess: List<Char>): Pair<Int, Int> {
        var correctPosition = 0
        var correctColor = 0
        val codeCopy = code.toMutableList()

        for (i in guess.indices) {
            if (guess[i] == code[i]) {
                correctPosition++
                codeCopy[i] = 'X'
            }
        }

        for (i in guess.indices) {
            if (guess[i] in codeCopy && guess[i] != code[i]) {
                correctColor++
                codeCopy[codeCopy.indexOf(guess[i])] = 'X'
            }
        }

        return Pair(correctPosition, correctColor)
    }
}

fun main() {
    val game = Game()
    game.playGame()
}