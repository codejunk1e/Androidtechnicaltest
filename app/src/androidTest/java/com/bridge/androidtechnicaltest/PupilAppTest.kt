package com.bridge.androidtechnicaltest

import android.util.Log
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.work.Configuration
import androidx.work.testing.SynchronousExecutor
import androidx.work.testing.WorkManagerTestInitHelper
import com.bridge.androidtechnicaltest.ui.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class PupilAppTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testPupilListScreen_displaysPupils() {
        composeTestRule.onNodeWithText("John Doe").assertExists()
        composeTestRule.onNodeWithText("Nigeria").assertExists()
    }

    @Test
    fun testClickPupil_navigatesToDetailScreen() {
        composeTestRule.onNodeWithText("John Doe").performClick()
        composeTestRule.onNodeWithText("Name").assertExists()
    }

    @Test
    fun testClickAddPupil_navigatesToDetailScreen() {
        composeTestRule.onNodeWithContentDescription("Add Pupil").performClick()
        composeTestRule.onNodeWithText("Name").assertExists()
    }

    @Test
    fun testPupilDetailScreen_validatesInput() {
        composeTestRule.onNodeWithContentDescription("Add Pupil").performClick()

        composeTestRule.onNodeWithText("Name").performTextInput("A")
        composeTestRule.onNodeWithText("Country").performTextInput("")
        composeTestRule.onNodeWithText("Image URL").performTextInput("short")

        composeTestRule.onNodeWithText("Add Pupil").performClick()

        composeTestRule.onNodeWithText("Name must be at least 2 characters").assertExists()
        composeTestRule.onNodeWithText("Country must be at least 2 characters").assertExists()
        composeTestRule.onNodeWithText("Image URL must be at least 11 characters").assertExists()
    }

    @Test
    fun testPupilDetailScreen_savesPupil() {
        composeTestRule.onNodeWithContentDescription("Add Pupil").performClick()

        composeTestRule.onNodeWithText("Name").performTextInput("New Pupil")
        composeTestRule.onNodeWithText("Country").performTextInput("Ghana")
        composeTestRule.onNodeWithText("Image URL").performTextInput("https://example.com/img.jpg")

        composeTestRule.onNodeWithText("Add Pupil").performClick()

        composeTestRule.onNodeWithText("New Pupil").assertExists()
    }

    companion object {
        @JvmStatic
        @BeforeClass
        fun setupWorker() {
            val mockedContext = getInstrumentation().targetContext
            val config = Configuration.Builder()
                .setMinimumLoggingLevel(Log.DEBUG)
                .setExecutor(SynchronousExecutor())
                .build()
            WorkManagerTestInitHelper.initializeTestWorkManager(mockedContext, config)
        }
    }
}
