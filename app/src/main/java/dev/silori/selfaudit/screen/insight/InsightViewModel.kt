package dev.silori.selfaudit.screen.insight

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.silori.selfaudit.data.entity.AuditData
import dev.silori.selfaudit.data.repo.AuditRepo
import dev.silori.selfaudit.utillClass.LastMonth
import dev.silori.selfaudit.utillClass.SecondLastMonth
import dev.silori.selfaudit.utillClass.ThisMonth
import dev.silori.selfaudit.utillClass.ThisYear
import dev.silori.selfaudit.utillClass.TimeFrame
import dev.silori.selfaudit.utillClass.dateToFloat
import dev.silori.selfaudit.utillClass.getCurrentMonth
import dev.silori.selfaudit.utillClass.getCurrentYear
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsightViewModel @Inject constructor(
     val repo: AuditRepo
) : ViewModel() {

    val chartEntryModelProducer = ChartEntryModelProducer(listOf<FloatEntry>())

    private val _insightData = MutableStateFlow(listOf<AuditData>())
    val insightData: StateFlow<List<AuditData>> = _insightData

    private val _currentTimeFrameState = MutableStateFlow<TimeFrame>(ThisMonth)
    val currentTimeFrame: StateFlow<TimeFrame> = _currentTimeFrameState

    init {
        viewModelScope.launch {
            repo.getAuditByMonth(month = getCurrentMonth(), year = getCurrentYear().toString()).collect {
                _insightData.value = it
                this.coroutineContext.job.cancel()
            }
        }

        viewModelScope.launch {
            collecting()
        }
    }

    fun changeTimeFrame(timeFrame: TimeFrame) {
        _currentTimeFrameState.value = timeFrame

        when (timeFrame) {
            LastMonth -> {
                collectAuditByMonth(
                    month = timeFrame.month,
                    year = timeFrame.year
                )
            }
            SecondLastMonth -> {
                collectAuditByMonth(
                    month = timeFrame.month,
                    year = timeFrame.year
                )
            }
            ThisMonth -> {
                collectAuditByMonth(
                    month = timeFrame.month,
                    year = timeFrame.year
                )
            }
            ThisYear -> {
                collectAuditByYear(year = timeFrame.year)
            }
        }
    }

    private suspend fun collecting() {
        _insightData.collect { listData ->
            val listForGraph = mutableListOf<FloatEntry>()
            listData.forEach { auditData ->
                listForGraph.add(
                    FloatEntry(
                        auditData.date.dateToFloat(),
                        auditData.productivity.toFloat()
                    )
                )
            }
            chartEntryModelProducer.setEntries(listForGraph)
        }
    }


    private fun collectAuditByMonth(month: String, year: String) {
        viewModelScope.launch {
            repo.getAuditByMonth(month, year).collect {
                _insightData.value = it
                this.coroutineContext.job.cancel()
            }
        }
    }

    private fun collectAuditByYear(year: String) {
        viewModelScope.launch {
            repo.getAuditByYear(year).collect {
                _insightData.value = it
                this.coroutineContext.job.cancel()
            }
        }
    }
}