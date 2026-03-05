package com.toyrobot;

public final class Table {
   
	private final int width;
    private final int height;

    public Table(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Table size must be positive");
        }
        this.width = width;
        this.height = height;
    }

    public boolean isOnTable(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
}
