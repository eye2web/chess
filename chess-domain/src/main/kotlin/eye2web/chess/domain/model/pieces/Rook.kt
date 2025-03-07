package eye2web.chess.domain.model.pieces

import eye2web.chess.domain.Board
import eye2web.chess.domain.model.Color
import eye2web.chess.domain.model.position.Position

class Rook(override val color: Color) : Piece {
    override fun getValidMoves(board: Board): List<Position> {
        val myPos = board.getPosition(this)

        val tiles = board.getTiles()

        val validPositions = mutableListOf<Position>()
        validPositions.addAll(
            board.getValidLinearMovesForPiece(
                this,
                myPos.getIndexesNorth().map { tiles[it] }.toList()
            )
        )
        validPositions.addAll(
            board.getValidLinearMovesForPiece(
                this,
                myPos.getIndexesEast().map { tiles[it] }.toList()
            )
        )
        validPositions.addAll(
            board.getValidLinearMovesForPiece(
                this,
                myPos.getIndexesSouth().map { tiles[it] }.toList()
            )
        )
        validPositions.addAll(
            board.getValidLinearMovesForPiece(
                this,
                myPos.getIndexesWest().map { tiles[it] }.toList()
            )
        )

        return validPositions
    }
}
