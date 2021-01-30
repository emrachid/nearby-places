package com.example.findnearbyplaces.util

import android.app.Activity
import android.view.View
import com.example.findnearbyplaces.R
import com.google.android.material.snackbar.Snackbar

object AlertDialog {
    /**
     * Shows a [Snackbar] using `text`.
     *
     * @param text The Snackbar text.
     */
    fun showSnackbar(activity: Activity, text: String) {
        val container: View = activity.findViewById(R.id.home_container)
        Snackbar.make(container, text, Snackbar.LENGTH_LONG).show()
    }

    /**
     * Shows a [Snackbar].
     *
     * @param mainTextStringId The id for the string resource for the Snackbar text.
     * @param actionStringId   The text of the action item.
     * @param listener         The listener associated with the Snackbar action.
     */
    fun showSnackbar(activity: Activity,
                             mainTextStringId: Int, actionStringId: Int,
                             listener: View.OnClickListener
    ) {
        Snackbar.make(
            activity.findViewById(android.R.id.content),
            activity.getString(mainTextStringId),
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction(activity.getString(actionStringId), listener).show()
    }
}