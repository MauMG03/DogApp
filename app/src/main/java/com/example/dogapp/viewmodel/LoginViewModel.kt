package com.example.dogapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE
import androidx.biometric.BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED
import androidx.biometric.BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE
import androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.AuthenticationCallback
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

class LoginViewModel(application:Application) :AndroidViewModel(application) {
    val context = getApplication<Application>()

    fun biometricAuth(fragment:Fragment){
        val manager = BiometricManager.from(context)
        when (manager.canAuthenticate(BIOMETRIC_STRONG)) {
            BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                //Toast.makeText(context,"Hardware no disponible",Toast.LENGTH_LONG).show()
            }
            BIOMETRIC_ERROR_NO_HARDWARE -> {
                //Toast.makeText(context,"No HW disponible",Toast.LENGTH_LONG).show()
            }
            BIOMETRIC_ERROR_NONE_ENROLLED -> {
                //Toast.makeText(context,"No configurado Huella",Toast.LENGTH_LONG).show()
            }
            BIOMETRIC_SUCCESS -> {
                //Toast.makeText(context,"Satisfactorio",Toast.LENGTH_LONG).show()
            }
            else -> {
                //Toast.makeText(context,"Error fatal",Toast.LENGTH_LONG).show()
            }
        }

        val promptInfo = PromptInfo.Builder()
            .setTitle("Autenticacion con Biometría")
            .setDescription("Ingrese su huella digital")
            .setAllowedAuthenticators(BIOMETRIC_STRONG)
            .setNegativeButtonText("Cancelar")

        val executor = ContextCompat.getMainExecutor(context)

        val prompt = BiometricPrompt(fragment,executor,
            object : AuthenticationCallback(){
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    //Toast.makeText(context,errString,Toast.LENGTH_LONG).show()
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(context,"Autenticacion exitosa", Toast.LENGTH_LONG).show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(context,"Autenticacion Fallida",Toast.LENGTH_LONG).show()
                }
            }
        )

        prompt.authenticate(promptInfo.build())
    }
}