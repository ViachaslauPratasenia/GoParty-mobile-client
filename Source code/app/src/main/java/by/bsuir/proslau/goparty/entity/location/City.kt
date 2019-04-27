package by.bsuir.proslau.goparty.entity.location

class City(
    private val name : String,
    private val id : String
) {
    override fun toString(): String {
        return name
    }
}