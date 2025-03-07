package eye2web.chess.domain.model.position

enum class Row(val index: Int) {
    ONE(7), TWO(6), THREE(5), FOUR(4), FIVE(3), SIX(2), SEVEN(1), EIGHT(0);

    companion object {
        fun valueOf(index: Int): Row {
            return Row.entries.find { it.index == index }!!
        }
    }
}