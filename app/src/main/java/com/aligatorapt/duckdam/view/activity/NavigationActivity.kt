package com.aligatorapt.duckdam.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aligatorapt.duckdam.R
import com.aligatorapt.duckdam.databinding.ActivityNavigationBinding
import com.aligatorapt.duckdam.dto.compliment.ComplimentResponseDto
import com.aligatorapt.duckdam.retrofit.callback.ComplimentsCallback
import com.aligatorapt.duckdam.view.fragment.compliment.ComplimentFragment
import com.aligatorapt.duckdam.view.fragment.compliment.FriendListFragment
import com.aligatorapt.duckdam.view.fragment.home.ScrollHorizontalFragment
import com.aligatorapt.duckdam.view.fragment.home.ScrollVerticalFragment
import com.aligatorapt.duckdam.view.fragment.setting.SettingFragment
import com.aligatorapt.duckdam.view.fragment.sticker.StickerFragment
import com.aligatorapt.duckdam.viewModel.ComplimentSingleton
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NavigationActivity : AppCompatActivity() {
    lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        binding.apply {
            //탭바
            bottomNavigation.run {
                setOnItemSelectedListener {
                    when (it.itemId) {
                        R.id.tab_home -> {
                            changeFragment(ScrollHorizontalFragment())
                            showTabView(true)
                        }
                        R.id.tab_friend -> {
                            changeFragment(FriendListFragment())
                            showTabView(true)
                        }
                        R.id.tab_compliment -> {
                            changeFragment(ComplimentFragment())
                            showTabView(true)
                        }
                        R.id.tab_sticker -> {
                            changeFragment(StickerFragment())
                            showTabView(true)
                        }
                        R.id.tab_setting -> {
                            changeFragment(SettingFragment())
                            showTabView(true)
                        }
                    }
                    true
                }
                //초기값 셋팅
                selectedItemId = R.id.tab_home
            }

            val position = intent.getIntExtra("position", 0)
            if(position != 0){
                selectedBottomNavigationItem(position)
            }
        }
    }

    fun selectedBottomNavigationItem(_itemId: Int){
        binding.bottomNavigation.selectedItemId = _itemId
    }

    fun showTabView(flag: Boolean){
        if(flag)
            binding.bottomNavigation.visibility = View.VISIBLE
        else
            binding.bottomNavigation.visibility = View.GONE
    }

    fun changeFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.navigationFrameLayout, fragment)
        transaction.commit()
    }
}
