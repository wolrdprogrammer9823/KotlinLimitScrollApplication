package com.heng.kotlinlimitscrollapplication.adpater
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabFragmentAdapter(layoutManager: FragmentManager, behavior: Int, fragments: List<Fragment>,titles:List<String>)
                        : FragmentPagerAdapter(layoutManager,behavior) {

    private var fragments : List<Fragment> = fragments
    private var titles : List<String> = titles

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? = titles[position]
}