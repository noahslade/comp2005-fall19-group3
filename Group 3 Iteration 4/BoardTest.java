package javvv;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardTest {


	@Test
	public void testcheckCorner() {
		int butons[][] = new int[23][23] ;
		int a = 45;
		int b = 0;
		int turns = 0;
		int all[][] = new int [23][23];
		boolean corner = false;

		Blokus block = new Blokus();
		Board blokusgame = new Board(a, a, block);
		if (blokusgame.checkCorner(a, b, butons)) {
			for (int g = 0; g <= butons.length; g++){
				if (a+butons[g][0] == all[turns][0] && b + butons[g][1] == all[turns][1]) {
	                corner = true;
			}
         assertEquals(true,corner);
		}

	 }
	}
	@Test
	public void testcheckGrid() {
		int a = 0,b = 0,c=0;
		int[][] ind = new int[23][23];
		BoardButton boardd = new BoardButton(a,b);  
		Blokus block = new Blokus();
		Board hi = new Board(a, b, block);
		boardd.getIndex();
		
		c = hi.checkGrid(a, b, ind);
		assertNotSame(0,c);
		
	}
	@Test
	public void testplayAI() {
		int a = 43,b = 24,c=0;
		Blokus block = new Blokus();
		Board hi = new Board(a, b, block);
		
		hi.playAI();
		
		
	}
}
