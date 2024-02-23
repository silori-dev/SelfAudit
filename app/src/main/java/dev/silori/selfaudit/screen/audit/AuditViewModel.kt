package dev.silori.selfaudit.screen.audit


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.silori.selfaudit.data.entity.AuditData
import dev.silori.selfaudit.data.repo.AuditRepo
import dev.silori.selfaudit.utillClass.getTodayDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuditViewModel @Inject constructor(
    private val repo: AuditRepo
) : ViewModel() {

    private val _firstWork = MutableStateFlow<String>("")
    val firstWorkStateFlow: StateFlow<String> = _firstWork

    private val _secondWork = MutableStateFlow<String>("")
    val secondWorkStateFlow: StateFlow<String> = _secondWork

    private val _firstMess = MutableStateFlow<String>("")
    val firstMessStateFlow: StateFlow<String> = _firstMess

    private val _secondMess = MutableStateFlow<String>("")
    val secondMessStateFlow: StateFlow<String> = _secondMess

    private val _productivity = MutableStateFlow<Float>(5.0f)
    val productivityStateFlow: StateFlow<Float> = _productivity

    private val _showTextFieldError = MutableStateFlow(false)
    val showTextFieldError: StateFlow<Boolean> = _showTextFieldError

    fun updateFirstWork(value: String) {
        _firstWork.value = value
    }

    fun updateSecondWork(value: String) {
        _secondWork.value = value
    }

    fun updateFirstMess(value: String) {
        _firstMess.value = value
    }

    fun updateSecondMess(value: String) {
        _secondMess.value = value
    }

    fun updateProductivityPointer(value: Float) {
        _productivity.value = value
    }

    fun saveTodayStats() {
        // TODO : Show the Toast "Audit Saved"
        if (isInfoEmpty()) {
            _showTextFieldError.value = true
        } else {
            viewModelScope.launch {
                repo.addAudit(
                    AuditData(
                        date = getTodayDate(),
                        firstWork = _firstWork.value.trim(),
                        secondWork = _secondWork.value.trim(),
                        firstMess = _firstMess.value.trim(),
                        secondMess = _secondMess.value.trim(),
                        productivity = _productivity.value.toInt(),
                    )
                )
                emptyAuditData()
            }
        }
    }

    private fun isInfoEmpty(): Boolean {
        return (_firstWork.value.trim().isEmpty() ||
                _secondWork.value.trim().isEmpty() ||
                _firstMess.value.trim().isEmpty() ||
                _secondMess.value.trim().isEmpty()
                )
    }

    private fun emptyAuditData() {
        _firstWork.value = ""
        _secondWork.value = ""
        _firstMess.value = ""
        _secondMess.value = ""
        _productivity.value = 5f
        _showTextFieldError.value = false
    }

}