package eye2web.chess.domain.model.pieces

import eye2web.chess.domain.Board
import eye2web.chess.domain.model.Color
import eye2web.chess.domain.model.actions.Movement.Companion.getValidLinearMovesForPiece
import eye2web.chess.domain.model.pieces.base.BasePiece
import eye2web.chess.domain.model.position.Position

class Rook(
    override val color: Color,
    override var position: Position? = null,
    override var isFirstMove: Boolean = true
) : BasePiece() {

    override fun addValidMoves(validPositions: MutableList<Position>, board: Board) {
        validPositions.addAll(
            getValidLinearMovesForPiece(
                color,
                board.getTilesFor(position!!.getPositionsNorth())
            )
        )
        validPositions.addAll(
            getValidLinearMovesForPiece(
                color,
                board.getTilesFor(position!!.getPositionsEast())
            )
        )
        validPositions.addAll(
            getValidLinearMovesForPiece(
                color,
                board.getTilesFor(position!!.getPositionsSouth())
            )
        )
        validPositions.addAll(
            getValidLinearMovesForPiece(
                color,
                board.getTilesFor(position!!.getPositionsWest())
            )
        )
    }
}
