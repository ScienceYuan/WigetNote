package com.rousci.androidapp.widgetnote.viewPresent.setting

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageButton
import com.rousci.androidapp.widgetnote.R
import com.rousci.androidapp.widgetnote.viewPresent.*
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

class Setting : AppCompatActivity() {
    var frequencyEditor:EditText? = null
    var fontSizeEditor:EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting)
        setPresenter(this)

        val toolbar = find<Toolbar>(R.id.toolbar1)
        toolbar.setTitle(R.string.setting)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        frequencyEditor = find(R.id.frequencyEditor)
        fontSizeEditor = find(R.id.fontSize)

        val frequencyStored = getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).getInt(frequency, defaultFrequency)
        val fontSize = getSharedPreferences(singleDataPreference, Context.MODE_PRIVATE).getFloat(fontSP, fontSPDefault)

        fontSizeEditor!!.setText(fontSize.toString())
        frequencyEditor!!.setText(frequencyStored.toString())
    }

    override fun finish() {
        try {
            finishPR()
            super.finish()
        }
        catch (e:NumberFormatException){
            toast(R.string.invalidFormat)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onOptionsItemSelectedPR(item)
        return super.onOptionsItemSelected(item)
    }
}