package com.eliott.training.sevenminutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eliott.training.sevenminutesworkout.databinding.ActivityBmiactivityBinding
import com.eliott.training.sevenminutesworkout.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {
    var binding: ActivityHistoryBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarHistory)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "History"
        }

        binding?.toolbarHistory?.setNavigationOnClickListener() {
            onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}