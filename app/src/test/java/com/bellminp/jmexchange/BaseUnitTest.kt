package com.bellminp.jmexchange

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
abstract class BaseUnitTest {
    val dispatcher = TestCoroutineDispatcher()

    val testScope = TestCoroutineScope(dispatcher)

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule(dispatcher)

    @Before
    open fun setup() {
        MockitoAnnotations.openMocks(this)
    }
}