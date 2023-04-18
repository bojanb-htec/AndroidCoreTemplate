package ___PACKAGE_NAME___.domain.post.usecase

import com.htecgroup.core.test.CoreTest
import ___PACKAGE_NAME___.domain.post.PostRepository
import io.mockk.coEvery
import io.mockk.coVerifyAll
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

/**
 * Test for [RetrievePosts]
 */
class RetrievePostsTest : CoreTest() {

    private val postRepository: PostRepository = mockk(relaxed = true)
    private val retrievePosts = RetrievePosts(postRepository)

    @Test
    fun execute() {
        coEvery { postRepository.getPosts() } returns flow { emit(Result.success(listOf())) }
        runBlocking { retrievePosts().collect() }

        coVerifyAll {
            postRepository.getPosts()
            postRepository.fetchPosts()
        }
        confirmVerified(postRepository)
    }
}
