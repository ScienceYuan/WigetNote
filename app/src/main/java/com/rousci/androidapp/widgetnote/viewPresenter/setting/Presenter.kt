package com.rousci.androidapp.widgetnote.viewPresenter.setting

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.util.TypedValue
import android.view.MenuItem
import android.widget.RemoteViews
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.viewPresenter.*
import com.rousci.androidapp.widgetnote.viewPresenter.widget.NoteWidget

/**
 * Created by rousci on 17-11-2.
 */

fun updateConfig(context: Setting){
    val frequencyEdited = context.frequencyEditor!!.text.toString().toInt()
    val fontEdited = context.fontSizeEditor!!.text.toString().toFloat()

    val editor = context.getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).edit()
    editor.putInt(frequency, frequencyEdited)
    editor.putFloat(fontSP, fontEdited)

    editor.apply()
}

fun updateRemoteView(appWidgetManager: AppWidgetManager, context: Setting){
    val fontEdited = context.fontSizeEditor!!.text.toString().toFloat()
    val lastNote = context.getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).
            getString(lastChoicedNote, "没有添加数据")

    val views = RemoteViews(context.packageName, R.layout.note_widget)
    val componentName = ComponentName(context, NoteWidget::class.java)
    views.setTextViewText(R.id.appwidget_text, lastNote)
    views.setTextViewTextSize(R.id.appwidget_text, TypedValue.COMPLEX_UNIT_SP ,fontEdited)
    appWidgetManager.updateAppWidget(componentName, views)
}

fun onOptionsItemSelected(item: MenuItem, context: Setting){
    when(item.itemId){
        android.R.id.home -> {
            context.finish()
        }
    }
}