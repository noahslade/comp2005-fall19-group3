
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveManager {

	public static void main(String[] args) {
	
		String fileName = "C:\\Users\\johan\\git\\comp2005-fall19-group3\\Group 3 Iteration 2\\blokus\\src\\test.txt";
		try {
			SaveManager dataTest= new SaveManager(fileName,false);
			dataTest.writeToFile("Ring Ring goes the telephone, there is no one home");
		}
		catch (IOException e)
		{
			System.out.println("METE BIEN EL DEDO");
		}
		
	}
	//Fields
	private String path;
	private boolean append_to_file= false;
	
	/**
	 * Constructor of the class.
	 */
	public SaveManager(String file_path, boolean appendON)
	{
		path= file_path;
		append_to_file=appendON;
	}
	
	/**
	 * Method to write to text file to save the data
	 * @param String textLine
	 * @return void
	 */
	public void writeToFile(String textLine) throws IOException
	{
		try {
			
		FileWriter write = new FileWriter(path,append_to_file);
		//FileWriter only writes bytes, thus , it needs PrintWriter to write plain text
		PrintWriter print_line= new PrintWriter(write);
		print_line.printf("%s" +"%n", textLine);
		// "%s" means a string of characters of any length
		// "%n means a newline
		print_line.close();
	}
		catch (IOException e)
		{
			System.out.println("NO way");
		}
		
	}

}
