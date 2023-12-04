package com.example.composetutorial.utils

fun CalculateTotalTip(totalBill: Double, tipPercentage: Int): Double {

    return if (totalBill > 1 && totalBill.toString().isNotEmpty())
        (totalBill * tipPercentage) / 100
    else 0.0;
}

fun CalculateTotalPerson(totalBill: Double, splitBy: Int, tipPercentage: Int): Double {


    val bill = CalculateTotalTip(totalBill = totalBill, tipPercentage = tipPercentage) + totalBill;

    return (bill / splitBy);
}
