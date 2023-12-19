package com.android.network_connectivity

import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.android.network_connectivity.ui.theme.Network_ConnectivityTheme

class MainActivity : ComponentActivity() {

    lateinit var networkCallback: ConnectivityManager.NetworkCallback
    lateinit var networkMonitor: NetworkMonitor
//    lateinit var mViewModel: mViewModel

    override fun onDestroy() {
        super.onDestroy()
        networkMonitor.unregisterNetworkCallback(networkCallback)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var isConnected by mutableStateOf(false)
//        var isConnected:Boolean = false
        val networkCallback = object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                Log.d("Network: ","Connected")
                Log.d("Network: ",isConnected.toString())
                isConnected = true
//                mViewModel.hideNetwork()


            }

            override fun onLost(network: Network) {
                Log.d("Network: ","Disconnected")
                Log.d("Network: ",isConnected.toString())
                isConnected = false

//                mViewModel.showNetwork()

            }
        }

        networkMonitor = NetworkMonitor(applicationContext)
        networkMonitor.registerNetworkCallback(networkCallback)
//        Log.d("temp",networkMonitor.isNetworkAvailable(applicationContext).toString())


        setContent {
            Network_ConnectivityTheme {
                    temp(isConnected)
            }
        }
    }
}

@Composable
fun temp(isConnected: Boolean) {
    if(isConnected) {
Log.d("am","cncted")
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Check internet connection",
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Blue,
                textAlign = TextAlign.Center
            )
        }
    }else{
    netErrorDialog()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Network_ConnectivityTheme {
//        temp(isConnected)
    }
}

