package papayacoders.iplscoreyoutube.models

data class Match(
    val date: String,
    val dateTimeGMT: String,
    val fantasyEnabled: Boolean,
    val hasSquad: Boolean,
    val id: String,
    val matchType: String,
    val name: String,
    val status: String,
    val teams: List<String>,
    val venue: String
)