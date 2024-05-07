package com.example.dogapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.core.content.ContextCompat
import com.example.dogapp.R
import com.example.dogapp.databinding.FragmentLoginBinding

// TODO: Rename parameter arguments, choose names that match

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controladores()
    }

    private fun controladores(){
        /*binding.btnIniciar.setOnClickListener {
            biometricAuth()
        }*/
    }

    private fun biometricAuth(){
        val manager = BiometricManager.from(this.requireContext())
        when (manager.canAuthenticate(BIOMETRIC_STRONG)) {
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                Toast.makeText(context,"Hardware no disponible",Toast.LENGTH_LONG).show()
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                Toast.makeText(context,"No HW disponible",Toast.LENGTH_LONG).show()
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                Toast.makeText(context,"No configurado Huella",Toast.LENGTH_LONG).show()
            }
            BiometricManager.BIOMETRIC_SUCCESS -> {
                Toast.makeText(context,"Satisfactorio",Toast.LENGTH_LONG).show()
            }
            else -> {
                Toast.makeText(context,"Error fatal",Toast.LENGTH_LONG).show()
            }
        }

        val promptInfo = PromptInfo.Builder()
            .setTitle("Autenticacion con Biometría")
            .setDescription("Ingrese su huella digital")
            .setAllowedAuthenticators(BIOMETRIC_STRONG)
            .setNegativeButtonText("CANCEL")


        val executor = ContextCompat.getMainExecutor(this.requireContext())
        val prompt = BiometricPrompt (
            this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(context,errString,Toast.LENGTH_LONG).show()
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(context,"Autenticacion exitosa",Toast.LENGTH_LONG).show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(context,"autenticacion fallida",Toast.LENGTH_LONG).show()
                }
            }
        )

        prompt.authenticate(promptInfo.build())
    }
}