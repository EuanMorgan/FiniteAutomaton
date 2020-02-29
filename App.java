import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		//This is the main app file, it reads in the input and passes it to the relevant program
		
		if(args.length != 2) {
			System.out.println("Please specify filenames. E.g. java App file1.txt file2.txt");
			return;
		}
		
		String filename = args[0];
		String filename2 = args[1];
		File file = new File(filename);
		File file2 = new File(filename2);
		try {
			Scanner sc = new Scanner(file);
			while(sc.hasNext()) {
				System.out.println(sc.next());
			}
		} catch (FileNotFoundException e) {
			
			System.out.println("File not found! Please enter valid filename");
		}
		
		
		
	}

}
