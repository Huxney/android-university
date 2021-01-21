package com.example.coroutines

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coroutines.databinding.ActivityCoroutinesBinding
import kotlinx.coroutines.*

class CoroutinesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoroutinesBinding

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private lateinit var job1: Job
    private lateinit var job2: Job

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val button1 = binding.job1Button
        val button2 = binding.job2Button
        val button3 = binding.job3Button

        button1.setOnClickListener {
            job1 = coroutineScope.launch {
                button1.isEnabled = false

                startTimer()

                button1.isEnabled = true
            }
        }

        button2.setOnClickListener {
            job2 = coroutineScope.launch {
                button2.isEnabled = false

                addRandomNumberToArray()

                button2.isEnabled = true
            }
        }

        button3.setOnClickListener {
            if (::job1.isInitialized) {
                job1.cancel()

                button1.isEnabled = true
            }

            if (::job2.isInitialized) {
                job2.cancel()

                button2.isEnabled = true
            }

            if (::job1.isInitialized || ::job2.isInitialized) {
                binding.textView3.text = "Jobs have been cancelled"
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private suspend fun startTimer() {
        withContext(Dispatchers.Default) {
            for (second in 0..60) {
                val secondCounter = second

                if (job1.isActive) {
                    withContext(Dispatchers.Main) {
                        binding.textView1.text = "Current second: $secondCounter"
                    }
                    delay(1000)
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private suspend fun addRandomNumberToArray() {
        withContext(Dispatchers.Default) {
            val array = arrayListOf<Int>()

            repeat(1000) {
                array.add((0..1000).random())

                withContext(Dispatchers.Main) {
                    binding.textView2.text = "Random number added: ${array.last()}"
                }

                delay(500)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        binding.job1Button.isEnabled = true
        binding.job2Button.isEnabled = true
    }

    override fun onStop() {
        super.onStop()
        job1.cancel()
        job2.cancel()
    }

}