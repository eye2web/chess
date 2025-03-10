package eye2web.chess.domain.model.position

import java.util.stream.IntStream
import kotlin.math.abs

data class Position(val row: Row, val column: Column) {

    companion object {
        const val BOARD_WIDTH = 8

        fun indexToPosition(index: Int): Position {
            val row = (index / BOARD_WIDTH)
            val col = index - (row * BOARD_WIDTH)

            return Position(Row.valueOf(row), Column.valueOf(col))
        }
    }

    fun toIndex(): Int {
        return (this.row.index * BOARD_WIDTH) + this.column.index
    }

    fun getPositionsNorth(): List<Position> {
        val startIndex = this.toIndex()
        val timesNorth = this.row.index

        return IntStream.range(0, timesNorth).boxed()
            .map { multiplier -> multiplier + 1 }
            .map { multiplier -> startIndex - (multiplier * BOARD_WIDTH) }
            .map { indexToPosition(it) }
            .toList()
    }

    fun getPositionsNorthEast(): List<Position> {
        val startIndex = this.toIndex()

        val timesNorth = this.row.index
        val timesEast = BOARD_WIDTH - this.column.index - 1
        val timesNorthEast = listOf(timesNorth, timesEast).minOf { it }

        return IntStream.range(0, timesNorthEast).boxed()
            .map { multiplier -> multiplier + 1 }
            .map { multiplier -> startIndex - (multiplier * (BOARD_WIDTH - 1)) }
            .map { indexToPosition(it) }
            .toList()
    }

    fun getPositionsEast(): List<Position> {
        val startIndex = this.toIndex()
        val timesEast = BOARD_WIDTH - this.column.index - 1

        return IntStream.range(0, timesEast).boxed()
            .map { eastIndex -> eastIndex + 1 }
            .map { eastIndex -> startIndex + eastIndex }
            .map { indexToPosition(it) }
            .toList()
    }

    fun getPositionsSouthEast(): List<Position> {
        val startIndex = this.toIndex()

        val timesEast = BOARD_WIDTH - this.column.index - 1
        val timesSouth = abs(this.row.index - (BOARD_WIDTH - 1))

        val timesSouthEast = listOf(timesSouth, timesEast).minOf { it }

        return IntStream.range(0, timesSouthEast).boxed()
            .map { multiplier -> multiplier + 1 }
            .map { multiplier -> startIndex + (multiplier * (BOARD_WIDTH + 1)) }
            .map { indexToPosition(it) }
            .toList()
    }

    fun getPositionsSouth(): List<Position> {
        val startIndex = this.toIndex()
        val timesSouth = abs(this.row.index - (BOARD_WIDTH - 1))

        return IntStream.range(0, timesSouth).boxed()
            .map { multiplier -> multiplier + 1 }
            .map { multiplier -> startIndex + (multiplier * BOARD_WIDTH) }
            .map { indexToPosition(it) }
            .toList()
    }

    fun getPositionsSouthWest(): List<Position> {
        val startIndex = this.toIndex()

        val timesWest = this.column.index
        val timesSouth = abs(this.row.index - (BOARD_WIDTH - 1))

        val timesSouthWest = listOf(timesSouth, timesWest).minOf { it }

        return IntStream.range(0, timesSouthWest).boxed()
            .map { multiplier -> multiplier + 1 }
            .map { multiplier -> startIndex + (multiplier * (BOARD_WIDTH - 1)) }
            .map { indexToPosition(it) }
            .toList()
    }

    fun getPositionsWest(): List<Position> {
        val startIndex = this.toIndex()
        val timesWest = this.column.index

        return IntStream.range(0, timesWest).boxed()
            .map { westIndex -> westIndex + 1 }
            .map { westIndex -> startIndex - westIndex }
            .map { indexToPosition(it) }
            .toList()
    }

    fun getPositionsNorthWest(): List<Position> {
        val startIndex = this.toIndex()

        val timesWest = this.column.index
        val timesNorth = this.row.index
        val timesNorthWest = listOf(timesNorth, timesWest).minOf { it }

        return IntStream.range(0, timesNorthWest).boxed()
            .map { multiplier -> multiplier + 1 }
            .map { multiplier -> startIndex - (multiplier * (BOARD_WIDTH + 1)) }
            .map { indexToPosition(it) }
            .toList()
    }

    fun getJumpingPositionsInAllDirections(): List<Position> {
        val startIndex = this.toIndex()

        val timesNorth = this.row.index
        val timesEast = BOARD_WIDTH - this.column.index - 1
        val timesSouth = abs(this.row.index - (BOARD_WIDTH - 1))
        val timesWest = this.column.index

        val positionsToMove = 2

        val positions = mutableListOf<Position>()

        if (timesSouth >= positionsToMove) {
            if (timesEast > 0) {
                positions.add(indexToPosition(startIndex + (BOARD_WIDTH * positionsToMove) + 1))
            }

            if (timesWest > 0) {
                positions.add(indexToPosition(startIndex + (BOARD_WIDTH * positionsToMove) - 1))
            }
        }

        if (timesEast >= positionsToMove) {
            if (timesNorth > 0) {
                positions.add(indexToPosition(startIndex - (BOARD_WIDTH - positionsToMove)))
            }

            if (timesSouth > 0) {
                positions.add(indexToPosition(startIndex + (BOARD_WIDTH + positionsToMove)))
            }
        }

        if (timesNorth >= positionsToMove) {
            if (timesEast > 0) {
                positions.add(indexToPosition(startIndex - (BOARD_WIDTH * positionsToMove) - 1))
            }

            if (timesWest > 0) {
                positions.add(indexToPosition(startIndex - (BOARD_WIDTH * positionsToMove) + 1))
            }
        }

        if (timesWest >= positionsToMove) {
            if (timesNorth > 0) {
                positions.add(indexToPosition(startIndex - BOARD_WIDTH - positionsToMove))
            }

            if (timesSouth > 0) {
                positions.add(indexToPosition(startIndex + BOARD_WIDTH - positionsToMove))
            }
        }

        return positions
    }
}
