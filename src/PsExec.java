import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PsExec {
	
	public void execute(String path) throws IOException {
		
		try {
			Process process = new ProcessBuilder("C:\\pstools\\psexec.exe").start();
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;
			
			System.out.printf("Output of running is: ");
			
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			
		} catch(IOException e) {
			System.out.println("An error occurred: " + e);
		}	
		
	}

}
