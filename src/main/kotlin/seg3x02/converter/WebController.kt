package seg3x02.converter

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.Locale

@Controller
class WebController {

    @ModelAttribute
    fun addAttributes(model: Model) {
        model.addAttribute("error", "")
        model.addAttribute("celsius", "")
        model.addAttribute("fahrenheit", "")
    }

    @RequestMapping("/")
    fun home(): String {
        return "home"
    }

    @GetMapping("/convert")
    fun doConvert(
        @RequestParam(value = "celsius", required = false, defaultValue = "") celsius: String,
        @RequestParam(value = "fahrenheit", required = false, defaultValue = "") fahrenheit: String,
        @RequestParam(value = "operation", required = false, defaultValue = "") operation: String,
        model: Model
    ): String {
        when (operation) {
            "CtoF" -> {
                try {
                    val celsiusValue = celsius.toDouble()
                    val fahrenheitValue = celsiusValue * 9 / 5 + 32
                    model.addAttribute("celsius", celsius)
                    model.addAttribute(
                        "fahrenheit",
                        String.format(Locale.US, "%.2f", fahrenheitValue)
                    )
                } catch (_: NumberFormatException) {
                    model.addAttribute("error", "CelsiusFormatError")
                    model.addAttribute("celsius", celsius)
                    model.addAttribute("fahrenheit", fahrenheit)
                }
            }

            "FtoC" -> {
                try {
                    val fahrenheitValue = fahrenheit.toDouble()
                    val celsiusValue = (fahrenheitValue - 32) * 5 / 9
                    model.addAttribute(
                        "celsius",
                        String.format(Locale.US, "%.2f", celsiusValue)
                    )
                    model.addAttribute("fahrenheit", fahrenheit)
                } catch (_: NumberFormatException) {
                    model.addAttribute("error", "FahrenheitFormatError")
                    model.addAttribute("celsius", celsius)
                    model.addAttribute("fahrenheit", fahrenheit)
                }
            }

            else -> {
                model.addAttribute("error", "OperationFormatError")
                model.addAttribute("celsius", celsius)
                model.addAttribute("fahrenheit", fahrenheit)
            }
        }

        return "home"
    }
}
