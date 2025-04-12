import kotlinx.serialization.Serializable

@Serializable
data class ChampionResponse(
    val type: String,
    val format: String,
    val version: String,
    val data: Map<String, Champion>
)

@Serializable
data class Champion(
    val version: String,
    val id: String,
    val key: String,
    val name: String,
    val title: String,
    val blurb: String,
    val info: Info,
    val image: Image,
    val tags: List<String>,
    val partype: String,
    val stats: Stats
)

@Serializable
data class Info(
    val attack: Int,
    val defense: Int,
    val magic: Int,
    val difficulty: Int
)

@Serializable
data class Image(val full: String)

@Serializable
data class Stats(
    val hp: Float,
    val hpperlevel: Float,
    val mp: Float,
    val mpperlevel: Float,
    val movespeed: Float,
    val armor: Float,
    val armorperlevel: Float,
    val spellblock: Float,
    val spellblockperlevel: Float,
    val attackrange: Float,
    val hpregen: Float,
    val hpregenperlevel: Float,
    val mpregen: Float,
    val mpregenperlevel: Float,
    val crit: Float,
    val critperlevel: Float,
    val attackdamage: Float,
    val attackdamageperlevel: Float,
    val attackspeedperlevel: Float,
    val attackspeed: Float
)
