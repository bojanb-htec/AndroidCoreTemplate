package ___PACKAGE_NAME___.domain.post.usecase

import com.htecgroup.core.test.CoreTest
import ___PACKAGE_NAME___.domain.post.PostRepository
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

/**
 * Test for [FetchPosts]
 */
class FetchPostsTest : CoreTest() {

    private val postRepository: PostRepository = mockk(relaxed = true)
    private val fetchPosts: FetchPosts = FetchPosts(postRepository)

    @Test
    fun execute() {
        runBlocking { fetchPosts() }

        coVerify { postRepository.fetchPosts() }
        confirmVerified(postRepository)
    }
}
