package com.emrys.androidlab

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var tickCross: ImageView
    private lateinit var tickToCross: AnimatedVectorDrawable
    private lateinit var crossToTick: AnimatedVectorDrawable
    private var tick = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        tickCross = findViewById<View>(R.id.tick_cross) as ImageView
        tickToCross = getDrawable(R.drawable.avd_tick_to_cross) as AnimatedVectorDrawable
        crossToTick = getDrawable(R.drawable.avd_cross_to_tick) as AnimatedVectorDrawable

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            animate(tickCross)
        }
    }

    fun animate(view: View?) {
        val drawable: AnimatedVectorDrawable = if (tick) tickToCross else crossToTick
        tickCross.setImageDrawable(drawable)
        drawable.start()
        tick = !tick
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}