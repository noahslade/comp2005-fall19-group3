import javax.swing.JButton;

class customShapeButton extends JButton{
		
		private int isOne=0,actionIndex=0;
		
		public int shapeSize=0;
		private int[] index= {0,0};
		
		public boolean  getOne() {
			return this.isOne==1;
		}
		public void setOne(int s) {
			this.isOne=s;
		}
		public int[]  getIndex() {
			return this.index;
		}
		public void setIndex(int x,int y) {
			this.index[0]=x;
			this.index[1]=y;
		}
		public int  getActionIndex() {
			return this.actionIndex;
		}
		public void setActionIndex(int s) {
			this.actionIndex=s;
		}
		
	}
	