package com.heng.kotlinlimitscrollapplication
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.heng.kotlinlimitscrollapplication.adpater.TabFragmentAdapter
import com.heng.kotlinlimitscrollapplication.fragments.Tab1Fragment
import com.heng.kotlinlimitscrollapplication.fragments.Tab2Fragment
import com.heng.kotlinlimitscrollapplication.fragments.Tab3Fragment
import com.heng.kotlinlimitscrollapplication.fragments.Tab4Fragment
import kotlinx.android.synthetic.main.content_main.*

open class MainActivity : BaseActivity() {

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setNavigationBarBgColor()
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onContentChanged() {

        super.onContentChanged()

        val tab1Fragment = Tab1Fragment.newInstance("tab1","tab1")
        val tab2Fragment = Tab2Fragment.newInstance("tab2","tab2")
        val tab3Fragment = Tab3Fragment.newInstance("tab3","tab3")
        val tab4Fragment = Tab4Fragment.newInstance("tab4","tab4")

        val titles = listOf("one","two","three","four")
        val fragmentList = listOf(tab1Fragment, tab2Fragment, tab3Fragment, tab4Fragment)
        val tabFragmentPagerAdapter = TabFragmentAdapter(supportFragmentManager, BEHAVIOR_SET_USER_VISIBLE_HINT, fragmentList,titles)

        container_vp.adapter = tabFragmentPagerAdapter
        tab_layout.setupWithViewPager(container_vp)
    }

    override fun adapterNotchForView(rect: Rect) {
        val mLayoutParams = tab_layout.layoutParams as LinearLayoutCompat.LayoutParams
        //mLayoutParams.leftMargin = rect.right
        mLayoutParams.topMargin = rect.bottom
        tab_layout.layoutParams = mLayoutParams
    }

    override fun statusBarColor(): Int {
        return super.statusBarColor()
    }

    open fun setNavigationBarBgColor() {
        setNavigationBarBgColor(android.R.color.black)
    }

    open fun setNavigationBarBgColor(@ColorRes colorRes: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            window.navigationBarColor = resources.getColor(colorRes)
        }
    }
}
