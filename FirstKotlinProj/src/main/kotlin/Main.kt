
fun main() {
    print("Enter a number: ")
    val number = readLine()?.toIntOrNull()

    if (number != null) {
        val words = convertNumberToWords(number)
        println("In words: $words")
    }
    else println("Sorry you entered an invalid number! \nPlease try again later.")
}

fun convertNumberToWords(number: Int): String {
    val units = arrayOf("s
        ", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    val teens = arrayOf("", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen")
    val tens = arrayOf("", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")

    return when {
        number == 0 -> "Zero"
        number < 0 -> "Minus " + convertNumberToWords(-number)
        number < 10 -> units[number]
        number in 11..19 -> teens[number - 10]
        number < 100 -> tens[number / 10] + if (number % 10 != 0) " " + units[number % 10] else ""
        number < 1000 -> units[number / 100] + " hundred" + if (number % 100 != 0) " and " + convertNumberToWords(number % 100) else ""
        number < 1000000 -> convertNumberToWords(number / 1000) + " thousand" + if (number % 1000 != 0) " " + convertNumberToWords(number % 1000) else ""
        else -> "Number out of The range (1-999999)"
    }
}