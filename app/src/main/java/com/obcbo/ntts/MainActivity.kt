package com.obcbo.ntts

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startTTS()
    }

    private fun startTTS() { //初始化TTS
        tts = TextToSpeech(this, TextToSpeech.OnInitListener {
            if (it == TextToSpeech.SUCCESS) {
                val i = tts.setLanguage(Locale.CHINESE) //设置语言
                if (i == TextToSpeech.LANG_MISSING_DATA || i == TextToSpeech.LANG_NOT_SUPPORTED) {
                    tts.setSpeechRate(1.0f)
                    //Snackbar.make(view, "初始化失败", Snackbar.LENGTH_SHORT).show()
                }
            } else {
                //Snackbar.make(view, "你貌似还没有输入？", Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    fun speech(view: View) {
        val stext = findViewById<EditText>(R.id.speakText)
        val text = stext.text.toString()
        if (text.isBlank()) {
            Snackbar.make(view, "你貌似还没有输入？", Snackbar.LENGTH_SHORT)
                .setAction("确定") { }
                .show()
        } else {
            tts.speak(text, TextToSpeech.QUEUE_ADD, null)
        }
    }

    fun save(view: View) {
        Snackbar.make(view, "你貌似还什么都没输入？", Snackbar.LENGTH_SHORT)
            .setAction("确定") { }
            .show()
    }

    fun stop(view: View) {
        super.onStop();
    }
}

