package eye2web.chess.domain.model.pieces

import eye2web.chess.domain.model.Board
import eye2web.chess.domain.model.Color
import eye2web.chess.domain.model.position.Position

interface Piece {
    val color: Color

    fun getValidMoves(board: Board): List<Position>
}