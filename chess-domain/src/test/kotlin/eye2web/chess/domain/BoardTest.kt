package eye2web.chess.domain

import eye2web.chess.domain.model.Color
import eye2web.chess.domain.exception.InvalidMoveException
import eye2web.chess.domain.exception.NoPieceLocatedException
import eye2web.chess.domain.model.pieces.Bishop
import eye2web.chess.domain.model.pieces.Knight
import eye2web.chess.domain.model.pieces.Rook
import eye2web.chess.domain.model.position.Column
import eye2web.chess.domain.model.position.Position
import eye2web.chess.domain.model.position.Row
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.math.abs
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNull

class BoardTest {
    @Test
    fun tilePositionTest() {
        val board = Board()

        Row.entries.forEachIndexed { rowIndex, row ->
            Column.entries.forEachIndexed { colIndex, column ->
                val invertedRowIndex = abs(rowIndex - (Position.BOARD_WIDTH - 1))
                val index = (invertedRowIndex * 8) + colIndex
                val tile = board.getTileFor(Position.indexToPosition(index))

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
    fun validRookPositionsTest() {
        val board = Board()
        val rook = Rook(Color.WHITE)
        board.setPieceOnTile(Position(Row.TWO, Column.C), rook)

        val validMoves = board.getValidMovesForPiece(rook)

        assertContains(validMoves, Position(Row.EIGHT, Column.C))
        assertContains(validMoves, Position(Row.TWO, Column.H))
        assertContains(validMoves, Position(Row.ONE, Column.C))
        assertContains(validMoves, Position(Row.TWO, Column.A))
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
        assertEquals(Position(Row.SEVEN, Column.A), board.getPositionForPiece(rookToMove))
        assertNull(board.getTileFor(Position(Row.ONE, Column.A)).piece)
    }

    @Test
    fun validRookMoveTest() {
        val board = Board()
        val rook = Rook(Color.WHITE)
        board.setPieceOnTile(Position(Row.ONE, Column.A), rook)
        board.movePiece(Position(Row.ONE, Column.A), Position(Row.ONE, Column.G))

        assertEquals(board.getTileFor(Position(Row.ONE, Column.G)).piece!!, rook)
    }

    @Test
    fun validBishopPositionsTest() {
        val board = Board()
        val bishop = Bishop(Color.WHITE)

        board.setPieceOnTile(Position(Row.THREE, Column.C), bishop)

        val validMoves = board.getValidMovesForPiece(bishop)

        assertContains(validMoves, Position(Row.EIGHT, Column.H))
        assertContains(validMoves, Position(Row.ONE, Column.E))
        assertContains(validMoves, Position(Row.ONE, Column.A))
        assertContains(validMoves, Position(Row.FIVE, Column.A))
    }

    @Test
    fun invalidBishopPositionsTest() {
        val board = Board()
        val bishop = Bishop(Color.WHITE)

        board.setPieceOnTile(Position(Row.THREE, Column.C), bishop)

        assertThrows<InvalidMoveException> {
            board.movePiece(Position(Row.THREE, Column.C), Position(Row.TWO, Column.C))
        }
    }

    @Test
    fun validKnightPositionsTest() {
        val board = Board()
        val knight = Knight(Color.WHITE)
        val rook = Rook(Color.WHITE)

        board.setPieceOnTile(Position(Row.THREE, Column.C), knight)
        board.setPieceOnTile(Position(Row.TWO, Column.A), rook)

        val validMoves = board.getValidMovesForPiece(knight)

        assertEquals(7, validMoves.size)
        assertContains(validMoves, Position(Row.FIVE, Column.B))
        assertContains(validMoves, Position(Row.FIVE, Column.D))
        assertContains(validMoves, Position(Row.FOUR, Column.E))
        assertContains(validMoves, Position(Row.TWO, Column.E))
        assertContains(validMoves, Position(Row.ONE, Column.D))
        assertContains(validMoves, Position(Row.ONE, Column.B))
        assertContains(validMoves, Position(Row.FOUR, Column.A))
    }

    @Test
    fun validKnightPositions2Test() {
        val board = Board()
        val knight = Knight(Color.WHITE)
        val knight2 = Knight(Color.WHITE)

        board.setPieceOnTile(Position(Row.ONE, Column.A), knight)
        board.setPieceOnTile(Position(Row.ONE, Column.H), knight2)

        val validMoves = board.getValidMovesForPiece(knight)
        val validMoves2 = board.getValidMovesForPiece(knight2)

        assertEquals(2, validMoves.size)
        assertEquals(2, validMoves2.size)
    }

}