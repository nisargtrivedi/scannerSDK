package com.hbscanner

import android.content.Context
import com.regula.documentreader.api.DocumentReader
import com.regula.documentreader.api.config.ScannerConfig
import com.regula.documentreader.api.enums.DocReaderAction
import com.regula.documentreader.api.enums.OnlineMode
import com.regula.documentreader.api.enums.Scenario
import com.regula.documentreader.api.params.OnlineProcessingConfig
import com.regula.documentreader.api.results.DocumentReaderResults

class DocumentScannerWrapper(private val context: Context) {



    fun startScanner(
        context: Context,
        onResult: (DocumentReaderResults?) -> Unit,
        onError: (String) -> Unit
    ) {
        DocumentReader.Instance().functionality().edit()
            .setForcePagesCount(2)
            .apply();

        val onlineProcessingConfiguration = OnlineProcessingConfig.Builder(OnlineMode.MANUAL)
            .setUrl("https://api.regulaforensics.com")
            .build()
        onlineProcessingConfiguration.processParam.scenario = Scenario.SCENARIO_FULL_PROCESS;

        val scannerConfig = ScannerConfig.Builder(onlineProcessingConfiguration).build()
        DocumentReader.Instance().showScanner(context, scannerConfig
        ) { p0, result, error ->
            if(p0==DocReaderAction.COMPLETE){
                onResult(result)
            }else if(p0==DocReaderAction.CANCEL || p0==DocReaderAction.ERROR){
                onError(error.toString())
            }
        }
    }

    fun deinitialize() {
        DocumentReader.Instance().deinitializeReader()
    }
}