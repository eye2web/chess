package eye2web.chess.domain.model.pieces.base

import eye2web.chess.domain.Board
import eye2web.chess.domain.model.Color
import eye2web.chess.domain.model.position.Position

interface Piece {
    val color: Color
    var position: Position?

    fun hasPositionOnBoard(): Boolean

    fun isFirstMove(board: Board): Boolean

    fun getValidMoves(board: Board): List<Position>

    fun addValidMoves(
        validPositions: MutableList<Position>,
        board: Board
    )
}