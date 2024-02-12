package dev.silori.selfaudit.utillClass


sealed interface TimeFrame {
    val title: String
    val year: String
    val month: String
}

data object ThisYear : TimeFrame {
    override val title: String = "This Year"
    override val year: String = getCurrentYear().toString()
    override val month: String = "00"

}

data object ThisMonth : TimeFrame {
    override val title: String = "This Month"
    override val year: String = getCurrentYear().toString()
    override val month: String = getCurrentMonth()
}

data object LastMonth : TimeFrame {
    override val title: String = getPreviousMonthName(1)
    override val year: String = getYearOfPastMonth(1)
    override val month: String = getPreviousMonthNumber(1)
}

data object SecondLastMonth : TimeFrame {
    override val title: String = getPreviousMonthName(2)
    override val year: String = getYearOfPastMonth(2)
    override val month: String = getPreviousMonthNumber(1)
}