package com.heng.kotlinlimitscrollapplication
import activitystarter.Arg
import activitystarter.MakeActivityStarter
import android.graphics.Rect
import android.os.Bundle
import android.view.Menu
import android.widget.FrameLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.heng.kotlinlimitscrollapplication.adpater.VP2Adapter
import com.heng.kotlinlimitscrollapplication.bean.Student
import com.marcinmoskala.activitystarter.argExtra
import kotlinx.android.synthetic.main.activity_drawer.*

@MakeActivityStarter
class DrawerActivity : BaseActivity() {

    @get:Arg val student : Student by argExtra()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)
    }

    override fun onContentChanged() {

        super.onContentChanged()
        drawer_tv.text = "number:${student.number},name:${student.name},grade:${student.grade}"

        val vP2Adapter = VP2Adapter(this, dataSet)
        /*
        * orientation分为水平滚动和垂直滚动
        * */
        view_pager2.orientation = ViewPager2.ORIENTATION_VERTICAL
        /*
        * isUserInputEnabled是否允许滑动
        * */
        view_pager2.isUserInputEnabled = true
        view_pager2.adapter = vP2Adapter
        /*
        * 页面选中监听
        * */
        view_pager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
    }

    override fun adapterNotchForView(rect: Rect) {
        val drawerLayoutParams
                = drawer_layout.layoutParams as FrameLayout.LayoutParams
        drawerLayoutParams.topMargin = rect.bottom
        drawer_layout.layoutParams = drawerLayoutParams
    }

    companion object {
        private val dataSet = arrayListOf("Item1", "Item2", "Item3", "Item4")
    }
}
