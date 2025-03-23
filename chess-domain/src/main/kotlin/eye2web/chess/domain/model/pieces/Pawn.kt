package eye2web.chess.domain.model.pieces

import eye2web.chess.domain.Board
import eye2web.chess.domain.model.Color
import eye2web.chess.domain.model.pieces.base.BasePiece
import eye2web.chess.domain.model.position.Position

class Pawn(
    override val color: Color,
    override var position: Position?,
    override var isFirstMove: Boolean
) : BasePiece() {
    override fun addValidMoves(validPositions: MutableList<Position>, board: Board) {
        TODO("Not yet implemented")
    }
}