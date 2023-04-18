package ___PACKAGE_NAME___.domain.post.usecase

import com.htecgroup.core.test.CoreTest
import ___PACKAGE_NAME___.domain.post.Post
import ___PACKAGE_NAME___.domain.post.PostRepository
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

/**
 * Test for [UpdatePost]
 */
class UpdatePostTest : CoreTest() {

    private companion object {
        val POST = Post()
    }

    private val postRepository: PostRepository = mockk(relaxed = true)
    private val updatePost = UpdatePost(postRepository)

    @Test
    fun execute() {
        runBlocking { updatePost(POST) }

        coVerify { postRepository.updatePost(POST) }
        confirmVerified(postRepository)
    }
}
