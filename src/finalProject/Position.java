package finalProject;

/**
 * Simple immutable class representing a position on the 10x10 grid. Used for
 * player start, hiker last-known position, and blocked cells.
 * 
 * @author Bowen Bertheson
 */
public class Position {

	private final int row;
	private final int col;

	/**
	 * Creates a new position with given row and column.
	 */
	public Position(int row, int col) {
		if (row < 0 || row >= 10 || col < 0 || col >= 10) {
			throw new IllegalArgumentException("Row and col must be between 0 and 9");
		}
		this.row = row;
		this.col = col;
	}

	// Getters
	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	/**
	 * Two positions are equal if they have the same row and column.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Position other = (Position) obj;
		return row == other.row && col == other.col;
	}

	@Override
	public int hashCode() {
		return 31 * row + col;
	}

	@Override
	public String toString() {
		return "(" + row + ", " + col + ")";
	}
}