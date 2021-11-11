package com.lennydennis.chatapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.*


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        val dataStore = DataStore()

        val accountSettingPreference =
            findPreference<Preference>(getString(R.string.key_account_settings))
        accountSettingPreference?.setOnPreferenceClickListener {
            val navHostFragment =
                activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_frag) as NavHostFragment
            val navController = navHostFragment.navController
            val action = SettingsFragmentDirections.actionSettingsToAccSettings()
            navController.navigate(action)
            true
        }

        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(context)

        val autoReplyTime = sharedPreference.getString(getString(R.string.key_auto_reply_time), "")
        val autoDownload = sharedPreference.getBoolean(getString(R.string.key_auto_download), false)

        val statusPreference = findPreference<EditTextPreference>(getString(R.string.key_status))
        statusPreference?.setOnPreferenceChangeListener { preference, newValue ->
            if (newValue.toString().contains("bad")) {
                Toast.makeText(context, "Inappropriate status", Toast.LENGTH_SHORT).show()
                false
            } else {
                true
            }
        }

        val notificationPreference =
            findPreference<Preference>(getString(R.string.key_new_msg_notif))
        notificationPreference?.summaryProvider =
            Preference.SummaryProvider<SwitchPreference> { switchPref ->
                if (switchPref?.isChecked!!) {
                    "Status On"
                } else {
                    "Status Off"
                }
            }
        notificationPreference?.preferenceDataStore = dataStore

        val isNotificationEnabled = dataStore.getBoolean(getString(R.string.key_new_msg_notif),false)
    }

    class DataStore : PreferenceDataStore() {
        override fun getBoolean(key: String?, defValue: Boolean): Boolean {
            if (key == "key_new_msg_notif") {
                Log.d("Settings Fragment", "Get boolean for $key")
            }
            return defValue
        }

        override fun putBoolean(key: String?, value: Boolean) {
            if (key == "key_new_msg_notif") {
                Log.d("Settings Fragment", "Put boolean for $key with new value $value")
            }
        }
    }

}