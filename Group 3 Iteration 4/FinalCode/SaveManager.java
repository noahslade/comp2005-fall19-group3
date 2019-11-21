
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveManager {

	public static void main(String[] args) {
	
		String fileName = "C:\\Users\\johan\\Desktop\\test.txt";
		File file = new File(fileName);
		SaveManager dataTest= new SaveManager(fileName,true);
		try {
			if (file.exists())
			{
				dataTest.setAppend(false);
				dataTest.writeToFile("Soy el rey");
			}
			else 
			{
				
				dataTest.writeToFile("Ring Ring goes the telephone, there is no one home");
				dataTest.writeToFile("Si funciona");
			}
			
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
	 * Method to change the append or write
	 */
	public void setAppend(boolean alpha)
	{
		append_to_file=alpha;
	}
	/**
	 * Method to write to text file to save the data
	 * @param String textLine
	 * @return void
	 */
	public void writeToFile(String textLine) throws IOException
	{
			try {
			
			if (!append_to_file) //We are looking to delete the contents of the file
			{
			
				FileWriter write = new FileWriter(path,append_to_file); // We are negating appending
				PrintWriter print_line= new PrintWriter(write);
				print_line.print(textLine);
				print_line.close();
				write.close();
				
				
			}
			else
			{
				FileWriter write = new FileWriter(path,append_to_file); 
				PrintWriter print_line= new PrintWriter(write); //FileWriter only writes bytes, thus , it needs PrintWriter to write plain text
				print_line.printf("%s" +"%n", textLine);// "%s" means a string of characters of any length
														// "%n means a newline
				write.close();
				print_line.close();
			}
		
		
		
		
		
		
		
		
	}
		catch (IOException e)
		{
			System.out.println("NO way");
		}
		
	}

}
