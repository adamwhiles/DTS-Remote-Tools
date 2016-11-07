import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PsExec {
	
	public void execute(String machine, String user, String pass, String path) throws IOException {
		
		try {
			String command = "C:\\pstools\\psexec.exe " + "\\\\" + machine + " -u " + user + " -p " + pass;
			Process process = new ProcessBuilder("C:\\pstools\\psexec.exe ", "\\\\" + machine + " -u " + user + " -p " + pass).start();
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;
			
			JDialog dialog = new JDialog();
			dialog.setLocationByPlatform(true);
            JTextArea txtArea = new JTextArea() {
                @Override
                public Dimension getPreferredScrollableViewportSize() {
                    return new Dimension(900, 500);
                }
            };
            txtArea.setAutoscrolls(true);
            txtArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            txtArea.setFont(new Font("courier new", Font.PLAIN, 12));
            txtArea.setLineWrap(true);
            txtArea.setBackground(Color.BLACK);
            txtArea.setForeground(Color.GREEN);
            JScrollPane txtAreaScroll = new JScrollPane();
            txtAreaScroll.setViewportView(txtArea);
            txtAreaScroll.setAutoscrolls(true);
			

            dialog.add(txtAreaScroll);
            dialog.pack();
            dialog.setVisible(true);
			
			System.out.printf("Output of running is: " + command + "\n\n");
			
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				txtArea.append(line + "\n");
				
			}
			
		} catch(IOException e) {
			System.out.println("An error occurred: " + e);
		}	
		
	}

}
