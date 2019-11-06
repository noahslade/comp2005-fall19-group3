import java.awt.Color;

import javax.swing.JButton;
//Board Button class to get the user selected place on the board, then we use our coordinates of shape to place a piece on the board
class BoardButton extends JButton{
		private int x,y;
		private boolean isPlaced=false;
		// uselses commen
		public BoardButton(int x,int y){
			this.x=x;
			this.y=y;
		}

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

		public void highlight(){
			setBackground(Color.GRAY);
		}
		
	}
