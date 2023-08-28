package com.avangard.bratstvo.start.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.avangard.bratstvo.base.extensions.presentation.fragment.hideKeyboard
import com.avangard.bratstvo.base.root.presentation.BaseFragmentDelegate
import com.avangard.bratstvo.base.root.presentation.model.ToolbarButtonsStates
import com.avangard.bratstvo.databinding.StartFragmentBinding
import com.avangard.bratstvo.start.EditTextInputFilter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.regex.Pattern

class StartFragment : Fragment() {

    private lateinit var baseFragmentDelegate: BaseFragmentDelegate
    private var binding: StartFragmentBinding? = null
    private val viewModel by viewModel<StartViewModel>()

    private val cyrillicRegex = Pattern.compile("[Ѐ-ӿ]").toRegex()
    private val whiteSpaceRegex = Pattern.compile("\\s+").toRegex()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = StartFragmentBinding.inflate(inflater)

        baseFragmentDelegate = BaseFragmentDelegate(
            "",
            ToolbarButtonsStates.COMMON_STATE
        )

        baseFragmentDelegate.initialize()

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let { parent ->
            parent.root.setOnClickListener {
                hideKeyboard()
            }

            parent.startActionButton.let { button ->
                button.setOnClickListener {
                    button.isEnabled = false

                    viewModel.onLoginButtonClick(
                        parent.startLoginEt.text.toString(),
                        parent.startPasswordEt.text.toString()
                    )
                }
            }

            parent.startLoginEt.filters = arrayOf(EditTextInputFilter(cyrillicRegex))
            parent.startPasswordEt.filters = arrayOf(EditTextInputFilter(whiteSpaceRegex))
        }

        viewModel.authorizationResult.observe(viewLifecycleOwner) {
            it?.let {
                if (it.isAuthSucceed) {
                    navigateNext(it.isFirstTime)
                } else {
                    binding?.startActionButton?.isEnabled = true

                    Log.i("myLog", "errorMessage = ${it.errorMessage}")
                    if (it.errorMessage != null) {
                        Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun navigateNext(isFirstTime: Boolean) {
        val action = if (isFirstTime) {
            StartFragmentDirections.actionStartFragmentToAboutUserFragment()
        } else {
            StartFragmentDirections.actionStartFragmentToHomeFragment()
        }

        binding?.root?.findNavController()?.navigate(action)
    }
}