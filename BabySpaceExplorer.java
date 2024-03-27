
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BabySpaceExplorer extends JFrame{
	JMenu gameMenu;
	JMenu optionsMenu;
	JMenu customiseMenu;
	JPanel topPanel;
	JPanel leftPanel;
	JPanel rightPanel;
	GridLayout rightLayout;
	JPanel botPanel;
	middlePanel midPanel;

	JMenuItem loadGameFileButton;
	JMenuItem saveGameFileButton;
	JMenuItem newGameButton;
	JMenuItem soundButton;
	JCheckBox sandboxButton;
	JMenuItem helpButton;
	JSlider speedSlider;
	JLabel coordsLabel;
	JLabel sliderLabel;
	ArrayList<JTextField> xCoordsDisplay;
	ArrayList<JTextField> yCoordsDisplay;
	JTextField displaySpeed;
	JLabel displaySpeedLabel;
	//JButton editableButton;
	//JButton helpButton;
	
	
	boolean paramsEditable = false;
	int engineVelocity = 3;
	int[] xCoords;
	int[] yCoords;
	
	
	
	public BabySpaceExplorer() {
		super();
		//loading icon
		String OSNAME = System.getProperty("os.name");
		if (OSNAME.toLowerCase().contains("windows")){
			URL iconURL = getClass().getResource("BSE_icon.ico");
			ImageIcon icon = new ImageIcon(iconURL);
			this.setIconImage(icon.getImage());

		}
		else {
			URL iconURL = getClass().getResource("BSE_icon.png");
			ImageIcon icon = new ImageIcon(iconURL);
			this.setIconImage(icon.getImage());
		}

		//setting up the main JFrame
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(1200,800);
		this.setTitle("BabySpaceExplorer");
		this.getContentPane().setBackground(Color.BLACK);

		
		//creating the menu bar
		final JMenuBar menuBar = new JMenuBar();

		//GAME menu
		gameMenu = new JMenu("GAME");
		
		ButtonGroup gameGroup = new ButtonGroup();

		newGameButton = new JMenuItem("Restart (new game)");
		//newGameButton.setSelected(true);
		gameGroup.add(newGameButton);
		gameMenu.add(newGameButton);
		
		loadGameFileButton = new JMenuItem("Load game (from .csv file)");
		//loadGameFileButton.setSelected(true);
		gameGroup.add(loadGameFileButton);
		gameMenu.add(loadGameFileButton);
		
		saveGameFileButton = new JMenuItem("Save game (to .csv file)");
		//loadGameFileButton.setSelected(true);
		gameGroup.add(saveGameFileButton);
		gameMenu.add(saveGameFileButton);

		menuBar.add(gameMenu);

		
		optionsMenu = new JMenu("OPTIONS");
		
		ButtonGroup optionsGroup = new ButtonGroup();

		soundButton = new JMenuItem("Sound...");
		//newGameButton.setSelected(true);
		optionsGroup.add(soundButton);
		optionsMenu.add(soundButton);

		
		sandboxButton = new JCheckBox("Sandbox Mode");
		optionsGroup.add(sandboxButton);
		optionsMenu.add(sandboxButton);

		
		helpButton = new JMenuItem("Help");
		optionsGroup.add(helpButton);
		optionsMenu.add(helpButton);
		
		

		menuBar.add(optionsMenu);

		customiseMenu = new JMenu("CUSTOMISE");

		this.setJMenuBar(menuBar);
		


		
		midPanel = new middlePanel();
		midPanel.setPreferredSize(new Dimension (600, 400));
		midPanel.setMaximumSize(new Dimension (600, 400));
		
		
		
		topPanel = new JPanel();
		
		
		
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		

		leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension (200, 400));
		leftPanel.setMaximumSize(new Dimension (200, 400));

		
		sliderLabel = new JLabel("Speed");
		speedSlider = new JSlider(0, 12, 1);
		speedSlider.setMajorTickSpacing(1);
        speedSlider.setPaintLabels(true);
        speedSlider.setPaintTicks(true);
        speedSlider.setSnapToTicks(true);
        speedSlider.addChangeListener(new SliderChangeListener());
		leftPanel.add(sliderLabel);
        leftPanel.add(speedSlider);

		displaySpeedLabel = new JLabel("Rocket velocity:");
		displaySpeed = new JTextField("1");
		displaySpeed.setEditable(false);
		
		leftPanel.add(displaySpeedLabel);
		leftPanel.add(displaySpeed);

		/*/
		GroupLayout leftLayout = new GroupLayout(leftPanel);
		leftPanel.setLayout(leftLayout);
		leftLayout.setAutoCreateGaps(true);leftLayout.setHorizontalGroup(
				leftLayout.createSequentialGroup()
				      
					.addGroup(leftLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(displaySpeedLabel)
							.addComponent(displaySpeed))
				);
				leftLayout.setVerticalGroup(
				   leftLayout.createSequentialGroup()
				      .addGroup(leftLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				           .addComponent(displaySpeedLabel)
				           .addComponent(displaySpeed))
				);

				leftPanel.add(displaySpeedLabel);
				leftPanel.add(displaySpeed);
				
				
				*/
		
		rightLayout = new GridLayout();
				
		rightLayout.setColumns(3);
		
		rightPanel = new JPanel(rightLayout);
		rightPanel.setPreferredSize(new Dimension (200, 400));
		rightPanel.setMaximumSize(new Dimension (200, 400));


		rightPanel.add(new JLabel("  "));
		rightPanel.add(new JLabel("value"));
		rightPanel.add(new JLabel("unit"));

		rightPanel.add(new JLabel("velocity:"));
		JTextField velocityField = new JTextField("300000", 3);
		velocityField.setEditable(false);
		rightPanel.add(velocityField);
		rightPanel.add(new JTextField("m/s", 3));

		rightPanel.add(new JLabel("fuel load:"));
		JTextField loadField = new JTextField("300000", 3);
		loadField.setEditable(false);
		rightPanel.add(loadField);
		rightPanel.add(new JTextField("l", 3));
		
		rightPanel.add(new JLabel("fuel cap.:"));
		JTextField capacityField = new JTextField("300000", 3);
		capacityField.setEditable(false);
		rightPanel.add(capacityField);
		rightPanel.add(new JTextField("l", 3));
		
		rightPanel.add(new JLabel("rocket health:"));
		JTextField healthField = new JTextField("300000", 3);
		healthField.setEditable(false);
		rightPanel.add(healthField);
		rightPanel.add(new JTextField("l", 3));
		

		rightLayout.setRows(5);
		
		
		botPanel = new JPanel();


        botPanel.setLayout(new BoxLayout(botPanel, BoxLayout.X_AXIS));


		//"GLOBAL" LISTENERS
		
		sandboxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
				if(paramsEditable){
					paramsEditable = false;
            	}
				else{
					paramsEditable = true;
				}
				healthField.setEditable(paramsEditable);
				capacityField.setEditable(paramsEditable);
				loadField.setEditable(paramsEditable);
				velocityField.setEditable(paramsEditable);
				sandboxButton.setSelected(paramsEditable);
			}});


		this.add(topPanel, BorderLayout.NORTH);
		this.add(midPanel, BorderLayout.CENTER);
		this.add(rightPanel, BorderLayout.EAST);
		this.add(leftPanel, BorderLayout.WEST);
		this.add(botPanel, BorderLayout.SOUTH);
		

	}

	public class SliderChangeListener implements ChangeListener{
	@Override
		public void stateChanged(ChangeEvent arg0) {
			int value = speedSlider.getValue();
			engineVelocity = value;
			displaySpeed.setText(Integer.toString(value));
		}
	}
	
	public static void main(String[] args) {
		BabySpaceExplorer p = new BabySpaceExplorer();
		p.setVisible(true);
	}
}
