package com.emrys.androidlab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val callback = object: BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
//                showMessage("$errorCode :: $errString")
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
//                showMessage("Authentication failed for an unknown reason")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
//                showMessage("Authentication was successful")
            }
        }

        val executor = ContextCompat.getMainExecutor(requireContext())

        val biometricPrompt = BiometricPrompt(this, executor, callback)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            val biometricManager = BiometricManager.from(requireContext())
            when (biometricManager.canAuthenticate()) {
                BiometricManager.BIOMETRIC_SUCCESS -> {
                    val promptInfo = BiometricPrompt.PromptInfo.Builder()
                        .setTitle("My App's Authentication")
                        .setSubtitle("Please login to get access")
                        .setNegativeButtonText("negative")
                        .setDescription("My App is using Android biometric authentication")
                        .setDeviceCredentialAllowed(false)
                        .setConfirmationRequired(false)
                        .build()
                    biometricPrompt.authenticate(promptInfo)
                }
//                    Log.d("MY_APP_TAG", "App can authenticate using biometrics.")
                BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {

                }
//                    Log.e("MY_APP_TAG", "No biometric features available on this device.")
                BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {

                }
//                    Log.e("MY_APP_TAG", "Biometric features are currently unavailable.")
                BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {

                }
//                    Log.e("MY_APP_TAG", "The user hasn't associated " +
//                            "any biometric credentials with their account.")
            }
        }
    }
}