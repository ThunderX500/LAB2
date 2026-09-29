# SEG3502 — Lab 2

## Progress: about halfway complete (Part A / steps 1–6 of 11)

### Done
- Created the Spring Boot project structure with **Kotlin + Gradle**.
- Added **Spring Web** and **Thymeleaf** dependencies.
- Added the Spring Boot entry point: `ConverterApplication.kt`.
- Added `WebController.kt` with:
  - `GET /` → returns the `home` view.
  - `GET /convert` → handles Celsius → Fahrenheit and Fahrenheit → Celsius.
  - Model attributes for `celsius`, `fahrenheit`, and `error`.
  - Invalid-number and invalid-operation error handling.
- Added the Thymeleaf page `home.html`.
- Added `style.css`.
- Added MockMvc tests for:
  - loading `/`;
  - converting `0 °C` to `32.00 °F`.

### Left to do
- Replace/adapt the converter example into the **evaluated calculator**.
- Add two-number input parameters and the four operations: **+ − × ÷**.
- Handle invalid calculator input and division by zero.
- Adapt `home.html` from the temperature converter to the calculator.
- Adapt/add MockMvc tests for the calculator.
- Run the final Gradle tests and application, verify all four operations in the browser, and submit the final code.

> Stopped here intentionally so the repository contains approximately the first half of the lab.
