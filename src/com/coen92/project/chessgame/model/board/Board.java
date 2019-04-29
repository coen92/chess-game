package com.coen92.project.chessgame.model.board;

import com.coen92.project.chessgame.model.pieces.*;
import com.coen92.project.chessgame.model.rules.Alliance;
import com.coen92.project.chessgame.model.utils.BoardUtils;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Board {

    private final List<ChessTile> gameBoard;
    private final Collection<Piece> whitePieces;
    private final Collection<Piece> blackPieces;

    private Board(Builder builder) {
        this.gameBoard = createGameBoard(builder);
        this.whitePieces = calculateActivePieces(this.gameBoard, Alliance.WHITE);
        this.blackPieces = calculateActivePieces(this.gameBoard, Alliance.BLACK);
    }

    public ChessTile getChessTile(final int tileCoordinate) {
        return gameBoard.get(tileCoordinate);
    }

    private static List<ChessTile> createGameBoard(final Builder builder) {
        final ChessTile[] chessTiles = new ChessTile[BoardUtils.NUMBER_OF_TILES];
        for(int i=0; i<BoardUtils.NUMBER_OF_TILES; i++) {
            chessTiles[i] = ChessTile.createTile(i, builder.boardConfiguration.get(i));
        }
        return ImmutableList.copyOf(chessTiles);
    }

    private Collection<Piece> calculateActivePieces(final List<ChessTile> gameBoard, final Alliance alliance) {
        final List<Piece> activePieces = new ArrayList<>();

        for(final ChessTile tile : gameBoard) {
            if(tile.isTileOccupied()) {
                final Piece piece = tile.getPiece();
                if(piece.getPieceAlliance() == alliance) {
                    activePieces.add(piece);
                }
            }
        }
        return ImmutableList.copyOf(activePieces);
    }

    public static Board createInitialBoard() {
        final Builder builder = new Builder();
        // setting initial Piece configuration for Black layout
        builder.setPiece(new Rook(0, Alliance.BLACK));
        builder.setPiece(new Knight(1, Alliance.BLACK));
        builder.setPiece(new Bishop(2, Alliance.BLACK));
        builder.setPiece(new Queen(3, Alliance.BLACK));
        builder.setPiece(new King(4, Alliance.BLACK));
        builder.setPiece(new Bishop(5, Alliance.BLACK));
        builder.setPiece(new Knight(6, Alliance.BLACK));
        builder.setPiece(new Rook(7, Alliance.BLACK));
        builder.setPiece(new Pawn(8, Alliance.BLACK));
        builder.setPiece(new Pawn(9, Alliance.BLACK));
        builder.setPiece(new Pawn(10, Alliance.BLACK));
        builder.setPiece(new Pawn(11, Alliance.BLACK));
        builder.setPiece(new Pawn(12, Alliance.BLACK));
        builder.setPiece(new Pawn(13, Alliance.BLACK));
        builder.setPiece(new Pawn(14, Alliance.BLACK));
        builder.setPiece(new Pawn(15, Alliance.BLACK));

        // setting initial Piece configuration for White layout
        builder.setPiece(new Pawn(48, Alliance.WHITE));
        builder.setPiece(new Pawn(49, Alliance.WHITE));
        builder.setPiece(new Pawn(50, Alliance.WHITE));
        builder.setPiece(new Pawn(51, Alliance.WHITE));
        builder.setPiece(new Pawn(52, Alliance.WHITE));
        builder.setPiece(new Pawn(53, Alliance.WHITE));
        builder.setPiece(new Pawn(54, Alliance.WHITE));
        builder.setPiece(new Pawn(55, Alliance.WHITE));
        builder.setPiece(new Rook(56, Alliance.WHITE));
        builder.setPiece(new Knight(57, Alliance.WHITE));
        builder.setPiece(new Bishop(58, Alliance.WHITE));
        builder.setPiece(new Queen(59, Alliance.WHITE));
        builder.setPiece(new King(60, Alliance.WHITE));
        builder.setPiece(new Bishop(61, Alliance.WHITE));
        builder.setPiece(new Knight(62, Alliance.WHITE));
        builder.setPiece(new Rook(63, Alliance.WHITE));

        // setting initial Move for the WHITE alliance
        builder.setMoveMaker(Alliance.WHITE);

        return builder.build();
    }

    public static class Builder {

        private Map<Integer, Piece> boardConfiguration;
        private Alliance nextMoveMaker;

        public Builder() {
        }

        public Builder setPiece(final Piece piece) {
            this.boardConfiguration.put(piece.getPiecePosition(), piece);
            return this;
        }

        public Builder setMoveMaker(final Alliance alliance) {
            this.nextMoveMaker = alliance;
            return this;
        }

        //method for creating immutable Board based on our Builder class
        public Board build() {
            return new Board(this);
        }

    }


}
