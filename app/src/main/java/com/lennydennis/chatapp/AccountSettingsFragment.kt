package com.lennydennis.chatapp

import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.preference.MultiSelectListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceFragmentCompat


class AccountSettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

        val  publicInfoPref = MultiSelectListPreference(context)
        publicInfoPref.entries = resources.getStringArray(R.array.entries_public_info)
        publicInfoPref.entryValues = resources.getStringArray(R.array.values_public_info)
        publicInfoPref.key = getString(R.string.key_public_info)
        publicInfoPref.title = getString(R.string.title_public_info)
        publicInfoPref.isIconSpaceReserved = false

        val logOutPref = Preference(context)
        logOutPref.key = getString(R.string.key_log_out)
        logOutPref.title = getString(R.string.title_log_out)
        logOutPref.isIconSpaceReserved = false

        val deleteAccountPref = Preference(context)
        deleteAccountPref.key = getString(R.string.key_delete_account)
        deleteAccountPref.title = getString(R.string.title_delete_account)
        deleteAccountPref.summary = getString(R.string.summary_delete_account)
        deleteAccountPref.icon = ResourcesCompat.getDrawable(resources,android.R.drawable.ic_menu_delete,null)

        val privacyCategory = PreferenceCategory(context)
        privacyCategory.title = getString(R.string.title_privacy)
        privacyCategory.isIconSpaceReserved = false

        val securityCategory = PreferenceCategory(context)
        securityCategory.title = getString(R.string.title_security)
        securityCategory.isIconSpaceReserved = false

        val prefScreen = preferenceManager.createPreferenceScreen(context)

        prefScreen.addPreference(privacyCategory)
        prefScreen.addPreference(securityCategory)

        privacyCategory.addPreference(publicInfoPref)
        securityCategory.addPreference(logOutPref)
        securityCategory.addPreference(deleteAccountPref)

        preferenceScreen = prefScreen

    }
}
