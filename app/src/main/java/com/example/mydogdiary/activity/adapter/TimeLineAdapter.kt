package com.example.mydogdiary.activity.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.mydogdiary.R
import com.example.mydogdiary.activity.ListObject.TimelineDiary


open class TimeLineAdapter(context: Context, diaries: ArrayList<TimelineDiary>) : ArrayAdapter<TimelineDiary>(context, 0 , diaries) {

    private val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val diary = getItem(position)

        // レイアウトの設定
        var view = convertView
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.diary_list, parent, false)
        }

        val body = view?.findViewById<TextView>(R.id.diaryBody)
        body?.text = diary?.body


        return view!!
    }
}