package eye2web.chess.domain.model.pieces

import eye2web.chess.domain.Board
import eye2web.chess.domain.model.Color
import eye2web.chess.domain.model.pieces.base.BasePiece
import eye2web.chess.domain.model.position.Position

class King(
    override val color: Color,
    override var position: Position? = null,
    override var isFirstMove: Boolean = true
) : BasePiece() {

    override fun addValidMoves(
        validPositions: MutableList<Position>,
        board: Board
    ) {
        TODO("Not yet implemented")
    }
}