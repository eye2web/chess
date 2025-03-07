package eye2web.chess.domain.model

import eye2web.chess.domain.model.pieces.Piece
import eye2web.chess.domain.model.position.Position

data class Tile(val position: Position, var piece: Piece? = null)