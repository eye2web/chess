package eye2web.chess.domain.model.position

import java.util.stream.IntStream
import kotlin.math.abs

data class Position(val row: Row, val column: Column) {

    companion object {
        const val BOARD_WIDTH = 8

        fun indexToPosition(index: Int): Position {
            val row = (index / BOARD_WIDTH)
            //val invertedRow = BOARD_WIDTH - row - 1
            val col = index - (row * BOARD_WIDTH)

            return Position(Row.valueOf(row), Column.valueOf(col))
        }
    }

    fun toIndex(): Int {
        return (this.row.index * BOARD_WIDTH) + this.column.index
    }

    fun getIndexesNorth(): List<Int> {
        val startIndex = this.toIndex()
        val timesNorth = this.row.index

        return IntStream.range(0, timesNorth).boxed()
            .map { multiplier -> multiplier + 1 }
            .map { multiplier -> startIndex - (multiplier * BOARD_WIDTH) }
            .toList()
    }

    fun getIndexesEast(): List<Int> {
        val startIndex = this.toIndex()
        val timesEast = BOARD_WIDTH - this.column.index - 1

        return IntStream.range(0, timesEast).boxed()
            .map { eastIndex -> eastIndex + 1 }
            .map { eastIndex -> startIndex + eastIndex }
            .toList()
    }

    fun getIndexesSouth(): List<Int> {
        val startIndex = this.toIndex()
        val timesSouth = abs(this.row.index - (BOARD_WIDTH - 1))

        return IntStream.range(0, timesSouth).boxed()
            .map { multiplier -> multiplier + 1 }
            .map { multiplier -> startIndex + (multiplier * BOARD_WIDTH) }
            .toList()
    }

    fun getIndexesWest(): List<Int> {
        val startIndex = this.toIndex()
        val timesWest = this.column.index

        return IntStream.range(0, timesWest).boxed()
            .map { westIndex -> westIndex + 1 }
            .map { westIndex -> startIndex - westIndex }
            .toList()
    }
}
