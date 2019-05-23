package com.kernelpanic.nfcbanksim.Cards

import java.util.*

fun validateCard(number: String): Boolean{
    var checksum = 0

    val sanitizedNumber = number.replace(" ", "")

    for (i in sanitizedNumber.length-1 downTo 0 step 2){
        checksum += (sanitizedNumber[i] - '0')
    }
    for (i in sanitizedNumber.length-2 downTo 0 step 2){
        val temp = (sanitizedNumber[i] - '0') *2
        checksum += if(temp > 9) temp-9 else temp

    }
    return checksum % 10 == 0
}

fun generateCardNumber(): String{
    var cardNumber = "4"
    cardNumber += (0..14).map { Random().nextInt(10) }.joinToString("")
    var cardNumberString = cardNumber.chunked(4).joinToString(" ")
    print("cn: $cardNumberString  -- ")
    print("is ${validateCard(cardNumberString)} \n")
    if(!validateCard(cardNumberString)){
        cardNumberString = generateCardNumber()
    }
    return cardNumberString
}
