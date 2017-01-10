package ie.gmit.sw;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;


public class AppWindow {
	private JFrame frame;
	private String name;
	private AppSummary as;

	/**
	 * @author Shane Gleeson
	 * Creating a window for the application
	 * The Size and Title are set 
	 */
	
	public AppWindow(){
		//Create a window for the application
		frame = new JFrame();
		frame.setTitle("B.Sc. in Software Development - GMIT");
		frame.setSize(550, 500);
		frame.setResizable(false);
		frame.setLayout(new FlowLayout());
		
		/**
		 * Selecting the Jar File
		 * Browsing files to select your jar file
		 * Setting the border width and height
		 */
		
        //The file panel will contain the file chooser
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEADING));
        top.setBorder(new javax.swing.border.TitledBorder("Select Jar File"));
        top.setPreferredSize(new java.awt.Dimension(500, 100));
        top.setMaximumSize(new java.awt.Dimension(500, 100));
        top.setMinimumSize(new java.awt.Dimension(500, 100));
        
        final JTextField txtFileName =  new JTextField(20);
		txtFileName.setPreferredSize(new java.awt.Dimension(100, 30));
		txtFileName.setMaximumSize(new java.awt.Dimension(100, 30));
		txtFileName.setMargin(new java.awt.Insets(2, 2, 2, 2));
		txtFileName.setMinimumSize(new java.awt.Dimension(100, 30));
		
		JButton btnChooseFile = new JButton("Browse...");
		btnChooseFile.setToolTipText("Select Jar File");
        btnChooseFile.setPreferredSize(new java.awt.Dimension(90, 30));
        btnChooseFile.setMaximumSize(new java.awt.Dimension(90, 30));
        btnChooseFile.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnChooseFile.setMinimumSize(new java.awt.Dimension(90, 30));
		btnChooseFile.addActionListener(new java.awt.event.ActionListener() {
            
			public void actionPerformed(ActionEvent evt) {
        		JFileChooser fc = new JFileChooser("./");
        		int returnVal = fc.showOpenDialog(frame);
            	if (returnVal == JFileChooser.APPROVE_OPTION) {
                	File file = fc.getSelectedFile().getAbsoluteFile();
                	name = file.getAbsolutePath(); 
                	txtFileName.setText(name);
                	System.out.println("You selected the following file: " + name);
            	}
			}
        });
		
		/**
		 * 
		 * Button to calculate stability 
		 * Performs Metric Calculator action
		 * 
		 */
		
		JButton btnOther = new JButton("Calculate Stability");
		btnOther.setToolTipText("Calculate Stability");
		btnOther.setPreferredSize(new java.awt.Dimension(150, 30));
		btnOther.setMaximumSize(new java.awt.Dimension(150, 30));
		btnOther.setMargin(new java.awt.Insets(2, 2, 2, 2));
		btnOther.setMinimumSize(new java.awt.Dimension(150, 30));

		btnOther.addActionListener(new java.awt.event.ActionListener() {

					public void actionPerformed(ActionEvent evt) {
						 /**
						 * check if their is something entered in the filepath
						 * create the summary
						 * get handle on summary table model
						 * add metric data into table model
						 */
						
						if(txtFileName.getText().length() > 1){
							try {
								MetricCalculator m = new MetricCalculator(name);
								
		                        as = new AppSummary(frame, true);

		                        TypeSummaryTableModel tm = as.getTableModel();

		                        tm.setTableData(m.getData());
							} catch (IOException e) {
								e.printStackTrace();
							}
						
							/**
							 * 
							 * make the dialog visible
							 * 
							 */
	                    as.setVisible(true);
						}
	                    else {

							System.out.println("No jar selected");
						} 
			}
		});
		
        top.add(txtFileName);
        top.add(btnChooseFile);
        top.add(btnOther);
        frame.getContentPane().add(top); 
        /**
		 * Add the panel to the window
		 */
        
        
        /**
		 * A separate panel for the programme output
		 */
        JPanel mid = new JPanel(new FlowLayout(FlowLayout.LEADING));
        mid.setBorder(new BevelBorder(BevelBorder.RAISED));
        mid.setPreferredSize(new java.awt.Dimension(500, 300));
        mid.setMaximumSize(new java.awt.Dimension(500, 300));
        mid.setMinimumSize(new java.awt.Dimension(500, 300));
        

		frame.getContentPane().add(mid);
		
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.setPreferredSize(new java.awt.Dimension(500, 50));
        bottom.setMaximumSize(new java.awt.Dimension(500, 50));
        bottom.setMinimumSize(new java.awt.Dimension(500, 50));
        
        /**
		 * Create Quit button
		 */
        
        JButton btnQuit = new JButton("Quit"); //Create Quit button
        btnQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	System.exit(0);
			}
        });
        bottom.add(btnQuit);

        frame.getContentPane().add(bottom);       
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new AppWindow();
	}
}
