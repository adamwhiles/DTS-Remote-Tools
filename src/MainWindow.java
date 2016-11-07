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

public class MainWindow {

	private JFrame frmDtsRemoteTools;

	List<InstallerItem> itemList = new ArrayList<InstallerItem>();
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
			//str = in.readLine();
			while ((str = in.readLine()) != null) {
				String[] ar=str.split(",");
				String name = ar[0];
				String path = ar[1];
				String ext = ar[2];
				System.out.println(name);
				System.out.println(path);
				System.out.println(ext);
				itemList.add(new InstallerItem(name,path,ext));
				//InstallerItem item = new InstallerItem();
				//item.setName(name);
				//item.setPath(path);
				//item.setExt(ext);
				//itemList.add(item);
			}
			in.close();
		} catch (IOException e) {
			System.out.println("Unable to read input file");
		}
		
		/*
		listModel = new DefaultListModel();
		InstallerItem item = new InstallerItem();
		item.setName("Test File");
		item.setPath("C:\\windows\\test.txt");
		item.setExt("txt");
		listModel.addElement(item.getName());
		*/
		
		@SuppressWarnings("unchecked")
		JList<Object> list = new JList<Object>(new Vector<InstallerItem>(itemList));
		
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
        
		
        
		frmDtsRemoteTools.getContentPane().add(list, BorderLayout.WEST);
	//}
	
    list.addListSelectionListener(new ListSelectionListener(){
        public void valueChanged(ListSelectionEvent lse) {
            InstallerItem selectedValue = (InstallerItem)((JList)lse.getSource()).getSelectedValue();
            System.out.print(selectedValue.getPath());
        }
    });
	}

}
