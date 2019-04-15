package com.coen92.project.chessgame.model.board;

import com.coen92.project.chessgame.model.pieces.Piece;
import com.coen92.project.chessgame.model.utils.BoardUtils;
import com.google.common.collect.ImmutableMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class ChessTile {

    private final int tileCoordinate;
    private static final Map<Integer, EmptyTile> EMPTY_TILES = createAllPossibleEmptyTiles();

    //preventing from creation new Tile using Constructor
    protected ChessTile(final int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }

    public int getTileCoordinate() {
        return tileCoordinate;
    }

    public abstract boolean isTileOccupied();
    public abstract Piece getPiece();

    //method for creating new Tile which can be only occupied by Piece OR if the provided Piece is null it must be an EmptyTile (from Map<Integer, EmptyTile>
    public static ChessTile createTile(final int tileCoordinate, final Piece piece) {
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES.get(tileCoordinate);
    }

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

        for(int i = 0; i < BoardUtils.NUMBER_OF_TILES; i++) {
            emptyTileMap.put(i, new EmptyTile(i));
        }

        //by ImmutableMap class I prevent anyone to change the settings of created emptyTileMap
        //ImmutableMap class comes from Google guava-23.0.jar library - we could use Maven to add this dependency
        return ImmutableMap.copyOf(emptyTileMap);
        //return Collections.unmodifiableMap(emptyTileMap); //alternative
    }
}
