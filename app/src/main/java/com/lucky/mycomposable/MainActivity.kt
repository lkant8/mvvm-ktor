package com.lucky.mycomposable

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
import com.lucky.mycomposable.data.PhotoDTO
import com.lucky.mycomposable.ui.theme.MyComposableTheme
import com.lucky.mycomposable.network.Constants
import com.lucky.mycomposable.network.ktorHttpClient
import com.lucky.mycomposable.viewmodel.MainViewModel
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val viewmodel :MainViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposableTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                    viewmodel.getPhoto()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    checks(3,40,::sum)
    checkAPI()
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
fun sum(a: Int, b: Int): Int {
    return (a + b)
}
fun checkAPI(){
    CoroutineScope(Dispatchers.Main).launch {
        val aa = ktorHttpClient.get<PhotoDTO>("${Constants.BASE}/photos"){

        }
        println("$aa")
    }

}

fun checks(a: Int,b: Int,add:(Int,Int)->Int){
    val sum =add(a,b)
    println("the total sum of $sum")
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyComposableTheme {
        Greeting("Android")
    }
}