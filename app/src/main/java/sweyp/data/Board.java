package sweyp.data;

import sweyp.constants.UC;

public class Board {
	private int width;
	private int height;
	private char[][] data;

	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		data = new char[this.width][this.height];
		populateBoard();
	}

	public Board(int size) {
		this(size, size);
	}

	public Board() {
		this(UC.BOARD_SIZE);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public char get(int x, int y) {
		return data[x][y];
	}

	public boolean isValid(int x, int y) {
		return x >= 0 && x < width && y >= 0 && y < height;
	}
	
	private void populateBoard() {
		/* Letter Distribution (Out of 150 Tiles)
			19 - e
			13 - t
			12 - a r
			11 - i n o
			 9 - s
			 6 - d
			 5 - c h l
			 4 - f m p u
			 3 - g y
			 2 - w
			 1 - b j k q v x z
		 */
		
		/*
		 	A:9
			B:2
			C:2
			D:5
			E:13
			F:2
			G:3
			H:4
			I:8
			J:1
			K:1
			L:4
			M:2
			N:5
			O:8
			P:2
			Q:1
			R:6
			S:5
			T:7
			U:4
			V:2
			W:2
			X:1
			Y:2
			Z:1
		 */

		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				char chosen = ' ';

				int i = UC.RANDOM.nextInt(150);
				if(i < 19)
					chosen = 'E';
				else if(i < 32)
					chosen = 'T';
				else if(i < 44)
					chosen = 'A';
				else if(i < 56)
					chosen = 'R';
				else if(i < 67)
					chosen = 'I';
				else if(i < 78)
					chosen = 'N';
				else if(i < 89)
					chosen = 'O';
				else if(i < 98)
					chosen = 'S';
				else if(i < 104)
					chosen = 'D';
				else if(i < 109)
					chosen = 'C';
				else if(i < 114)
					chosen = 'H';
				else if(i < 119)
					chosen = 'L';
				else if(i < 123)
					chosen = 'F';
				else if(i < 127)
					chosen = 'M';
				else if(i < 131)
					chosen = 'P';
				else if(i < 135)
					chosen = 'U';
				else if(i < 138)
					chosen = 'G';
				else if(i < 141)
					chosen = 'Y';
				else if(i < 143)
					chosen = 'W';
				else if(i < 144)
					chosen = 'B';
				else if(i < 145)
					chosen = 'J';
				else if(i < 146)
					chosen = 'K';
				else if(i < 147)
					chosen = 'Q';
				else if(i < 148)
					chosen = 'V';
				else if(i < 149)
					chosen = 'X';
				else if(i < 150)
					chosen = 'Z';
				
				data[x][y] = chosen;
			}
		}
	}

	@SuppressWarnings("unused")
	private void printBoard() {
		for(int i = 0; i < width; i++)
			System.out.print("+---");
		System.out.println("+");
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				System.out.print("| " + data[x][y] + " ");
			}
			System.out.println("|");
			for(int i = 0; i < width; i++)
				System.out.print("+---");
			System.out.println("+");
		}
	}
}
