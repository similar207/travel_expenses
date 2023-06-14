package com.example.travel_expenses

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.travel_expenses.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private  lateinit var mBinding: ActivityMainBinding
    private val model: MainViewModel by viewModels()
    val api = APIService.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mBinding.setting.setOnClickListener(){
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("설정")

            val  mAlertDialog = mBuilder.show()
            val okButton = mDialogView.findViewById<Button>(R.id.search_btn)
            okButton.setOnClickListener {
                val exchange = mDialogView.findViewById<EditText>(R.id.exchange).text.toString()
                val radioGroup = mDialogView.findViewById<RadioGroup>(R.id.rg)
                var text = ""
                when(radioGroup.checkedRadioButtonId){
                    R.id.usd -> {
                        text = "$"
                        ret("usd")
                    }
                    R.id.jpy -> {
                        text = "¥"
                        ret("jpy")
                    }
                }
                model.set_money(exchange, text)
                mAlertDialog.dismiss()
            }

            val noButton = mDialogView.findViewById<Button>(R.id.closeButton)
            noButton.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }

        mBinding.lifecycleOwner = this
        mBinding.viewModel = model
    }

    fun ret(countury : String) {
        api.get(countury).enqueue(object : Callback<apiresponse> {
            override fun onFailure(call: Call<apiresponse>, t: Throwable) {
                println("실패")
                Log.d("백엔드", "메세지 + " + t.message)
            }

            override fun onResponse(call: Call<apiresponse>, response: Response<apiresponse>) {
                println("response = " + response.body())
                println("성공")
                if(countury == "usd"){
                    response.body()!!.usd?.let { model.set_change(it) }
                }
                else if(countury == "jpy"){
                    response.body()!!.jpy?.let { model.set_change(it) }
                }

            }
        })
    }
}