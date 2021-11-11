package com.lennydennis.chatapp

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings,rootKey)

        val accountSettingPreference = findPreference<Preference>(getString(R.string.key_account_settings))
        accountSettingPreference?.setOnPreferenceClickListener {
            val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_frag) as NavHostFragment
            val navController = navHostFragment.navController
            val action = SettingsFragmentDirections.actionSettingsToAccSettings()
            navController.navigate(action)
            true
        }

        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(context)

        val autoReplyTime = sharedPreference.getString(getString(R.string.key_auto_reply_time),"")
        val autoDownload = sharedPreference.getBoolean(getString(R.string.key_auto_download),false)

        val statusPreference = findPreference<EditTextPreference>(getString(R.string.key_status))
        statusPreference?.setOnPreferenceChangeListener { preference, newValue ->
            if(newValue.toString().contains("bad")){
                Toast.makeText(context,"Inappropriate status",Toast.LENGTH_SHORT).show()
                false
            }else{
                true
            }
        }
    }

}