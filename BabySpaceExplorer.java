
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BabySpaceExplorer extends JFrame{
	JMenu menu;
	JPanel topPanel;
	JPanel leftPanel;
	JPanel rightPanel;
	GridLayout rightLayout;
	JPanel botPanel;
	middlePanel midPanel;

	JRadioButtonMenuItem rbMenuRandom;
	JRadioButtonMenuItem rbMenuRegular;
	JSlider speedSlider;
	JLabel coordsLabel;
	JLabel sliderLabel;
	ArrayList<JTextField> xCoordsDisplay;
	ArrayList<JTextField> yCoordsDisplay;
	JTextField displaySpeed;
	JLabel displaySpeedLabel;
	JButton editableButton;
	JButton helpButton;
	
	
	boolean editable = false;
	int N = 3;
	int[] xCoords;
	int[] yCoords;
	int R = 190;
	
	
	
	public BabySpaceExplorer() {
		super();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(1200,800);
		this.setTitle("BabySpaceExplorer");
		this.getContentPane().setBackground(Color.BLACK);

		
		
		final JMenuBar menuBar = new JMenuBar();
		menu = new JMenu("GAME");
		//menu.setMnemonic(KeyEvent.VK_A);
		
		ButtonGroup group = new ButtonGroup();
		rbMenuRegular = new JRadioButtonMenuItem("Restart (new game)", true);
		rbMenuRegular.setSelected(true);
		//rbMenuRegular.setMnemonic(KeyEvent.VK_O);
		group.add(rbMenuRegular);
		menu.add(rbMenuRegular);
		
		rbMenuRandom = new JRadioButtonMenuItem("Load from file");
		rbMenuRandom.setSelected(true);
		//rbMenuRandom.setMnemonic(KeyEvent.VK_R);
		group.add(rbMenuRandom);
		menu.add(rbMenuRandom);

		menuBar.add(menu);
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

		editableButton = new JButton("Sandbox Mode");
		
		editableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
				if(editable){
					editable = false;
            	}
				else{
					editable = true;
				}
				healthField.setEditable(editable);
				capacityField.setEditable(editable);
				loadField.setEditable(editable);
				velocityField.setEditable(editable);
			}});
		
		botPanel.add(editableButton);

		helpButton = new JButton("Help");
		botPanel.add(helpButton);

        botPanel.setLayout(new BoxLayout(botPanel, BoxLayout.X_AXIS));

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
			N = value;
			displaySpeed.setText(Integer.toString(value));
		}
	}
	
	public static void main(String[] args) {
		BabySpaceExplorer p = new BabySpaceExplorer();
		p.setVisible(true);
	}
}
