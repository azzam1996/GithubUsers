package com.azzam.githubusers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class CoroutinesTestRule<T : CoroutineDispatcher>(
    val dispatcher: T,
) : TestWatcher() {

    companion object {

        /**
         * Since `dispatcher` has a generic type, we can't give it a default value. This is here so it feels like there's
         * still a default constructor that receives no parameters, since this can be called as if it was a constructor.
         */
        operator fun invoke() = CoroutinesTestRule(Dispatchers.Unconfined)
    }

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        (dispatcher as? TestCoroutineDispatcher)?.cleanupTestCoroutines()
    }
}
