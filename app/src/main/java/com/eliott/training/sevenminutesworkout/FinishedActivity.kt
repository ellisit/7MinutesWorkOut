package com.eliott.training.sevenminutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eliott.training.sevenminutesworkout.databinding.ActivityExerciseBinding
import com.eliott.training.sevenminutesworkout.databinding.ActivityFinishedBinding

class FinishedActivity : AppCompatActivity() {
    private var binding: ActivityFinishedBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishedBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnFinish?.setOnClickListener{
            finish()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}