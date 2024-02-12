package dev.silori.selfaudit.utillClass

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


private val mapOfMonth = mapOf(
    "01" to "Jan",
    "02" to "Feb",
    "03" to "Mar",
    "04" to "Apr",
    "05" to "May",
    "06" to "Jun",
    "07" to "Jul",
    "08" to "Aug",
    "09" to "Sep",
    "10" to "Oct",
    "11" to "Nov",
    "12" to "Dec"
)


fun getTodayDate(): String {
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return current.format(formatter)
}

fun getCurrentYear(): Int {
    return LocalDateTime.now().year
}

fun getCurrentMonth(): String {
    return LocalDateTime.now().monthValue.monthValueToString()
}


fun getYearOfPastMonth(monthNum: Int): String {
    val previousMonth = LocalDateTime.now().monthValue - 1

    return if (previousMonth <= 0)
        (getCurrentYear() - 1).toString()
    else
        getCurrentYear().toString()
}

fun getPreviousMonthNumber(num: Int): String {
    val localDate = LocalDateTime.now().minusMonths(num.toLong()).monthValue
    return localDate.monthValueToString()
}

fun getPreviousMonthName(num: Int): String {
    val previousMonthNum = getPreviousMonthNumber(num)
    return mapOfMonth.getValue(previousMonthNum)

}

fun Int.monthValueToString(): String {
    return if (this < 10) "0$this" else this.toString()
}


fun String.dateToFloat(): Float {
    return LocalDate.parse(this).toEpochDay().toFloat()
}


fun String.dateFormatter(): String {

    val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("d MMM")
    val date = this.dateToFloat()
    val formatedDate = (LocalDate.ofEpochDay(date.toLong())).format(dateTimeFormatter)
    return formatedDate

}