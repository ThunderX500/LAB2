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
        model.addAttribute("a", "")
        model.addAttribute("b", "")
        model.addAttribute("result", "")
    }

    @RequestMapping("/")
    fun home(): String {
        return "home"
    }

    @GetMapping("/convert")
    fun doConvert(
        @RequestParam(value = "a", required = false, defaultValue = "") a: String,
        @RequestParam(value = "b", required = false, defaultValue = "") b: String,
        @RequestParam(value = "result", required = false, defaultValue = "") result: String,
        @RequestParam(value = "operation", required = false, defaultValue = "") operation: String,
        model: Model
    ): String {
        // Retain form inputs across requests
        model.addAttribute("a", a)
        model.addAttribute("b", b)
        model.addAttribute("result",result)

        when (operation) {

            "add" -> {
                try {
                    val result = a.toDouble() + b.toDouble()
                    model.addAttribute("result", String.format(Locale.US, "%.2f", result))
                } catch (_: NumberFormatException) {
                    model.addAttribute("error", "ArithmeticFormatError")
                }
            }

            "subtract" -> {
                try {
                    val result = a.toDouble() - b.toDouble()
                    model.addAttribute("result", String.format(Locale.US, "%.2f", result))
                } catch (_: NumberFormatException) {
                    model.addAttribute("error", "ArithmeticFormatError")
                }
            }

            "multiply" -> {
                try {
                    val result = a.toDouble() * b.toDouble()
                    model.addAttribute("result", String.format(Locale.US, "%.2f", result))
                } catch (_: NumberFormatException) {
                    model.addAttribute("error", "ArithmeticFormatError")
                }
            }

            "divide" -> {
                try {
                    val valA = a.toDouble()
                    val valB = b.toDouble()
                    if (valB == 0.0) {
                        model.addAttribute("error", "DivisionByZero")
                    } else {
                        val result = valA / valB
                        model.addAttribute("result", String.format(Locale.US, "%.2f", result))
                    }
                } catch (_: NumberFormatException) {
                    model.addAttribute("error", "ArithmeticFormatError")
                }
            }

            else -> {
                model.addAttribute("error", "OperationFormatError")
            }
        }

        return "home"
    }
}