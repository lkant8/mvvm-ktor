package com.lucky.mycomposable

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.lucky.mycomposable.ui.theme.MyComposableTheme
import com.lucky.mycomposable.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

//    private val viewmodel  by viewModel<MainViewModel>()
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
                    LaunchedEffect(true){
                        viewmodel.getPhoto()
                        viewmodel.eventData.collect{
                            showToast(it)
                        }
                    }
                }
            }
        }


    }


    fun showToast(s:String?){
        Toast.makeText(this@MainActivity,"$s",Toast.LENGTH_SHORT).show()
    }


}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    checks(3,40,::sum)
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
fun sum(a: Int, b: Int): Int {
    return (a + b)
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