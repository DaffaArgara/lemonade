package id.dafa.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.dafa.lemonade.ui.theme.LemonadeTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }
    val requiredSqueezes = Random.nextInt(2, 5)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (currentStep) {
            1 -> {
                LemonImageAndText(
                    imageResourceId = R.drawable.lemon_tree,
                    contentDescription = stringResource(R.string.lemon_tree_content_description),
                    textResourceId = R.string.tap_lemon_tree,
                    onImageClick = { currentStep = 2 }
                )
            }
            2 -> {
                LemonImageAndText(
                    imageResourceId = R.drawable.lemon_squeeze,
                    contentDescription = stringResource(R.string.lemon_content_description),
                    textResourceId = R.string.tap_lemon,
                    onImageClick = {
                        squeezeCount++
                        if (squeezeCount >= requiredSqueezes) {
                            currentStep = 3
                            squeezeCount = 0
                        }
                    }
                )
            }
            3 -> {
                LemonImageAndText(
                    imageResourceId = R.drawable.lemon_drink,
                    contentDescription = stringResource(R.string.lemonade_content_description),
                    textResourceId = R.string.tap_lemonade,
                    onImageClick = { currentStep = 4 }
                )
            }
            4 -> {
                LemonImageAndText(
                    imageResourceId = R.drawable.lemon_restart,
                    contentDescription = stringResource(R.string.empty_glass_content_description),
                    textResourceId = R.string.tap_glass,
                    onImageClick = { currentStep = 1 }
                )
            }
        }
    }
}

@Composable
fun LemonImageAndText(
    imageResourceId: Int,
    contentDescription: String,
    textResourceId: Int,
    onImageClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageResourceId),
            contentDescription = contentDescription,
            modifier = Modifier
                .size(200.dp)
                .clickable { onImageClick() }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(textResourceId),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}