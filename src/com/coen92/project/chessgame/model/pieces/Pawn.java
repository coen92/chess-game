package com.coen92.project.chessgame.model.pieces;

import com.coen92.project.chessgame.model.board.Board;
import com.coen92.project.chessgame.model.rules.Alliance;
import com.coen92.project.chessgame.model.rules.Move;
import com.coen92.project.chessgame.model.utils.BoardUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATE = {8};

    protected Pawn(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        int candidateDestinationCoordinate;
        final List<Move> legalMoves = new ArrayList<>();

        for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {
            candidateDestinationCoordinate = this.getPiecePosition() + (this.getPieceAlliance().getDirection() * currentCandidateOffset);

            if(!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                continue;
            }

            if(currentCandidateOffset == 8 && !board.getChessTile(candidateDestinationCoordinate).isTileOccupied()) {
                legalMoves.add(new Move.UponMove(board, this, candidateDestinationCoordinate));
            }
        }

        return legalMoves;
    }
}
