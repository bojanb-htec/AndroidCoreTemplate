package ___PACKAGE_NAME___.domain.post.usecase

import com.htecgroup.core.test.CoreTest
import ___PACKAGE_NAME___.domain.post.PostRepository
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

/**
 * Test for [DeletePost]
 */
class DeletePostTest : CoreTest() {

    private companion object {
        const val POST_ID = 1
    }

    private val postRepository: PostRepository = mockk(relaxed = true)
    private val deletePost = DeletePost(postRepository)

    @Test
    fun execute() {
        runBlocking { deletePost(POST_ID) }

        coVerify { postRepository.deletePost(POST_ID) }
        confirmVerified(postRepository)
    }
}
