import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.eclipse.jface.text.templates.GlobalTemplateVariables.User;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame frmDtsRemoteTools;

	List<InstallerItem> itemList = new ArrayList<InstallerItem>();
	private JTextField txtUserName;
	private JTextField txtPassword;
	private JTextField txtSingleMachine;
	private JTextField txtListPath;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmDtsRemoteTools.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDtsRemoteTools = new JFrame();
		frmDtsRemoteTools.setTitle("DTS Remote Tools");
		frmDtsRemoteTools.setBounds(100, 100, 703, 436);
		frmDtsRemoteTools.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DefaultListModel listModel;
		
		try {
			BufferedReader in = new BufferedReader(new FileReader("src/test1.txt"));
			String str;
			while ((str = in.readLine()) != null) {
				String[] ar=str.split(",");
				String name = ar[0];
				String path = ar[1];
				String ext = ar[2];
				System.out.println(name);
				System.out.println(path);
				System.out.println(ext);
				itemList.add(new InstallerItem(name,path,ext));
			}
			in.close();
		} catch (IOException e) {
			System.out.println("Unable to read input file");
		}
		frmDtsRemoteTools.getContentPane().setLayout(null);
		
		@SuppressWarnings("unchecked")
		final
		JList<Object> list = new JList<Object>(new Vector<InstallerItem>(itemList));
		list.setBounds(20, 34, 472, 353);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		list.setVisibleRowCount(10);
        list.setCellRenderer(new DefaultListCellRenderer() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof InstallerItem) {
                    ((JLabel) renderer).setText(((InstallerItem) value).getName());
                }
                return renderer;
            }
        });
        
		
        
		frmDtsRemoteTools.getContentPane().add(list);
		
		JButton btnSingleMachine = new JButton("Run Single Machine Install");
		btnSingleMachine.setBounds(502, 303, 175, 33);
		btnSingleMachine.setMinimumSize(new Dimension(20, 20));
		btnSingleMachine.setMaximumSize(new Dimension(20, 20));
		btnSingleMachine.setPreferredSize(new Dimension(30, 8));
		frmDtsRemoteTools.getContentPane().add(btnSingleMachine);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(502, 218, 138, 20);
		frmDtsRemoteTools.getContentPane().add(txtUserName);
		txtUserName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(502, 201, 86, 14);
		frmDtsRemoteTools.getContentPane().add(lblNewLabel);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(502, 266, 138, 20);
		frmDtsRemoteTools.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(502, 249, 86, 14);
		frmDtsRemoteTools.getContentPane().add(lblNewLabel_1);
		
		JLabel lblAvailableApplicationTo = new JLabel("Available Applications to Install:");
		lblAvailableApplicationTo.setBounds(20, 11, 329, 14);
		frmDtsRemoteTools.getContentPane().add(lblAvailableApplicationTo);
		
		JButton btnMultipleInstall = new JButton("Install on Machines From List");
		btnMultipleInstall.setBounds(502, 347, 175, 33);
		frmDtsRemoteTools.getContentPane().add(btnMultipleInstall);
		
		JLabel lblMachineNameFor = new JLabel("Machine Name for Single Install:");
		lblMachineNameFor.setBounds(502, 36, 175, 14);
		frmDtsRemoteTools.getContentPane().add(lblMachineNameFor);
		
		txtSingleMachine = new JTextField();
		txtSingleMachine.setBounds(502, 50, 175, 20);
		frmDtsRemoteTools.getContentPane().add(txtSingleMachine);
		txtSingleMachine.setColumns(10);
		
		JLabel lblImportListOf = new JLabel("Import List of Machines for Install:");
		lblImportListOf.setBounds(502, 79, 175, 14);
		frmDtsRemoteTools.getContentPane().add(lblImportListOf);
		
		txtListPath = new JTextField();
		txtListPath.setBounds(502, 95, 175, 20);
		frmDtsRemoteTools.getContentPane().add(txtListPath);
		txtListPath.setColumns(10);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(588, 118, 89, 23);
		frmDtsRemoteTools.getContentPane().add(btnBrowse);
	
	    list.addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent lse) {
	            //InstallerItem selectedValue = (InstallerItem)((JList)lse.getSource()).getSelectedValue();
	            //System.out.print(selectedValue.getPath());
	        }
	    });
	    
		btnSingleMachine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	            InstallerItem selectedValue = (InstallerItem)list.getSelectedValue();
	            System.out.print("\nThe Path is: " + selectedValue.getPath());
	    		PsExec ps = new PsExec();
	    		try {
					ps.execute(txtSingleMachine.getText(), txtUserName.getText(), txtPassword.getText(), selectedValue.getPath());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	    
	    
	    
	    
	}
}
