package eye2web.chess.domain.model.pieces

import eye2web.chess.domain.Board
import eye2web.chess.domain.model.Color
import eye2web.chess.domain.model.actions.Movement.Companion.getValidSingleMovesForPiece
import eye2web.chess.domain.model.pieces.base.BasePiece
import eye2web.chess.domain.model.position.Position


class Knight(
    override val color: Color,
    override var position: Position? = null,
    override var isFirstMove: Boolean = true
) : BasePiece() {

    override fun addValidMoves(validPositions: MutableList<Position>, board: Board) {
        validPositions.addAll(
            getValidSingleMovesForPiece(
                color,
                board.getTilesFor(position!!.getJumpingPositionsInAllDirections())
            )
        )
    }
}