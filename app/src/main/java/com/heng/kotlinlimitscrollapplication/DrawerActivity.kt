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
    }

    override fun adapterNotchForView(rect: Rect) {
        val drawerLayoutParams
                = drawer_layout.layoutParams as FrameLayout.LayoutParams
        drawerLayoutParams.topMargin = rect.bottom
        drawer_layout.layoutParams = drawerLayoutParams
    }
}
