package com.example.qr_rheinberg_app.ui.main

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton
import com.example.qr_rheinberg_app.R
import com.example.qr_rheinberg_app.databinding.ActivityMainBinding
import com.example.qr_rheinberg_app.ui.login.RheinBergLogin

class MainActivity : AppCompatActivity() {

    /* wir setzen auf binding den Hauptscreen, der auch activity_main.xml ist (check per Doppelklick) */
    private lateinit var binding: ActivityMainBinding

    /* beim erstellen des des Fensters werden die Variablen, Listener usw. gesetzt. */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        /* es wird eine variable binding erstellt, die als Objekt handelt, dass
        *  Activity-Elemente halten kann. Activities sind einfach gesagt Fenster.
        *  mit setContentView, wird das Activity binding als Hauptfenster gesetzt. (das sehen wir) */
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)

        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter

        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)

        /* mit dem Klick auf den Button oben rechts, öffnet sich ein neues Fenster
        *  so wie ich es verstehe, werden die Infos aus dem noch aktiven Fenster in savedInstanceState
        *  gesichert
        *  Button eines views per Vererbung erreichbar durch einen Cast, da type Button benötigt
        *  wird: (view as Button).text */

        val login_btn: ImageButton = binding.loginButton
        login_btn.setOnClickListener { view ->
            startActivity(Intent(this, RheinBergLogin::class.java))
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        }

        val QR_scan: ImageButton = binding.QRCodeScan
        QR_scan.setOnClickListener { view ->
            startActivity(Intent(this, QR_CodeScan_Activity::class.java))
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        }
        /* Button rechts unten mit E-Mail-Icon
        * */
        val fab: FloatingActionButton = binding.fab
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}