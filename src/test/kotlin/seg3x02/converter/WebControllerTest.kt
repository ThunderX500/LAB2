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
    fun celsiusToFahrenheitConversion() {
        mockMvc.perform(
            get("/convert")
                .param("celsius", "0")
                .param("fahrenheit", "")
                .param("operation", "CtoF")
        )
            .andExpect(status().isOk)
            .andExpect(model().attribute("fahrenheit", "32.00"))
            .andExpect(view().name("home"))
    }
}
