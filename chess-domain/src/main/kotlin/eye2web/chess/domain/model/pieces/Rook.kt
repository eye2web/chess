package eye2web.chess.domain.model.pieces

import eye2web.chess.domain.Board
import eye2web.chess.domain.model.Color
import eye2web.chess.domain.model.position.Position

class Rook(override val color: Color) : Piece {
    override fun getValidMoves(board: Board): List<Position> {
        val myPos = board.getPositionForPiece(this)
        
        val validPositions = mutableListOf<Position>()
        validPositions.addAll(
            board.getValidLinearMovesForPiece(
                this,
                myPos.getPositionsNorth()
            )
        )
        validPositions.addAll(
            board.getValidLinearMovesForPiece(
                this,
                myPos.getPositionsEast()
            )
        )
        validPositions.addAll(
            board.getValidLinearMovesForPiece(
                this,
                myPos.getPositionsSouth()
            )
        )
        validPositions.addAll(
            board.getValidLinearMovesForPiece(
                this,
                myPos.getPositionsWest()
            )
        )

        return validPositions
    }
}
