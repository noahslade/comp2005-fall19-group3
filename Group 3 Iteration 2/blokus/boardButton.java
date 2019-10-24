import javax.swing.JButton;

class boardButton extends JButton{
		private int x,y;
		private boolean isTaken=false;
		
		
		public boolean isTaken() {
			return isTaken;
		}
		public void setTaken(boolean isTaken) {
			this.isTaken = isTaken;
		}
		public int[] getIndex() {
			int[] index= {this.x,this.y};
			return index;
		}
		public boardButton(int x,int y){
			this.x=x;
			this.y=y;
		}
	}