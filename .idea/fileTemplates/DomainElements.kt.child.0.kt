#set ($entityLower = $ENTITY.toLowerCase())
#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME}.${entityLower}.usecase
    #set( $presentationIndex = $PACKAGE_NAME.indexOf(".domain") )
    #if( $presentationIndex >= 0 )
        #set( $rootPackage = $PACKAGE_NAME.substring(0, $presentationIndex) )
    #end
#end
#parse("File Header.java")

## Convert to lower case only first char:**
#set( $entityLowerFirst= $ENTITY.replaceFirst( $ENTITY.substring(0, 1), $ENTITY.substring(0, 1).toLowerCase() ) )
## Convert to lower case only first char:**
#set( $useCaseLowerFirst= $USE_CASE.replaceFirst( $USE_CASE.substring(0, 1), $USE_CASE.substring(0, 1).toLowerCase() ) )

import com.htec.core.domain.CoreUseCase
import com.htec.core.domain.IUseCase
import com.htec.core.domain.Result
import ${rootPackage}.domain.${entityLower}.${ENTITY}Repository
import javax.inject.Inject

class ${USE_CASE}UseCase @Inject constructor(
    private val ${entityLowerFirst}Repository: ${ENTITY}Repository
) : CoreUseCase(), IUseCase<Result<Unit>> {

    override suspend fun invoke(): Result<Unit> =
        ${entityLowerFirst}Repository.${useCaseLowerFirst}()
}
