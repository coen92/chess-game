package com.coen92.project.chessgame.model.board;

import com.coen92.project.chessgame.model.pieces.Piece;

public class EmptyTile extends ChessTile {

    protected EmptyTile(final int coordinate) {
        super(coordinate);
    }

    // if the ChessTile is empty it will be printed out in console as dash "-"
    @Override
    public String toString() {
        return "-";
    }

    @Override
    public boolean isTileOccupied() {
        return false;
    }

    @Override
    public Piece getPiece() {
        return null;
    }
}
