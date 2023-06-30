package org.ethosmobile.uniswaproutingsdk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.ethosmobile.uniswap_routing_sdk.Token
import org.ethosmobile.uniswap_routing_sdk.UniswapRoutingSDK
import org.ethosmobile.uniswaproutingsdk.ui.theme.UniswapRoutingSDKTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Uniswap sdk
        val uniswapSDK = UniswapRoutingSDK(
            context = this,
            web3RPC = "YOUR_WEB3_RPC_URL_HERE"
        )

        // Create dai token for output
        val daiToken = Token(
            1,
            "0x6b175474e89094c44da98b954eedeac495271d0f",
            18,
            "DAI",
            "Dai Stablecoin"
        )

        uniswapSDK.getCalldata(
            inputToken = UniswapRoutingSDK.ETH_MAINNET, // Use ETH as input
            outputToken = daiToken,
            amountIn = 2.0,
            receiverAddress = "0x3a4e6eD8B0F02BFBfaA3C6506Af2DB939eA5798c" // mhaas.eth
        ).whenComplete { t, u ->
            // This calldata can be used with the WalletSDK
            println("Calldata: $t")
        }

        setContent {
            UniswapRoutingSDKTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UniswapRoutingSDKTheme {
        Greeting("Android")
    }
}