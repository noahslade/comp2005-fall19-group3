import javax.swing.JButton;
// Custom Shape Button class to make shapes according to their coordinates
class CustomShapeButton extends JButton{
		
		private int isOne=0,selectionIndex=0;
		
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
		public int  getSelectionIndex() {
			return this.selectionIndex;
		}
		public void setSelectionIndex(int s) {
			this.selectionIndex=s;
		}
		
	}
	
