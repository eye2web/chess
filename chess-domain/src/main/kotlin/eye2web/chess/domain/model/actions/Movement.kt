package eye2web.chess.domain.model.actions

import eye2web.chess.domain.model.Color
import eye2web.chess.domain.model.Tile
import eye2web.chess.domain.model.pieces.base.Piece
import eye2web.chess.domain.model.position.Position
import java.time.LocalDateTime

data class Movement(
    val piece: Piece,
    val fromPosition: Position,
    val toPosition: Position,
    val dateTime: LocalDateTime = LocalDateTime.now()
) {
    companion object {
        fun getValidLinearMovesForPiece(pieceColor: Color, possibleTiles: List<Tile>): List<Position> {
            val positions: MutableList<Position> = mutableListOf()

            run breaking@{
                possibleTiles.forEach { tile ->

                    tile.piece?.let { piece: Piece ->
                        if (piece.color != pieceColor) {
                            positions.add(tile.position)
                        }
                        return@breaking
                    }

                    positions.add(tile.position)
                }
            }
            return positions
        }

        fun getValidSingleMovesForPiece(pieceColor: Color, possibleTiles: List<Tile>): List<Position> {
            val positions: MutableList<Position> = mutableListOf()

            possibleTiles.forEach { tile ->
                tile.piece?.let { piece: Piece ->
                    if (piece.color != pieceColor) {
                        positions.add(tile.position)
                    }
                    return@forEach
                }
                positions.add(tile.position)
            }

            return positions
        }
    }
}