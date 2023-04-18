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
 * Test for [AddPost]
 */
class AddPostTest : CoreTest() {

    private companion object {
        val POST = Post()
    }

    private val postRepository: PostRepository = mockk(relaxed = true)
    private val addPost = AddPost(postRepository)

    @Test
    fun execute() {
        runBlocking { addPost(POST) }

        coVerify { postRepository.addPost(POST) }
        confirmVerified(postRepository)
    }
}
