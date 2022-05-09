package com.aligatorapt.duckdam.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aligatorapt.duckdam.R
import com.aligatorapt.duckdam.databinding.ActivityVendingBinding
import com.aligatorapt.duckdam.view.fragment.vending.DrawFragment

class VendingActivity: AppCompatActivity()  {
    lateinit var binding: ActivityVendingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVendingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        val transaction = supportFragmentManager.beginTransaction()
            .add(R.id.vendingFrameLayout, DrawFragment())
        transaction.commit()

        binding.vendingBackBtn.setOnClickListener {
            onBackPressed()
        }
    }

    fun changeFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.vendingFrameLayout, fragment)
            .addToBackStack(null)
        transaction.commit()
    }
}
