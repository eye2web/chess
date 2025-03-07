package eye2web.chess.domain.model.pieces

import eye2web.chess.domain.Board
import eye2web.chess.domain.model.Color
import eye2web.chess.domain.model.Tile
import eye2web.chess.domain.model.position.Position

class Rook(override val color: Color) : Piece {
    override fun getValidMoves(board: Board): List<Position> {
        val myPos = board.getPosition(this)

        val tiles = board.getTiles()

        val validPositions = mutableListOf<Position>()
        validPositions.addAll(getValidLinearMoves(myPos.getIndexesNorth().map { tiles[it] }.toList()))
        validPositions.addAll(getValidLinearMoves(myPos.getIndexesEast().map { tiles[it] }.toList()))
        validPositions.addAll(getValidLinearMoves(myPos.getIndexesSouth().map { tiles[it] }.toList()))
        validPositions.addAll(getValidLinearMoves(myPos.getIndexesWest().map { tiles[it] }.toList()))

        return validPositions
    }

    private fun getValidLinearMoves(possibleTiles: List<Tile>): List<Position> {
        val positions: MutableList<Position> = mutableListOf()

        run breaking@{
            possibleTiles.forEach { tile ->
                if (tile.piece == null) {
                    positions.add(tile.position)
                } else {
                    if (tile.piece!!.color != this.color) {
                        positions.add(tile.position)
                    }

                    return@breaking
                }
            }
        }
        return positions
    }
}
