package eye2web.chess.domain

import eye2web.chess.domain.model.Tile
import eye2web.chess.domain.exception.InvalidMoveException
import eye2web.chess.domain.exception.NoPieceLocatedException
import eye2web.chess.domain.exception.PieceNotOnBoardException
import eye2web.chess.domain.model.pieces.Piece
import eye2web.chess.domain.model.position.Position
import eye2web.chess.domain.model.position.Position.Companion.BOARD_WIDTH
import eye2web.chess.domain.model.position.Position.Companion.indexToPosition
import java.util.stream.IntStream

class Board {
    private val tiles: List<Tile> =
        IntStream.range(0, BOARD_WIDTH * BOARD_WIDTH).boxed().map { Tile(indexToPosition(it)) }.toList()

    fun getTiles(): List<Tile> {
        return tiles
    }

    fun movePiece(fromPosition: Position, targetPosition: Position): Piece? {

        val fromTile = fromPosition.getTile()
        val pieceToMove = fromTile.piece ?: throw NoPieceLocatedException("No Piece found for position $fromPosition")

        validateMove(pieceToMove, targetPosition)

        val capturedPiece = movePieceToTile(fromTile, targetPosition, pieceToMove)

        return capturedPiece
    }

    fun getPosition(piece: Piece): Position {
        return tiles.filter { it.piece !== null }.find { it.piece!! == piece }?.position
            ?: throw PieceNotOnBoardException("$piece not located on the board")
    }

    fun setPieceOnTile(position: Position, piece: Piece): Piece? {
        val currentPiece = position.getTile().piece
        position.getTile().piece = piece
        return currentPiece
    }

    private fun movePieceToTile(fromTile: Tile, position: Position, piece: Piece): Piece? {
        val currentPiece = setPieceOnTile(position, piece)
        fromTile.piece = null
        return currentPiece
    }

    private fun validateMove(
        pieceToMove: Piece,
        targetPosition: Position
    ) {
        if (!pieceToMove.getValidMoves(this).contains(targetPosition)) {
            throw InvalidMoveException("Invalid move position $targetPosition for piece $pieceToMove")
        }
    }

    private fun Position.getTile(): Tile {
        return tiles[this.toIndex()]
    }
}
