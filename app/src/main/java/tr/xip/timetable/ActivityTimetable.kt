package tr.xip.timetable

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_timetable.*
import org.apache.commons.io.IOUtils
import tr.xip.timetable.model.TimetableClass
import java.util.*

class ActivityTimetable : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timetable)
        setSupportActionBar(timetable_toolbar)

        val timetable = Gson().fromJson<ArrayList<TimetableClass>>(
                IOUtils.toString(assets.open("timetable.json"), "UTF-8"),
                object : TypeToken<ArrayList<TimetableClass>>() {}.type
        )

        if (timetable != null && timetable.isNotEmpty()) {
            timetable_pager.adapter = TimetableDaysPagerAdapter(timetable, supportFragmentManager)
            timetable_tabs_layout.setupWithViewPager(timetable_pager)

            // Normalize day value - our adapter works with five days, the first day (0) being Monday.
            val today = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 2
            timetable_pager.currentItem = if (today in 0..4) today else 0
        }
    }
}
