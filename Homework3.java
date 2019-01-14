import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.net.URL;
import java.awt.*; 
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextPane;
import javax.swing.JLayeredPane;
/**
 * 
 * @author krobinson20
 *this is the ui for my city
 */
public class Homework3 implements MouseMotionListener, ActionListener {
	private JPanel panel_1; //jpanel that is the school in this city
	private JPanel panel; //jpanel that is the city hall for this city
	private JFrame frame; // frame for the city
	private JLayeredPane lpane; //invisible layer that allows for objects to be dragged across the screen
	private JButton btnAddKid; //button to create a sprite of a kid
	private JButton btnAddOfficer; //button to create a sprite of an officer
	private JLabel lblSchool;
	public List<JLabel> police= new ArrayList<>(); //array list to hosue all of the jlabels for the sprites for officers
	public List<JLabel> kids= new ArrayList<>(); //array list to house all of the jlabels for the sprites for kids
	public List<Integer> labels = new ArrayList<>();  //array list to house the type of person being dragged
	Point diffDrag;
	public List<Police> CH = new ArrayList<>();
	public List<Kid> School = new ArrayList<>(); 
	
	/**
	*above is the lsit of variables used for the ui in this program
	/**
	/**
	 * Create the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Homework3 window = new Homework3();
					window.frame.setVisible(true);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Homework3() {
		initialize();
		
	}
	/**
	 * below is the function that keeps track of the mouse being dragged across the screen
	 * it keeps track of the mouse position and checks to see if a police has been moved to the city hall or a kid has been
	 * moved to the school
	**/
	public void mouseDragged(MouseEvent e) {
	//	System.out.printf("dragging %s\n", e.getPoint().toString());
		JLabel label = null; //variable to house the label being dragged
		int person =0; 
		int index = 0;
		for (int i=0; i<lpane.getComponentCount(); i++) {
			if (lpane.getComponent(i) instanceof JLabel && lpane.getComponent(i).getBounds().contains(e.getPoint())) {
				label = (JLabel)lpane.getComponent(i); //sets the label variable to the currently clicked on jlabel
				person = labels.get(i);
				index = i;
			}
		}
		if(label!= null)
		{
			if(diffDrag==null)
				diffDrag = new Point(e.getX() - label.getBounds().x, e.getY() - label.getBounds().y);
			label.setBounds(e.getX() - diffDrag.x, e.getY() -diffDrag.y, label.getBounds().width, label.getBounds().height);
		//	System.out.printf("moved label to <%d, %d>", e.getX() - diffDrag.x, e.getY() -diffDrag.y);
			frame.repaint();
			
			
			/**
			 * below makes sure that a kid jlabel is being dragged over to the school panel and if it is, it will remove the sprite from the schreen and 
			 * remove the information about that label from the respective lists
			 */
			if(person == 2 && panel_1.getBounds().contains(e.getPoint())) //checks to see if the mouse has dragged a kid type jlabel to the school panel
			{
				lpane.remove(label); //removes the sprite from the screen
				labels.remove(index);
				 School.add(new Kid()); //this calls the generic constructor for kid and puts the object in the array list for the school
				if (kids != null)
				{
					kids.remove(label);
				}
				/**
				 * the code will check to see if the school is empty and if it is will output that to the console
				 * if the school is not empty then the names, ages, and favorite candies of each student will be outputted to the consol
				 */
				if(School.size() == 0)
				{
					System.out.printf("\nThe School is empty\n");
				}
				else
				{
					System.out.printf("\nThe total number of students in school is: %d", School.size());
					System.out.printf("\nThe kids currently in school are: \n");
					for(int i =0; i<School.size();i++)
					{
						System.out.printf("%s who is %s years old and loves %s\n" , School.get(i).getname(), School.get(i).getage(),School.get(i).getCandy());
					}
				}
			}
			
				
			/**
			 * below checks to see if the mouse has dragged a police jlabel to the city hall and if that is true
			 * it will remove the sprite from the screen and delete the informaiton about that sprite from its respective lists
			 */
			else if(person == 1 && panel.getBounds().contains(e.getPoint()))  //checks to see if the mouse has dragged a officer type sprite to the city hall
			{
					lpane.remove(label); //removes the sprite from the screen
					labels.remove(index);
					CH.add(new Police()); //this calls the generic constructor for the police object and puts it in the array  list for the city hall
					if (police != null)
						police.remove(label);
				/**
				 * it will check and see if the city hall is empty and if it is, it will output to the console that it is
				 * if the city hall is not empty then it will output the names, ages, and the roles of each officer in the city hall
				 */
					if(CH.size() == 0)
					{
						System.out.printf("\nThe City Hall is empty\n");
					}
					else
					{
						System.out.printf("\nThe total number of  officers in City Hall is: %d", CH.size());
						System.out.printf("\nThe Officers currently in school are: \n");
						for(int i =0; i<CH.size();i++)
						{
							System.out.printf("%s who is %s years old and his role is %s\n" , CH.get(i).getname(), CH.get(i).getage(),CH.get(i).getrole());
						}
				}
			
		}		}
	}
	
	
	
