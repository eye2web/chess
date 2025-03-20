package eye2web.chess.domain.model.actions

import eye2web.chess.domain.model.pieces.base.Piece
import eye2web.chess.domain.model.position.Position
import java.time.LocalDateTime

data class Movement(
    val piece: Piece,
    val fromPosition: Position,
    val toPosition: Position,
    val dateTime: LocalDateTime = LocalDateTime.now()
)