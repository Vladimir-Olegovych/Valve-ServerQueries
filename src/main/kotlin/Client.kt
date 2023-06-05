

fun main() {
    SourceEngineClient().use {
        it.printInfo("95.156.230.56", 27055)
    }
}
