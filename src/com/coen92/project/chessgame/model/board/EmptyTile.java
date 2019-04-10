package com.coen92.project.chessgame.model.board;

import com.coen92.project.chessgame.model.pieces.Piece;

public class EmptyTile extends ChessTile {

    protected EmptyTile(final int coordinate) {
        super(coordinate);
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
