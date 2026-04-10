package com.ebpmedical.app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.settings)

        val prefs = getSharedPreferences(MainActivity.PREFS_NAME, MODE_PRIVATE)
        val etEndpoint = findViewById<EditText>(R.id.etApiEndpoint)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val tvHint = findViewById<TextView>(R.id.tvHint)

        val current = prefs.getString(
            MainActivity.KEY_API_ENDPOINT,
            MainActivity.DEFAULT_ENDPOINT
        ) ?: MainActivity.DEFAULT_ENDPOINT

        etEndpoint.setText(current)
        tvHint.text = "请输入您的 Vercel 部署地址\n格式：https://your-app.vercel.app/api/analyze"

        btnSave.setOnClickListener {
            val url = etEndpoint.text.toString().trim()
            if (url.startsWith("https://") && url.contains("vercel.app")) {
                prefs.edit().putString(MainActivity.KEY_API_ENDPOINT, url).apply()
                setResult(RESULT_OK)
                finish()
            } else {
                etEndpoint.error = "请输入有效的 Vercel URL（https://...vercel.app/api/analyze）"
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
