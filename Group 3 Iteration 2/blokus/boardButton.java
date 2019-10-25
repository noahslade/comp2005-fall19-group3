import javax.swing.JButton;

class BoardButton extends JButton{
		private int x,y;
		private boolean isPlaced=false;
		
		
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
	}