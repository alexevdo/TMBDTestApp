package com.sano.tmdbtestapp.presentation

import android.content.Context
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.sano.tmdbtestapp.R

abstract class BaseFragment(@LayoutRes contentLayoutId: Int): Fragment(contentLayoutId) {

    var progressBar: ProgressBar? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        progressBar = activity?.findViewById(R.id.progress)
    }
}