package eye2web.chess.domain

import eye2web.chess.domain.model.Color
import eye2web.chess.domain.exception.InvalidMoveException
import eye2web.chess.domain.exception.NoPieceLocatedException
import eye2web.chess.domain.model.pieces.Rook
import eye2web.chess.domain.model.position.Column
import eye2web.chess.domain.model.position.Position
import eye2web.chess.domain.model.position.Row
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.math.abs
import kotlin.test.assertEquals
import kotlin.test.assertNull

class BoardTest {
    @Test
    fun tilePositionTest() {
        val board = Board()
        val tiles = board.getTiles()
        Row.entries.forEachIndexed { rowIndex, row ->
            Column.entries.forEachIndexed { colIndex, column ->
                val invertedRowIndex = abs(rowIndex - (Position.BOARD_WIDTH - 1))
                val index = (invertedRowIndex * 8) + colIndex
                val tile = tiles[index]

                assertEquals(row, tile.position.row)
                assertEquals(column, tile.position.column)
                assertEquals(index, tile.position.toIndex())
            }
        }
    }

    @Test
    fun noPieceToMoveTest() {
        val board = Board()

        assertThrows<NoPieceLocatedException> {
            board.movePiece(Position(Row.ONE, Column.A), Position(Row.TWO, Column.A))
        }
    }

    @Test
    fun invalidRookMoveTest() {
        val board = Board()
        board.setPieceOnTile(Position(Row.ONE, Column.A), Rook(Color.WHITE))

        assertThrows<InvalidMoveException> {
            board.movePiece(Position(Row.ONE, Column.A), Position(Row.TWO, Column.B))
        }
    }

    @Test
    fun invalidRookToRookMoveTest() {
        val board = Board()
        board.setPieceOnTile(Position(Row.ONE, Column.A), Rook(Color.WHITE))
        board.setPieceOnTile(Position(Row.ONE, Column.B), Rook(Color.WHITE))

        assertThrows<InvalidMoveException> {
            board.movePiece(Position(Row.ONE, Column.A), Position(Row.ONE, Column.G))
        }
    }

    @Test
    fun captureWithRookTest() {
        val board = Board()
        val rookToMove = Rook(Color.WHITE)
        val rookToCapture = Rook(Color.BLACK)
        board.setPieceOnTile(Position(Row.ONE, Column.A), rookToMove)
        board.setPieceOnTile(Position(Row.SEVEN, Column.A), rookToCapture)

        val capturedPiece = board.movePiece(Position(Row.ONE, Column.A), Position(Row.SEVEN, Column.A))

        assertEquals(rookToCapture, capturedPiece)
        assertEquals(Position(Row.SEVEN, Column.A), board.getPosition(rookToMove))
        assertNull(board.getTiles()[Position(Row.ONE, Column.A).toIndex()].piece)
    }

    @Test
    fun validRookMoveTest() {
        val board = Board()
        val rook = Rook(Color.WHITE)
        board.setPieceOnTile(Position(Row.ONE, Column.A), rook)
        board.movePiece(Position(Row.ONE, Column.A), Position(Row.ONE, Column.G))

        assertEquals(board.getTiles()[Position(Row.ONE, Column.G).toIndex()].piece!!, rook)
    }
}