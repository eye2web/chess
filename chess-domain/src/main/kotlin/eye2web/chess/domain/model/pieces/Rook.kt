package eye2web.chess.domain.model.pieces

import eye2web.chess.domain.Board
import eye2web.chess.domain.model.Color
import eye2web.chess.domain.model.pieces.base.BasePiece
import eye2web.chess.domain.model.position.Position

class Rook(override val color: Color, override var position: Position? = null) : BasePiece() {

    override fun addValidMoves(validPositions: MutableList<Position>, board: Board) {
        validPositions.addAll(
            board.getValidLinearMovesForPiece(
                color,
                position!!.getPositionsNorth()
            )
        )
        validPositions.addAll(
            board.getValidLinearMovesForPiece(
                color,
                position!!.getPositionsEast()
            )
        )
        validPositions.addAll(
            board.getValidLinearMovesForPiece(
                color,
                position!!.getPositionsSouth()
            )
        )
        validPositions.addAll(
            board.getValidLinearMovesForPiece(
                color,
                position!!.getPositionsWest()
            )
        )
    }
}
