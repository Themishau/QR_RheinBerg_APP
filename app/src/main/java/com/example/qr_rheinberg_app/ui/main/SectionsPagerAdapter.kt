package com.example.qr_rheinberg_app.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.qr_rheinberg_app.R

private const val QR_Code = "QR_Code"
private const val RheinBergAuslastung = "RheinBergAuslastung"
private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return QR_CodeScan.newInstance(position + 1)
        } else if (position == 1) {
            return BIItemFragment.newInstance(position + 1)
        } else if (position == 2) {
            return RheinBergAuslastungFragment.newInstance(RheinBergAuslastung, RheinBergAuslastung)

        } else
            return PlaceholderFragment.newInstance(position + 1)
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 3 total pages.
        return 3
    }
}