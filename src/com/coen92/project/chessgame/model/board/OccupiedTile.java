package com.coen92.project.chessgame.model.board;

import com.coen92.project.chessgame.model.pieces.Piece;

public class OccupiedTile extends ChessTile {

    private final Piece pieceOnTile;

    protected OccupiedTile(final int coordinate, Piece pieceOnTile) {
        super(coordinate);
        this.pieceOnTile = pieceOnTile;
    }

    @Override
    public boolean isTileOccupied() {
        return true;
    }

    @Override
    public Piece getPiece() {
        return this.pieceOnTile;
    }
}
