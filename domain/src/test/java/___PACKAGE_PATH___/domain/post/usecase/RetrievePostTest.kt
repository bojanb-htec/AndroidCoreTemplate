package ___PACKAGE_NAME___.domain.post.usecase

import com.htecgroup.core.test.CoreTest
import ___PACKAGE_NAME___.domain.post.Post
import ___PACKAGE_NAME___.domain.post.PostRepository
import ___PACKAGE_NAME___.domain.user.UserRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

/**
 * Test for [RetrievePost]
 */
class RetrievePostTest : CoreTest() {

    private companion object {
        const val POST_ID = 1
        const val USER_ID = 2
    }

    private val postRepository: PostRepository = mockk(relaxed = true)
    private val userRepository: UserRepository = mockk(relaxed = true)
    private val retrievePost = RetrievePost(postRepository, userRepository)

    @Test
    fun execute() {
        val post = Post(POST_ID, userId = USER_ID)
        every { postRepository.getPost(any()) } returns flow { emit(Result.success(post)) }
        coEvery { userRepository.fetchUser(any()) } returns Result.success(Unit)

        runBlocking { retrievePost(POST_ID).collect() }

        verify { postRepository.getPost(POST_ID) }
        confirmVerified(postRepository)
        coVerify { userRepository.fetchUser(USER_ID) }
        confirmVerified(userRepository)
    }
}
