#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME}
    #set( $presentationIndex = $PACKAGE_NAME.indexOf(".domain") )
    #if( $presentationIndex >= 0 )
        #set( $rootPackage = $PACKAGE_NAME.substring(0, $presentationIndex) )
    #end
#end
#parse("File Header.java")

#set( $entityLowerCase= $ENTITY.toLowerCase())
#set( $entityLowerFirst= $ENTITY.replaceFirst( $ENTITY.substring(0, 1), $ENTITY.substring(0, 1).toLowerCase() ) )
#set( $useCaseLowerFirst= $USE_CASE.replaceFirst( $USE_CASE.substring(0, 1), $USE_CASE.substring(0, 1).toLowerCase() ) )

import com.htec.core.domain.CoreUseCase
import com.htec.core.domain.IFlowUseCase
import com.htec.core.domain.Result
import ${rootPackage}.domain.${entityLowerCase}.${ENTITY}Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ${USE_CASE}UseCase @Inject constructor(
    private val ${entityLowerFirst}Repository: ${ENTITY}Repository
) : CoreUseCase(), IFlowUseCase<Result<Unit>> {

    override fun invoke(): Flow<Result<Unit>> =
        ${entityLowerFirst}Repository.${useCaseLowerFirst}()
}
