package com.regula.documentreader



import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.hbscanner.DocumentScannerWrapper
import com.regula.documentreader.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.showScannerBtn.setOnClickListener {
            binding.surnameTv.text = "Surname:"
            binding.nameTv.text = "Name:"
            binding.resultIv.setImageBitmap(null)
            showScanner()
        }
    }

//    private val completion =
//        IDocumentReaderCompletion { action, results, error ->
//            //processing is finished, all results are ready
//            if (action == DocReaderAction.COMPLETE) {
//                displayImage(results)
//                displayTextFields(results)
//            } else {
//                //something happened before all results were ready
//                if (action == DocReaderAction.CANCEL) {
//                    Toast.makeText(this@MainActivity, "Scanning was cancelled", Toast.LENGTH_LONG)
//                        .show()
//                } else if (action == DocReaderAction.ERROR) {
//                    Toast.makeText(this@MainActivity, "Error:$error", Toast.LENGTH_LONG).show()
//                }
//            }
//        }

    private fun showScanner() {

        val scanner = DocumentScannerWrapper(this)
        scanner.startScanner(this,{
            data->
            var documentImage = data?.getGraphicFieldImageByType(201)
            val aspectRatio = documentImage?.width!!.toDouble() / documentImage.height
                    .toDouble()
                documentImage = Bitmap.createScaledBitmap(
                    documentImage,
                    (1000 * aspectRatio).toInt(), 1000, false
                )
            binding.resultIv.setImageBitmap(documentImage)
            data!!.textResult!!.fields.forEach {
            Log.e("SCANNER DATA========>", "FIELD NAME: "+it.getFieldName(this).toString())
            Log.e("SCANNER DATA========>", "displayTextFields: "+it.value.toString())

        }
        },{
        })

//        DocumentScanner().openScanner(this, object: ScannerUtils {
//            override fun onScanImage(img: Bitmap) {
//                binding.resultIv.setImageBitmap(img)
//            }
//
//            override fun onScanData(listData: List<ScannerResult>) {
//                listData.forEach {
//                    Log.e("SCANNER DATA========>", "FIELD NAME: "+it.fieldName)
//                    Log.e("SCANNER DATA========>", "displayTextFields: "+it.value.toString())
//
//                }
//            }
//        })
//        DocumentReader.Instance().functionality().edit()
//            .setForcePagesCount(2)
//            .apply();
//
//        val onlineProcessingConfiguration = OnlineProcessingConfig.Builder(OnlineMode.MANUAL)
//            .setUrl(Constants.BASE_URL)
//            .build()
//        onlineProcessingConfiguration.processParam.scenario = Scenario.SCENARIO_FULL_PROCESS;
//
//        val scannerConfig = ScannerConfig.Builder(onlineProcessingConfiguration).build()
//        DocumentReader.Instance().showScanner(this, scannerConfig, completion)
    }


//    private fun displayImage(results: DocumentReaderResults?) {
//        if (results?.getGraphicFieldImageByType(eGraphicFieldType.GF_PORTRAIT) != null) {
//
//            var documentImage = results.getGraphicFieldImageByType(eGraphicFieldType.GF_PORTRAIT)
//            if (documentImage != null) {
//                val aspectRatio = documentImage.width.toDouble() / documentImage.height
//                    .toDouble()
////                documentImage = Bitmap.createScaledBitmap(
////                    documentImage,
////                    (1000 * aspectRatio).toInt(), 1000, false
////                )
//                binding.resultIv.setImageBitmap(documentImage)
//            }
//        }
//    }

//    private fun displayTextFields(results: DocumentReaderResults?) {
//
//        results!!.textResult!!.fields!!.forEach {
//            Log.e("SCANNER DATA========>", "FIELD NAME: "+it.getFieldName(this).toString())
//            Log.e("SCANNER DATA========>", "displayTextFields: "+it.value.toString())
//
//        }
//
//        if (results?.getTextFieldByType(eVisualFieldType.FT_SURNAME) != null) {
//            val surname = "Surname:" + results.getTextFieldValueByType(eVisualFieldType.FT_SURNAME)
//            binding.surnameTv.text = surname
//        } else {
//            binding.surnameTv.text = "Surname:"
//        }
//
//        if (results?.getTextFieldByType(eVisualFieldType.FT_GIVEN_NAMES) != null) {
//            val name = "Name: " + results.getTextFieldValueByType(eVisualFieldType.FT_GIVEN_NAMES)
//            binding.nameTv.text = name
//        } else {
//            binding.nameTv.text = "Name:"
//        }
//    }
}