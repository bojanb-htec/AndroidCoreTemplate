#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME}
    #set( $presentationIndex = $PACKAGE_NAME.indexOf(".presentation") )
    #if( $presentationIndex >= 0 )
        #set( $rootPackage = $PACKAGE_NAME.substring(0, $presentationIndex) )
    #end
#end
#parse("File Header.java")

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.htecgroup.androidcore.presentation.model.DataUiState
import com.htecgroup.androidcore.presentation.model.DataUiState.Idle
import ${rootPackage}.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ${SCREEN}Screen(uiState: DataUiState<${ENTITY_VIEW}>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.screen_padding)),
    ) {
    }
}

@Preview
@Composable
private fun ${SCREEN}ScreenPreview() {
    ${SCREEN}Screen(
        uiState = Idle()
    )
}