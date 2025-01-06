fun main() {
    print("Комиссия по операции составит: " + transferFee("Mastercard", 75_000, 75_000))
}

fun cardFee(card: String) = when (card) { // Возвращает процент комисси по текущей карте
    "Visa" -> 0.0075
    "Mastercard" -> 0.006
    else -> 0.0
}

fun visaFee(currentSum: Int): Int { // Возвращает сумму комиссии для карты Visa
    return if ((currentSum * cardFee("Visa")) > 35)
        (currentSum * cardFee("Visa")).toInt()
    else 35
}

fun masterCardFee(mounthSum: Int, currentSum: Int): Int { // Возвращает сумму комиссии по карте Mastercard
    return if (mounthSum > 75_000)
        (currentSum * cardFee("Mastercard")).toInt() + 20
    else if (mounthSum + currentSum > 75_000)
        ((mounthSum + currentSum - 75_000 ) * cardFee("Mastercard")).toInt() + 20
    else 20
}

fun transferFee(card: String = "Мир", monthSum: Int, currentSum: Int): Int { /* Проверяет месячный и дневной
                                                                                лимиты и блокирует операцию
                                                                                либо возвращает сумму комиссии */
    if (currentSum > 150_000 || currentSum + monthSum > 600_000) {
        print("Операция заблокирована, комиссия = ")
        return 0
    }

    return when (card) {
        "Visa" -> visaFee(currentSum)
        "Mastercard" -> masterCardFee(monthSum, currentSum)
        else -> 0
    }

}