package com.coen92.project.chessgame.model.board;

import com.coen92.project.chessgame.model.pieces.Piece;

public class OccupiedTile extends ChessTile {

    private final Piece pieceOnTile;

    protected OccupiedTile(final int coordinate, final Piece pieceOnTile) {
        super(coordinate);
        this.pieceOnTile = pieceOnTile;
    }

    // if the ChessTile is occupied it will be printed out as declared in each kind of piece class toString() method
    // black pieces will be printed as lower case String
    // white pieces will be printed as upper case String
    @Override
    public String toString() {
        return getPiece().getPieceAlliance().isBlack() ? getPiece().toString().toLowerCase() : getPiece().toString();
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
