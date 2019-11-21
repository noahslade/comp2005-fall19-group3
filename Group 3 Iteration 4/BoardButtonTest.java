package javvv;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardButtonTest {

	@Test
	void test() {
		BoardButton piece = new BoardButton(0, 0);
		
		if (piece.isPlaced()) {
			assertEquals(true,piece);
		}
    }
	@Test
    public void testsettaken(){ 
		BoardButton piece = new BoardButton(0, 0);
		if(piece.isPlaced()) {
			piece.setTaken(piece.isPlaced());
		}
		
	}
	@Test
	public void testSetColor() {
		BoardButton piece = new BoardButton(0, 0);
		piece.setColorSquare(piece.getColorSquare());
	}

}
