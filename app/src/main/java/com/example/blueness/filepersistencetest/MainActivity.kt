package com.example.blueness.filepersistencetest

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import java.io.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val edit = findViewById<EditText>(R.id.edit)
        val inputText = load()
        if(!TextUtils.isEmpty(inputText)){
            edit.setText(inputText)
            toast(inputText)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val inputText:String = edit.text.toString()
        save(inputText)
    }

    fun save(inputText: String){
        val out = openFileOutput("data.txt", Context.MODE_PRIVATE)
        val print = PrintStream(out)
        print.println(inputText)
        print.close()
    }

    fun load():String{
        val fis = openFileInput("data.txt")
        val buff = ByteArray(1024)
        val sb = StringBuilder("")
        var hasRead = fis.read(buff)
        while (hasRead > 0) {
            sb.append(String(buff, 0, hasRead))
            hasRead = fis.read(buff)
        }
        fis.close()
        return sb.toString()
    }
}
