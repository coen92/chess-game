package com.coen92.project.chessgame.model.pieces;

import com.coen92.project.chessgame.model.board.Board;
import com.coen92.project.chessgame.model.board.ChessTile;
import com.coen92.project.chessgame.model.rules.Alliance;
import com.coen92.project.chessgame.model.rules.Move;
import com.coen92.project.chessgame.model.utils.BoardUtils;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.coen92.project.chessgame.model.rules.Move.*;

public class King extends Piece {

    private static final int[] CANDIDATE_MOVE_COORDINATE = {-9, -8, -7, -1, 1, 7, 8, 9};

    public King(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {
            final int candidateDestinationCoordinate = this.getPiecePosition() + currentCandidateOffset;

            if(isFirstColumnExclusion(this.getPiecePosition(), currentCandidateOffset)
                    || isEighthColumnExclusion(this.getPiecePosition(), currentCandidateOffset)) {
                continue;
            }

            if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                final ChessTile candidateDestinationTile = board.getChessTile(candidateDestinationCoordinate);

                if(!candidateDestinationTile.isTileOccupied()) {
                    legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));

                } else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                    if(this.getPieceAlliance() != pieceAlliance) {
                        legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));

                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -9 || candidateOffset == -1 || candidateOffset == 7);
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == -7 || candidateOffset == 1 || candidateOffset == 9);
    }
}
