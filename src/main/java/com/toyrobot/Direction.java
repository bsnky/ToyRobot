package com.toyrobot;

import java.util.Locale;

public enum Direction {
    
	NORTH, EAST, SOUTH, WEST;

    public Direction left() {
        switch (this) {
            case NORTH: return WEST;
            case WEST:  return SOUTH;
            case SOUTH: return EAST;
            case EAST:  return NORTH;
            default: throw new IllegalStateException("Unknown direction: " + this);
        }
    }

    public Direction right() {
        switch (this) {
            case NORTH: return EAST;
            case EAST:  return SOUTH;
            case SOUTH: return WEST;
            case WEST:  return NORTH;
            default: throw new IllegalStateException("Unknown direction: " + this);
        }
    }

    public static Direction parse(String raw) {
        if (raw == null) return null;
        String s = raw.trim().toUpperCase(Locale.ROOT);
        try {
            return Direction.valueOf(s);
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }
    
}
