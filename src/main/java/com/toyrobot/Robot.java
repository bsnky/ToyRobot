package com.toyrobot;

import java.util.Objects;
import java.util.Optional;

public final class Robot {
   
	private final Table table;

    private boolean placed;
    private int x;
    private int y;
    private Direction facing;

    public Robot(Table table) {
        this.table = Objects.requireNonNull(table, "table");
    }

    public boolean isPlaced() {
        return placed;
    }

    public void place(int x, int y, Direction facing) {
        if (facing == null) return;
        if (!table.isOnTable(x, y)) return;

        this.x = x;
        this.y = y;
        this.facing = facing;
        this.placed = true;
    }

    public void move() {
        if (!placed) return;

        int nx = x;
        int ny = y;

        switch (facing) {
            case NORTH: ny = y + 1; break;
            case SOUTH: ny = y - 1; break;
            case EAST:  nx = x + 1; break;
            case WEST:  nx = x - 1; break;
            default: throw new IllegalStateException("Unknown direction: " + facing);
        }

        if (table.isOnTable(nx, ny)) {
            x = nx;
            y = ny;
        }
        // else ignore (prevents falling)
    }

    public void left() {
        if (!placed) return;
        facing = facing.left();
    }

    public void right() {
        if (!placed) return;
        facing = facing.right();
    }

    public Optional<String> report() {
        if (!placed) return Optional.empty();
        return Optional.of(x + "," + y + "," + facing);
    }
    
}
