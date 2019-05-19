package com.coen92.project.chessgame.model.player;

import com.coen92.project.chessgame.model.board.Board;
import com.coen92.project.chessgame.model.board.ChessTile;
import com.coen92.project.chessgame.model.pieces.Piece;
import com.coen92.project.chessgame.model.rules.Alliance;
import com.coen92.project.chessgame.model.rules.Move;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BlackPlayer extends Player {

    public BlackPlayer(final Board board, final Collection<Move> blackStandardLegalMoves, final Collection<Move> whiteStandardLegalMoves) {
        super(board, blackStandardLegalMoves, whiteStandardLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.BLACK;
    }

    @Override
    public Player getOpponent() {
        return this.board.getWhitePlayer();
    }

    @Override
    protected Collection<Move> calculateKingCastles(Collection<Move> playerLegals, Collection<Move> opponentsLegals) {
        final List<Move> kingCastles = new ArrayList<>();

        if(this.playerKing.isFirstMove() && !this.isInCheck()) {
            //Black Player king side castle move
            if(!this.board.getChessTile(5).isTileOccupied()
                    && !this.board.getChessTile(6).isTileOccupied()) {
                final ChessTile rookTile = this.board.getChessTile(7);

                if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                    if(Player.calculateAttacksOnTile(5, opponentsLegals).isEmpty()
                            && Player.calculateAttacksOnTile(6, opponentsLegals).isEmpty()
                            && rookTile.getPiece().getPieceType().isRook()) {
                        //Todo: add a castle move
                        kingCastles.add(null);
                    }
                }
            }
            //Black Player queen side castle move
            if(!this.board.getChessTile(1).isTileOccupied()
                    && !this.board.getChessTile(2).isTileOccupied()
                    && !this.board.getChessTile(3).isTileOccupied()) {
                final ChessTile rookTile = this.board.getChessTile(0);

                if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                    if(Player.calculateAttacksOnTile(1, opponentsLegals).isEmpty()
                            && Player.calculateAttacksOnTile(2, opponentsLegals).isEmpty()
                            && Player.calculateAttacksOnTile(3, opponentsLegals).isEmpty()
                            && rookTile.getPiece().getPieceType().isRook()) {
                        //Todo: add castle move
                        kingCastles.add(null);
                    }
                }
            }
        }
        return ImmutableList.copyOf(kingCastles);
    }
}
