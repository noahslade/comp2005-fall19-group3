package javvv;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestCustomShapeButton {

	@Test
	void testgetOne() {
		CustomShapeButton buton = new CustomShapeButton();
		
		int result = 0;
		if (buton.getOne()) {
			result = 1;
			assertEquals(1,result);
		}
	}
	@Test
	void testgetSelectionIndex() {
		CustomShapeButton buton = new CustomShapeButton();
		buton.setSelectionIndex(3094);
		
	    if (buton.getSelectionIndex() == 3094) {
	    	assertEquals(3094,buton.getSelectionIndex());
	    }
	}
   @Test
   void testgetindex() {
	    int[] a = new int[21] ;
	    int c = 650,d = 340;
	    int f = 0,g = 0;
	    a[0] = c;
	    a[1] = d;
	    boolean ind = false;
		CustomShapeButton buton = new CustomShapeButton();
		buton.setIndex(c,d);
	    a = buton.getIndex();
	    	for (int i=0; i<=1; i++) {
	           f = a[0];
	           g = a[1];
	            }
	            if (f==650 && g ==340 ) {
	            	ind = true;
	            }
	    assertTrue(ind);
	    }		
   
}
