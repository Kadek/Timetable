package tr.xip.timetable

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.timetable_fragment_day.*
import tr.xip.timetable.model.TimetableClass

class TimetableDayFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.timetable_fragment_day, container, false)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        val day = arguments?.getInt(ARG_DAY)
        val timetable = arguments?.getSerializable(ARG_TIMETABLE)

        if (day == null || timetable == null) return

        timetable_recycler.layoutManager = LinearLayoutManager(context)
        timetable_recycler.adapter = TimetableClassesAdapter(day, timetable as List<TimetableClass>)
    }

    companion object {
        val ARG_DAY = "day"
        val ARG_TIMETABLE = "timetable"

        fun new(day: Int, timetable: ArrayList<TimetableClass>): TimetableDayFragment {
            val fragment = TimetableDayFragment()
            val arguments = Bundle()
            arguments.putInt(ARG_DAY, day)
            arguments.putSerializable(ARG_TIMETABLE, timetable)
            fragment.arguments = arguments
            return fragment
        }
    }
}