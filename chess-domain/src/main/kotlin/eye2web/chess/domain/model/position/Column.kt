package eye2web.chess.domain.model.position

enum class Column(val index: Int) {
    A(0), B(1), C(2), D(3), E(4), F(5), G(6), H(7);

    companion object {
        fun valueOf(index: Int): Column {
            return entries.find { it.index == index }!!
        }
    }
}