package day11

/**
 * Either an RTG or a microchip
 */
data class Equipment(val material: String, val isChip: Boolean) {
    override fun toString(): String = if (isChip) "a $material-compatible microchip" else "a $material generator"
}