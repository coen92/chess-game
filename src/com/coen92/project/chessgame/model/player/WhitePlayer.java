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

public class WhitePlayer extends Player {

    public WhitePlayer(final Board board, final Collection<Move> whiteStandardLegalMoves, final Collection<Move> blackStandardLegalMoves) {
        super(board, whiteStandardLegalMoves, blackStandardLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.getBlackPlayer();
    }

    @Override
    protected Collection<Move> calculateKingCastles(Collection<Move> playerLegals, Collection<Move> opponentsLegals) {
        final List<Move> kingCastles = new ArrayList<>();

        if(this.playerKing.isFirstMove() && !this.isInCheck()) {
            //White Player king side castle move
            if(!this.board.getChessTile(61).isTileOccupied()
                    && !this.board.getChessTile(62).isTileOccupied()) {
                final ChessTile rookTile = this.board.getChessTile(63);

                if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                    if(Player.calculateAttacksOnTile(61, opponentsLegals).isEmpty()
                            && Player.calculateAttacksOnTile(62, opponentsLegals).isEmpty()
                            && rookTile.getPiece().getPieceType().isRook()) {
                        //Todo: add a castle move
                        kingCastles.add(null);
                    }
                }
            }
            //White Player queen side castle move
            if(!this.board.getChessTile(59).isTileOccupied()
                    && !this.board.getChessTile(58).isTileOccupied()
                    && !this.board.getChessTile(57).isTileOccupied()) {
                final ChessTile rookTile = this.board.getChessTile(56);

                if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                    if(Player.calculateAttacksOnTile(59, opponentsLegals).isEmpty()
                            && Player.calculateAttacksOnTile(58, opponentsLegals).isEmpty()
                            && Player.calculateAttacksOnTile(57, opponentsLegals).isEmpty()
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
