package sanchez.sergio.kmp_test.domain.models


data class Character (
    val id: Long,
    val name: String,
    val status: CharacterStatusEnum,
    val species: String,
    val type: String,
    val gender: CharacterGenderEnum,
    val origin: SimpleLocation,
    val simpleLocation: SimpleLocation,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

enum class CharacterStatusEnum {
    Alive, Dead, unknown
}

enum class CharacterGenderEnum {
    Female, Male, Genderless, unknown
}

data class SimpleLocation (
    val name: String,
    val url: String
)