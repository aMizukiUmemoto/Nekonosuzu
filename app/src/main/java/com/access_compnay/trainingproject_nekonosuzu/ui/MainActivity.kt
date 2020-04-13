package com.access_compnay.trainingproject_nekonosuzu.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.access_compnay.trainingproject_nekonosuzu.R
import com.access_compnay.trainingproject_nekonosuzu.ui.QrCaptureActivity.Companion.EXTRA_VALUE
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            // TODO: QrCaptureActivityを起動し、得られたコードがEquipmentListFragmentのリストに含まれるIDであれば、それをタップしたときと同様にopenDetailする
            val openIntent = Intent(this@MainActivity, QrCaptureActivity::class.java)
            startActivityForResult(openIntent,9)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val res = data?.getStringExtra(EXTRA_VALUE)
        val openIntent = Intent(this, EquipmentDetailActivity::class.java).apply { putExtra(EquipmentDetailActivity.EXTRA_ID, res) }
        startActivity(openIntent)
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
