# Uniwap Routing SDK

This is an SDK to do Uniswap V3 swaps in android.

### How to add the Uniwap Routing SDK into your app

First, go into your `settings.gradle` file and add the line `maven { url 'https://jitpack.io' }` to the `pluginManagement` and the `dependencyResolutionManagement` section like this:

```groovy
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

Then go to your module-level `build.gradle` file and add the following line to the `dependencies` section:

```groovy
implementation 'com.github.EthereumPhone:Uniswap-routing-sdk:0.0.1'
```

### How to initialize SDK

```kotlin
// Initialize Uniswap sdk
val uniswapSDK = UniswapRoutingSDK(
    context = this,
    web3RPC = "YOUR_WEB3_RPC_URL_HERE"
)
```

### How to make a swap from ETH to DAI

```kotlin
// Create dai token for output

val daiToken = Token(
    chainId = 1,
    address = "0x6b175474e89094c44da98b954eedeac495271d0f",
    decimals = 18,
    symbol = "DAI",
    name = "Dai Stablecoin"
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
```
