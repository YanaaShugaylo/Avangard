package com.avangard.bratstvo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.avangard.bratstvo.base.root.presentation.BaseFragmentDelegate
import com.avangard.bratstvo.base.root.presentation.model.ToolbarButtonsStates
import com.avangard.bratstvo.databinding.FragmentPointsDetailBinding
import com.avangard.bratstvo.databinding.FragmentSupportBinding
import com.jivosite.sdk.Jivo
import com.jivosite.sdk.ui.chat.JivoChatFragment


class SupportFragment : Fragment() {

    private lateinit var baseFragmentDelegate: BaseFragmentDelegate
    private var binding: FragmentSupportBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            Jivo.init(
                appContext = it,
                widgetId = "xXxXxxXxXx"
            )
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.jivoBtn)?.run {

                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, JivoChatFragment())
                    .commit()

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSupportBinding.inflate(inflater)

        baseFragmentDelegate = BaseFragmentDelegate(
            getString(R.string.support_title),
            ToolbarButtonsStates.MAIN_STATE,
            View.VISIBLE
        )

        baseFragmentDelegate.initialize()

        return binding?.root
    }


}