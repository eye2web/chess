package eye2web.chess.domain.model.pieces.base

import eye2web.chess.domain.Board
import eye2web.chess.domain.model.position.Position

abstract class BasePiece : Piece {

    override fun isFirstMove(board: Board): Boolean {
        return board.getMovementHistory().find { it.piece == this }?.let { false } ?: true
    }

    override fun getValidMoves(board: Board): List<Position> {
        val validPositions = mutableListOf<Position>()

        if (hasPositionOnBoard()) {
            addValidMoves(validPositions, board)
        }

        return validPositions
    }

    override fun hasPositionOnBoard(): Boolean {
        return position?.let { true } ?: false
    }
}