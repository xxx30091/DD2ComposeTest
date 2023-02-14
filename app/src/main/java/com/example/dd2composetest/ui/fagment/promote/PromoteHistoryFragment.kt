package com.example.dd2composetest.ui.fagment.promote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dd2composetest.R

@Deprecated ("All in fans promote")
class PromoteHistoryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_promote_history, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = PromoteHistoryFragment()
    }
}