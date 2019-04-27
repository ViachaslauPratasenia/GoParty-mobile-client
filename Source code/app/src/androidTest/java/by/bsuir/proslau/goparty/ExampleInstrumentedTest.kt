package by.bsuir.proslau.goparty

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import by.bsuir.proslau.goparty.logic.events.EventLogicManager
import by.bsuir.proslau.goparty.server.TypeOfServerError
import by.bsuir.proslau.goparty.utils.Base64Converter
import org.junit.Assert

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private var preferences = ""
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("by.bsuir.proslau.goparty", appContext.packageName)
    }

    @Test
    fun getStartPositionFromPageTest() {
        val currentPage = 5
        val count = 15
        val expectedStartPos = count * (currentPage - 1) + 1
        assertEquals(EventLogicManager.getStartPositionByPage(currentPage, count), expectedStartPos)
    }

    @Test
    fun checkErrorTest() {
        val error_404 = EventLogicManager.checkError(404, "")
        val error_401 = EventLogicManager.checkError(401, "")
        val error_500 = EventLogicManager.checkError(500, "")
        val error_101 = EventLogicManager.checkError(101, "")

        assertEquals(error_404, true)
        assertEquals(error_401, true)
        assertEquals(error_500, false)
        assertEquals(error_101, false)
    }
}
