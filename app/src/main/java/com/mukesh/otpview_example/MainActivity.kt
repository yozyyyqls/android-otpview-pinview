package com.mukesh.otpview_example

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import com.mukesh.*
import com.mukesh.otpview_example.ui.theme.ComposeOTPViewTheme

class MainActivity : ComponentActivity() {
    private val otpTextModel: OtpTextViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate()")

        val otpTextObserver = Observer<String> { newOtp ->
            Log.d(TAG, "otp text: $newOtp")
        }

        otpTextModel.currentOtpText.observe(this@MainActivity, otpTextObserver)

        setContent {
            Log.d(TAG, "setContent")

            ComposeOTPViewTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF3F51B5)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Verification Code",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        modifier = Modifier.padding(32.dp),
                        textAlign = TextAlign.Center,
                        text = "Please type the verification code sent to +xxxxxxxxxxxx",
                        color = Color.White
                    )
                    OtpView(
                        otpTextViewModel = otpTextModel,
                        type = OTP_VIEW_TYPE_BORDER,
                        password = true,
                        containerSize = 48.dp,
                        passwordChar = "•",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        charColor = Color.White
//                        onOtpTextChange = {
//                            otpValueState!!.otpText = it
//                        }
                    )

                    TextButton(modifier = Modifier.padding(32.dp), onClick = {
                        if (otpTextModel.currentOtpText.value == "1234") {
                            Toast.makeText(this@MainActivity, "OTP is correct", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast.makeText(this@MainActivity, "OTP is wrong", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }) {
                        Text(text = "Validate", color = Color.White)
                    }
                }
            }
        }
        Log.d(TAG, "end of onCreate()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop()")
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
