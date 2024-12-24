package com.myjar.jarassignment

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.myjar.jarassignment.databinding.ActivityDetailBinding
import com.myjar.jarassignment.databinding.ActivityMainBinding

class DetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_detail)

        // Get the itemId passed from the MainActivity
        val itemId = intent.getStringExtra("itemId")

        // Find the TextView to display the details
        val detailTextView = binding.detailText

        // Display the item details
        detailTextView.text = "Item Details for ID: $itemId"
    }
}
