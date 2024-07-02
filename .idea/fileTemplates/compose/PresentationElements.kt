#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME}
    #set( $presentationIndex = $PACKAGE_NAME.indexOf(".presentation") )
    #if( $presentationIndex >= 0 )
        #set( $rootPackage = $PACKAGE_NAME.substring(0, $presentationIndex) )
    #end
#end
#parse("File Header.java")

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.text.style.TextAlign.Companion
import androidx.navigation.NavHostController
import com.htecgroup.androidcore.presentation.compose.AnimateSlide
import com.htecgroup.androidcore.presentation.compose.DefaultTopBar
import com.htecgroup.androidcore.presentation.compose.composer.DestinationComposer
import com.htecgroup.androidcore.presentation.compose.navigation.Destination
import ${rootPackage}.presentation.R
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class ${SCREEN}DestinationComposer @Inject constructor() : DestinationComposer<${SCREEN}ViewModel>() {

    override val topBarVisible = true
    override val titleResId = "title_${SCREEN}"
    override val destination: Destination = "" //TODO: Add destination screen
    override val viewModelClass: Class<${SCREEN}ViewModel> = ${SCREEN}ViewModel::class.java

    @Composable
    override fun Content(navController: NavHostController, viewModel: ${SCREEN}ViewModel) {

        AnimateSlide(navController.isScreenVisible, -1) {
            ${SCREEN}Screen(viewModel.uiState)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun TopBarContent(
        upButton: State<BarButton?>,
        contextButton: State<BarButton?>
    ) {
        DefaultTopBar(
            titleResId = titleResId,
            topAppBarColors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
            ),
            contextButton = contextButton,
            textAlign = Companion.Center
        )
    }
}