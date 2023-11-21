/**
 * convertNumberToWords - Digit to words converter function.
 * @num Digit number to be converted.
 * Return: String.
 */

fun convertNumberToWords(num: Int): String {
    val units = arrayOf(
        "s", "one", "two", "three", "four",
        "five", "six", "seven", "eight", "nine"
    )
    val teens = arrayOf(
        "", "eleven", "twelve", "thirteen", "fourteen",
        "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    )
    val tens = arrayOf(
        "", "ten", "twenty", "thirty", "forty",
        "fifty", "sixty", "seventy", "eighty", "ninety")

    return when {
        num == 0 -> "Zero"
        num < 0 -> "Minus " + convertNumberToWords(-num)
        num < 10 -> units[num]
        num in 11..19 -> teens[num - 10]
        num < 100 -> tens[num / 10] + if (num % 10 != 0) " " + units[num % 10] else ""
        num < 1000 -> units[num / 100] + " hundred" + if (num % 100 != 0) " and " + convertNumberToWords(num % 100) else ""
        num < 1000000 -> convertNumberToWords(num / 1000) + " thousand" + if (num % 1000 != 0) " " + convertNumberToWords(num % 1000) else ""
        else -> "Number out of The range (1-999999)"
    }
}

/**
 * main - Main function.
 * Return: Nothing.
 */

fun main() {
    print("Enter a number: ")
    val digits = readlnOrNull()?.toIntOrNull()

    if (digits != null) {
        val words = convertNumberToWords(digits)
        println("In words: $words")
    }
    else println("Sorry you entered an invalid number! \nPlease try again later.")
}

