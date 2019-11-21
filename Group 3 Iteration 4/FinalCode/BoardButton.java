
import javax.swing.JButton;
import java.awt.Color;
//Board Button class to get the user selected place on the board, then we use our coordinates of shape to place a piece on the board
class BoardButton extends JButton{
		private int x,y;
		private boolean isPlaced=false;
		private Color color_game_square;
		// uselses commen		
		public boolean isPlaced() {
			return isPlaced;
		}
		public void setTaken(boolean isPlaced) {
			this.isPlaced = isPlaced;
		}
		public int[] getIndex() {
			int[] index= {this.x,this.y};
			return index;
		}
		public BoardButton(int x,int y){
			this.x=x;
			this.y=y;
		}
		/**
		 * Method to set the color of a taken Square in the game board
		 * @param null
		 * @return The Color/Player who placed that square that belongs to piece
		 */
		public Color getColorSquare()
		{
			return color_game_square;
		}
		/**
		 * Method to set the color of a board_button to the piece placed by the user
		 * @param Color that has to been placed
		 * @return void
		 */
		public void setColorSquare(Color playerColor)
		{
			color_game_square = playerColor;
		}
		public void highlight (){
			setBackground(Color.WHITE);
		}
	}
