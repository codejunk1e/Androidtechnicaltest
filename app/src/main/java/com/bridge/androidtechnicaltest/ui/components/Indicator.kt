import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun LoadingIndicator(indicatorSize: LoadingIndicatorSize = LoadingIndicatorSize.BIG) {
    Spacer(Modifier.height(8.dp))
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        val isSmallIndicatorSize = indicatorSize == LoadingIndicatorSize.SMALL
        CircularProgressIndicator(
            modifier =  if (isSmallIndicatorSize) Modifier.size(24.dp) else Modifier,
            strokeWidth = if (isSmallIndicatorSize) 2.dp else ProgressIndicatorDefaults.CircularStrokeWidth,
        )
    }
    Spacer(Modifier.height(8.dp))
}

@Composable
fun ErrorItem(tryAgainClicked: () -> Unit) {
    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Error while loading. Please try again", color = Color.Red, fontWeight = FontWeight.Bold)
        Button(
            onClick = { tryAgainClicked() },
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray
            )
        ){
            Text("Try again", color = Color.Black)
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

enum class LoadingIndicatorSize {
    BIG,
    SMALL
}