#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME}
    #set( $domainDirIndex = $PACKAGE_NAME.indexOf(".domain") )
    #if( $domainDirIndex >= 0 )
        #set( $rootPackage = $PACKAGE_NAME.substring(0, $domainDirIndex) )
    #end
#end
#parse("File Header.java")

#set( $entityLowerCase= $ENTITY.toLowerCase())
#set( $entityLowerFirst= $ENTITY.replaceFirst( $ENTITY.substring(0, 1), $ENTITY.substring(0, 1).toLowerCase() ) )
#set( $useCaseLowerFirst= $USE_CASE.replaceFirst( $USE_CASE.substring(0, 1), $USE_CASE.substring(0, 1).toLowerCase() ) )

import com.htecgroup.androidcore.domain.CoreUseCase
import com.htecgroup.androidcore.domain.IUseCase
import ${rootPackage}.domain.${entityLowerCase}.${ENTITY}Repository
import javax.inject.Inject

class ${USE_CASE}UseCase @Inject constructor(
    private val ${entityLowerFirst}Repository: ${ENTITY}Repository
) : CoreUseCase(), IUseCase<Result<Unit>> {

    override suspend fun invoke(): Result<Unit> =
        ${entityLowerFirst}Repository.${useCaseLowerFirst}()
}
