package eye2web.chess.domain.model.pieces.base

import eye2web.chess.domain.Board
import eye2web.chess.domain.model.position.Position

abstract class BasePiece : Piece {

    override fun getValidMoves(board: Board): List<Position> {
        val validPositions = mutableListOf<Position>()

        if (hasPositionOnBoard(board)) {
            addValidMoves(validPositions, board)
        }

        return validPositions
    }

    override fun hasPositionOnBoard(board: Board): Boolean {
        return position?.let { board.getTileFor(it).piece == this } ?: false
    }
}