	public void mouseMoved(MouseEvent e) {
		diffDrag = null;
	}
			
		/**
		 * below checks to see if the user has clicked either of the buttons
		 * if they have it will paint a new jlabel with the correct sprite onto the screen
		 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAddKid) { //checks to see if the button pressed is the 
			CreateKid();
			for(int i=0;i<kids.size();i++)
			{
				//System.out.printf("\tkids: %s\n", kids.get(i).getBounds().toString());
			}
			frame.getContentPane().revalidate();
			frame.getContentPane().repaint();
			
			for(int i=0;i<kids.size();i++)
			{
				//System.out.printf("\tkids: %s\n", kids.get(i).getBounds().toString());
			}
		}
		else if (e.getSource() == btnAddOfficer) {
			CreatePolice();
			for(int i=0;i<police.size();i++)
			{
				//System.out.printf("\tpolice: %s\n", police.get(i).getBounds().toString());
			}
			frame.getContentPane().revalidate();
			frame.getContentPane().repaint();
			
			for(int i=0;i<police.size();i++)
			{
				//System.out.printf("\tpolice: %s\n", police.get(i).getBounds().toString());
			}
		}
	}


	
	/** 
	 * below is the function that gets called when the user clicks on the add officer button
	 * it makes a new jlabel and sets the image on that label to the sprite of the officer
	 * it then repaints the scsreen so that the sprite will appear
	 */
	public void CreatePolice()
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		URL imgURL = getClass().getResource("/resources/officerSprite.png");
		Image img = toolkit.getImage(imgURL);
		ImageIcon icon = new ImageIcon(img);
		JLabel label = new JLabel(icon);
		label.setPreferredSize(new Dimension(40,40));
		police.add(label);
		lpane.add(label);
		labels.add(1);
	//System.out.printf("Content pane: %s\n", frame.getContentPane().getBounds().toString());
		
	}
	/**
	 * below is the function that gets called when soemone clicks on the add kid button
	 * it will create a new jlabel and assign a sprite of a kid to that jlabel and refresh the screen so that the sprite will appear
	 */
	public void CreateKid()
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		URL imgURL = getClass().getResource("/resources/schoolKid.png");
		Image img = toolkit.getImage(imgURL);
		ImageIcon icon = new ImageIcon(img);
		JLabel label = new JLabel(icon);
		label.setPreferredSize(new Dimension(40,40));
		kids.add(label);
		lpane.add(label);
		labels.add(2);
		
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("My City");
		frame.setBounds(100, 100, 800, 560);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		lpane = new JLayeredPane();
		Color newc = new Color(0f,0f,0f,0f);
		lpane.setBackground(newc);
		GridBagConstraints gbc_lpane = new GridBagConstraints(); 
		gbc_lpane.gridheight = 15;
		gbc_lpane.gridwidth = 20;
		gbc_lpane.insets = new Insets(0,0,0,5);
		gbc_lpane.fill = GridBagConstraints.BOTH;
		gbc_lpane.gridx=0;
		gbc_lpane.gridy=0;
		lpane.setOpaque(true);
		frame.getContentPane().add(lpane, gbc_lpane);
		lpane.setLayout(new GridBagLayout());
		
		lpane.addMouseMotionListener(this);
		
		JPanel panel_37 = new JPanel();
		GridBagConstraints gbc_panel_37 = new GridBagConstraints();
		gbc_panel_37.gridwidth = 3;
		gbc_panel_37.insets = new Insets(0, 0, 5, 5);
		gbc_panel_37.fill = GridBagConstraints.BOTH;
		gbc_panel_37.gridx = 11;
		gbc_panel_37.gridy = 1;
		frame.getContentPane().add(panel_37, gbc_panel_37);
		GridBagLayout gbl_panel_37 = new GridBagLayout();
		gbl_panel_37.columnWidths = new int[]{0, 0, 0};
		gbl_panel_37.rowHeights = new int[]{0, 0};
		gbl_panel_37.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_37.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_37.setLayout(gbl_panel_37);
		
		
		JTextPane txtpnStudentsInSchool = new JTextPane();
		txtpnStudentsInSchool.setText("Students in school");
		GridBagConstraints gbc_txtpnStudentsInSchool = new GridBagConstraints();
		gbc_txtpnStudentsInSchool.gridwidth = 2;
		gbc_txtpnStudentsInSchool.fill = GridBagConstraints.BOTH;
		gbc_txtpnStudentsInSchool.gridx = 0;
		gbc_txtpnStudentsInSchool.gridy = 0;
		panel_37.add(txtpnStudentsInSchool, gbc_txtpnStudentsInSchool);
		
		JPanel panel_35 = new JPanel();
		GridBagConstraints gbc_panel_35 = new GridBagConstraints();
		gbc_panel_35.gridwidth = 3;
		gbc_panel_35.gridheight = 4;
		gbc_panel_35.insets = new Insets(0, 0, 5, 5);
		gbc_panel_35.fill = GridBagConstraints.BOTH;
		gbc_panel_35.gridx = 11;
		gbc_panel_35.gridy = 2;
		frame.getContentPane().add(panel_35, gbc_panel_35);
		GridBagLayout gbl_panel_35 = new GridBagLayout();
		gbl_panel_35.columnWidths = new int[]{0, 0};
		gbl_panel_35.rowHeights = new int[]{0, 0};
		gbl_panel_35.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_35.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_35.setLayout(gbl_panel_35);
		
		JList list_2 = new JList();
		GridBagConstraints gbc_list_2 = new GridBagConstraints();
		gbc_list_2.fill = GridBagConstraints.BOTH;
		gbc_list_2.gridx = 0;
		gbc_list_2.gridy = 0;
		panel_35.add(list_2, gbc_list_2);
		
		JPanel panel_36 = new JPanel();
		GridBagConstraints gbc_panel_36 = new GridBagConstraints();
		gbc_panel_36.insets = new Insets(0, 0, 5, 5);
		gbc_panel_36.fill = GridBagConstraints.BOTH;
		gbc_panel_36.gridx = 1;
		gbc_panel_36.gridy = 5;
		frame.getContentPane().add(panel_36, gbc_panel_36);
		GridBagLayout gbl_panel_36 = new GridBagLayout();
		gbl_panel_36.columnWidths = new int[]{0, 0, 0};
		gbl_panel_36.rowHeights = new int[]{0, 0};
		gbl_panel_36.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_36.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_36.setLayout(gbl_panel_36);
		
		JTextPane txtpnPoliceInCity = new JTextPane();
		txtpnPoliceInCity.setText("Police in City Hall");
		GridBagConstraints gbc_txtpnPoliceInCity = new GridBagConstraints();
		gbc_txtpnPoliceInCity.gridwidth = 2;
		gbc_txtpnPoliceInCity.fill = GridBagConstraints.BOTH;
		gbc_txtpnPoliceInCity.gridx = 0;
		gbc_txtpnPoliceInCity.gridy = 0;
		panel_36.add(txtpnPoliceInCity, gbc_txtpnPoliceInCity);
		
		JPanel panel_34 = new JPanel();
		GridBagConstraints gbc_panel_34 = new GridBagConstraints();
		gbc_panel_34.gridheight = 4;
		gbc_panel_34.insets = new Insets(0, 0, 5, 5);
		gbc_panel_34.fill = GridBagConstraints.BOTH;
		gbc_panel_34.gridx = 1;
		gbc_panel_34.gridy = 6;
		frame.getContentPane().add(panel_34, gbc_panel_34);
		GridBagLayout gbl_panel_34 = new GridBagLayout();
		gbl_panel_34.columnWidths = new int[]{50, 1, 0};
		gbl_panel_34.rowHeights = new int[]{1, 0, 0};
		gbl_panel_34.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_34.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_34.setLayout(gbl_panel_34);
		
		JList list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 0);
		gbc_list.anchor = GridBagConstraints.NORTHWEST;
		gbc_list.gridx = 1;
		gbc_list.gridy = 0;
		panel_34.add(list, gbc_list);
		
		JList list_1 = new JList();
		GridBagConstraints gbc_list_1 = new GridBagConstraints();
		gbc_list_1.gridheight = 2;
		gbc_list_1.insets = new Insets(0, 0, 0, 5);
		gbc_list_1.fill = GridBagConstraints.BOTH;
		gbc_list_1.gridx = 0;
		gbc_list_1.gridy = 0;
		panel_34.add(list_1, gbc_list_1);
		
		
		JPanel panel_33 = new JPanel();
		panel_33.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_33 = new GridBagConstraints();
		gbc_panel_33.insets = new Insets(0, 0, 5, 5);
		gbc_panel_33.fill = GridBagConstraints.BOTH;
		gbc_panel_33.gridx = 5;
		gbc_panel_33.gridy = 0;
		frame.getContentPane().add(panel_33, gbc_panel_33);
		
		JLabel lblMyCity = new JLabel("My City");
		GridBagConstraints gbc_lblMyCity = new GridBagConstraints();
		gbc_lblMyCity.insets = new Insets(0, 0, 5, 5);
		gbc_lblMyCity.gridwidth = 2;
		gbc_lblMyCity.gridx = 10;
		gbc_lblMyCity.gridy = 0;
		frame.getContentPane().add(lblMyCity, gbc_lblMyCity);
		
		JPanel panel_32 = new JPanel();
		panel_32.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_32 = new GridBagConstraints();
		gbc_panel_32.insets = new Insets(0, 0, 5, 5);
		gbc_panel_32.fill = GridBagConstraints.BOTH;
		gbc_panel_32.gridx = 5;
		gbc_panel_32.gridy = 1;
		frame.getContentPane().add(panel_32, gbc_panel_32);
		
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.gridheight = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 2;
		frame.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblCityHall = new JLabel("City Hall");
		GridBagConstraints gbc_lblCityHall = new GridBagConstraints();
		gbc_lblCityHall.gridx = 1;
		gbc_lblCityHall.gridy = 0;
		panel.add(lblCityHall, gbc_lblCityHall);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setForeground(Color.GRAY);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 5;
		gbc_panel_2.gridy = 2;
		frame.getContentPane().add(panel_2, gbc_panel_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setForeground(Color.GRAY);
		panel_4.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 5);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 5;
		gbc_panel_4.gridy = 3;
		frame.getContentPane().add(panel_4, gbc_panel_4);
		
		JPanel panel_3 = new JPanel();
		panel_3.setForeground(Color.GRAY);
		panel_3.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 5;
		gbc_panel_3.gridy = 4;
		frame.getContentPane().add(panel_3, gbc_panel_3);
		
		JPanel panel_5 = new JPanel();
		panel_5.setForeground(Color.GRAY);
		panel_5.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 5);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 5;
		gbc_panel_5.gridy = 5;
		frame.getContentPane().add(panel_5, gbc_panel_5);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.RED);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 2;
		gbc_panel_1.gridwidth = 4;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 15;
		gbc_panel_1.gridy = 4;
		frame.getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		lblSchool = new JLabel("School");
		GridBagConstraints gbc_lblSchool = new GridBagConstraints();
		gbc_lblSchool.gridx = 1;
		gbc_lblSchool.gridy = 0;
		panel_1.add(lblSchool, gbc_lblSchool);
		
		JPanel panel_6 = new JPanel();
		panel_6.setForeground(Color.GRAY);
		panel_6.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.insets = new Insets(0, 0, 5, 5);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 5;
		gbc_panel_6.gridy = 6;
		frame.getContentPane().add(panel_6, gbc_panel_6);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.insets = new Insets(0, 0, 5, 5);
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 6;
		gbc_panel_7.gridy = 6;
		frame.getContentPane().add(panel_7, gbc_panel_7);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.insets = new Insets(0, 0, 5, 5);
		gbc_panel_8.fill = GridBagConstraints.BOTH;
		gbc_panel_8.gridx = 7;
		gbc_panel_8.gridy = 6;
		frame.getContentPane().add(panel_8, gbc_panel_8);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.insets = new Insets(0, 0, 5, 5);
		gbc_panel_9.fill = GridBagConstraints.BOTH;
		gbc_panel_9.gridx = 8;
		gbc_panel_9.gridy = 6;
		frame.getContentPane().add(panel_9, gbc_panel_9);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_11 = new GridBagConstraints();
		gbc_panel_11.insets = new Insets(0, 0, 5, 5);
		gbc_panel_11.fill = GridBagConstraints.BOTH;
		gbc_panel_11.gridx = 9;
		gbc_panel_11.gridy = 6;
		frame.getContentPane().add(panel_11, gbc_panel_11);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_12 = new GridBagConstraints();
		gbc_panel_12.insets = new Insets(0, 0, 5, 5);
		gbc_panel_12.fill = GridBagConstraints.BOTH;
		gbc_panel_12.gridx = 10;
		gbc_panel_12.gridy = 6;
		frame.getContentPane().add(panel_12, gbc_panel_12);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_10 = new GridBagConstraints();
		gbc_panel_10.insets = new Insets(0, 0, 5, 5);
		gbc_panel_10.fill = GridBagConstraints.BOTH;
		gbc_panel_10.gridx = 11;
		gbc_panel_10.gridy = 6;
		frame.getContentPane().add(panel_10, gbc_panel_10);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_13 = new GridBagConstraints();
		gbc_panel_13.insets = new Insets(0, 0, 5, 5);
		gbc_panel_13.fill = GridBagConstraints.BOTH;
		gbc_panel_13.gridx = 12;
		gbc_panel_13.gridy = 6;
		frame.getContentPane().add(panel_13, gbc_panel_13);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_14 = new GridBagConstraints();
		gbc_panel_14.insets = new Insets(0, 0, 5, 5);
		gbc_panel_14.fill = GridBagConstraints.BOTH;
		gbc_panel_14.gridx = 13;
		gbc_panel_14.gridy = 6;
		frame.getContentPane().add(panel_14, gbc_panel_14);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_15 = new GridBagConstraints();
		gbc_panel_15.insets = new Insets(0, 0, 5, 5);
		gbc_panel_15.fill = GridBagConstraints.BOTH;
		gbc_panel_15.gridx = 14;
		gbc_panel_15.gridy = 6;
		frame.getContentPane().add(panel_15, gbc_panel_15);
		
		JPanel panel_16 = new JPanel();
		panel_16.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_16 = new GridBagConstraints();
		gbc_panel_16.insets = new Insets(0, 0, 5, 5);
		gbc_panel_16.fill = GridBagConstraints.BOTH;
		gbc_panel_16.gridx = 15;
		gbc_panel_16.gridy = 6;
		frame.getContentPane().add(panel_16, gbc_panel_16);
		
		JPanel panel_17 = new JPanel();
		panel_17.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_17 = new GridBagConstraints();
		gbc_panel_17.insets = new Insets(0, 0, 5, 5);
		gbc_panel_17.fill = GridBagConstraints.BOTH;
		gbc_panel_17.gridx = 16;
		gbc_panel_17.gridy = 6;
		frame.getContentPane().add(panel_17, gbc_panel_17);
		
		JPanel panel_18 = new JPanel();
		panel_18.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_18 = new GridBagConstraints();
		gbc_panel_18.insets = new Insets(0, 0, 5, 5);
		gbc_panel_18.fill = GridBagConstraints.BOTH;
		gbc_panel_18.gridx = 17;
		gbc_panel_18.gridy = 6;
		frame.getContentPane().add(panel_18, gbc_panel_18);
		
		JPanel panel_19 = new JPanel();
		panel_19.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_19 = new GridBagConstraints();
		gbc_panel_19.insets = new Insets(0, 0, 5, 5);
		gbc_panel_19.fill = GridBagConstraints.BOTH;
		gbc_panel_19.gridx = 18;
		gbc_panel_19.gridy = 6;
		frame.getContentPane().add(panel_19, gbc_panel_19);
		
		JPanel panel_20 = new JPanel();
		panel_20.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_20 = new GridBagConstraints();
		gbc_panel_20.insets = new Insets(0, 0, 5, 5);
		gbc_panel_20.fill = GridBagConstraints.BOTH;
		gbc_panel_20.gridx = 19;
		gbc_panel_20.gridy = 6;
		frame.getContentPane().add(panel_20, gbc_panel_20);
		
		JPanel panel_21 = new JPanel();
		panel_21.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_21 = new GridBagConstraints();
		gbc_panel_21.insets = new Insets(0, 0, 5, 5);
		gbc_panel_21.fill = GridBagConstraints.BOTH;
		gbc_panel_21.gridx = 20;
		gbc_panel_21.gridy = 6;
		frame.getContentPane().add(panel_21, gbc_panel_21);
		
		JPanel panel_22 = new JPanel();
		panel_22.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_22 = new GridBagConstraints();
		gbc_panel_22.insets = new Insets(0, 0, 5, 5);
		gbc_panel_22.fill = GridBagConstraints.BOTH;
		gbc_panel_22.gridx = 21;
		gbc_panel_22.gridy = 6;
		frame.getContentPane().add(panel_22, gbc_panel_22);
		
		JPanel panel_23 = new JPanel();
		panel_23.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_23 = new GridBagConstraints();
		gbc_panel_23.insets = new Insets(0, 0, 5, 0);
		gbc_panel_23.fill = GridBagConstraints.BOTH;
		gbc_panel_23.gridx = 22;
		gbc_panel_23.gridy = 6;
		frame.getContentPane().add(panel_23, gbc_panel_23);
		
		JPanel panel_24 = new JPanel();
		panel_24.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_24 = new GridBagConstraints();
		gbc_panel_24.insets = new Insets(0, 0, 5, 5);
		gbc_panel_24.fill = GridBagConstraints.BOTH;
		gbc_panel_24.gridx = 5;
		gbc_panel_24.gridy = 7;
		frame.getContentPane().add(panel_24, gbc_panel_24);
		
		JPanel panel_25 = new JPanel();
		panel_25.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_25 = new GridBagConstraints();
		gbc_panel_25.insets = new Insets(0, 0, 5, 5);
		gbc_panel_25.fill = GridBagConstraints.BOTH;
		gbc_panel_25.gridx = 5;
		gbc_panel_25.gridy = 8;
		frame.getContentPane().add(panel_25, gbc_panel_25);
		
		JPanel panel_26 = new JPanel();
		panel_26.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_26 = new GridBagConstraints();
		gbc_panel_26.insets = new Insets(0, 0, 5, 5);
		gbc_panel_26.fill = GridBagConstraints.BOTH;
		gbc_panel_26.gridx = 5;
		gbc_panel_26.gridy = 9;
		frame.getContentPane().add(panel_26, gbc_panel_26);
		
		JPanel panel_27 = new JPanel();
		panel_27.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_27 = new GridBagConstraints();
		gbc_panel_27.insets = new Insets(0, 0, 5, 5);
		gbc_panel_27.fill = GridBagConstraints.BOTH;
		gbc_panel_27.gridx = 5;
		gbc_panel_27.gridy = 10;
		frame.getContentPane().add(panel_27, gbc_panel_27);
		
		JPanel panel_28 = new JPanel();
		panel_28.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_28 = new GridBagConstraints();
		gbc_panel_28.insets = new Insets(0, 0, 5, 5);
		gbc_panel_28.fill = GridBagConstraints.BOTH;
		gbc_panel_28.gridx = 5;
		gbc_panel_28.gridy = 11;
		frame.getContentPane().add(panel_28, gbc_panel_28);
		
		
		
		JPanel panel_29 = new JPanel();
		panel_29.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_29 = new GridBagConstraints();
		gbc_panel_29.insets = new Insets(0, 0, 5, 5);
		gbc_panel_29.fill = GridBagConstraints.BOTH;
		gbc_panel_29.gridx = 5;
		gbc_panel_29.gridy = 12;
		frame.getContentPane().add(panel_29, gbc_panel_29);
		
		btnAddKid = new JButton("Add Kid");
		GridBagConstraints gbc_btnAddKid = new GridBagConstraints();
		gbc_btnAddKid.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddKid.gridx = 21;
		gbc_btnAddKid.gridy = 12;
		frame.getContentPane().add(btnAddKid, gbc_btnAddKid);
		btnAddKid.addActionListener(this);
		/*
		btnAddKid.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CreateKid();
				frame.getContentPane().validate();
				frame.getContentPane().repaint();
			}
		});
		*/
		JPanel panel_30 = new JPanel();
		panel_30.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_30 = new GridBagConstraints();
		gbc_panel_30.insets = new Insets(0, 0, 5, 5);
		gbc_panel_30.fill = GridBagConstraints.BOTH;
		gbc_panel_30.gridx = 5;
		gbc_panel_30.gridy = 13;
		frame.getContentPane().add(panel_30, gbc_panel_30);
		
		btnAddOfficer = new JButton("Add Officer");
		GridBagConstraints gbc_btnAddOfficer = new GridBagConstraints();
		gbc_btnAddOfficer.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddOfficer.gridx = 21;
		gbc_btnAddOfficer.gridy = 13;
		frame.getContentPane().add(btnAddOfficer, gbc_btnAddOfficer);
		btnAddOfficer.addActionListener(this);
		/*
		btnAddOfficer.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0) {
						CreatePolice();
						frame.getContentPane().validate();
						frame.getContentPane().repaint();
					}
				});
		*/
		JPanel panel_31 = new JPanel();
		panel_31.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_31 = new GridBagConstraints();
		gbc_panel_31.insets = new Insets(0, 0, 0, 5);
		gbc_panel_31.fill = GridBagConstraints.BOTH;
		gbc_panel_31.gridx = 5;
		gbc_panel_31.gridy = 14;
		frame.getContentPane().add(panel_31, gbc_panel_31);
		
	}

}
