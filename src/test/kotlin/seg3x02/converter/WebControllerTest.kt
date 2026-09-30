package seg3x02.converter

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.model
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.view

@WebMvcTest(WebController::class)
class WebControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun requestToHome() {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk)
            .andExpect(view().name("home"))
    }

    @Test
    fun testAddition() {
        mockMvc.perform(
            get("/convert")
                .param("a", "10.5")
                .param("b", "4.5")
                .param("operation", "add")
        )
            .andExpect(status().isOk)
            .andExpect(model().attribute("result", "15.00"))
            .andExpect(model().attribute("error", ""))
            .andExpect(view().name("home"))
    }

    @Test
    fun testSubtraction() {
        mockMvc.perform(
            get("/convert")
                .param("a", "20.0")
                .param("b", "8.0")
                .param("operation", "subtract")
        )
            .andExpect(status().isOk)
            .andExpect(model().attribute("result", "12.00"))
            .andExpect(model().attribute("error", ""))
            .andExpect(view().name("home"))
    }

    @Test
    fun testMultiplication() {
        mockMvc.perform(
            get("/convert")
                .param("a", "6.0")
                .param("b", "7.0")
                .param("operation", "multiply")
        )
            .andExpect(status().isOk)
            .andExpect(model().attribute("result", "42.00"))
            .andExpect(model().attribute("error", ""))
            .andExpect(view().name("home"))
    }

    @Test
    fun testDivision() {
        mockMvc.perform(
            get("/convert")
                .param("a", "10.0")
                .param("b", "4.0")
                .param("operation", "divide")
        )
            .andExpect(status().isOk)
            .andExpect(model().attribute("result", "2.50"))
            .andExpect(model().attribute("error", ""))
            .andExpect(view().name("home"))
    }

    @Test
    fun testDivisionByZero() {
        mockMvc.perform(
            get("/convert")
                .param("a", "10.0")
                .param("b", "0")
                .param("operation", "divide")
        )
            .andExpect(status().isOk)
            .andExpect(model().attribute("error", "DivisionByZero"))
            .andExpect(view().name("home"))
    }

    @Test
    fun testArithmeticFormatError() {
        mockMvc.perform(
            get("/convert")
                .param("a", "invalid_number")
                .param("b", "5")
                .param("operation", "add")
        )
            .andExpect(status().isOk)
            .andExpect(model().attribute("error", "ArithmeticFormatError"))
            .andExpect(view().name("home"))
    }

    @Test
    fun testUnknownOperationFormatError() {
        mockMvc.perform(
            get("/convert")
                .param("a", "5")
                .param("b", "5")
                .param("operation", "unknown_op")
        )
            .andExpect(status().isOk)
            .andExpect(model().attribute("error", "OperationFormatError"))
            .andExpect(view().name("home"))
    }
}