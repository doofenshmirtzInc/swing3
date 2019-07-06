package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel{
	
	private JLabel nameLabel;
	private JLabel occupationLabel;
	private JTextField nameField;
	private JTextField occupationField;
	private JButton submitButton;
	private FormListener formListener;
	private JList ageList;
	private JComboBox empCombo;
	private JCheckBox citizenCheck;
	private JTextField taxField;
	private JLabel taxLabel;
	
	private JRadioButton maleRadio;
	private JRadioButton femaleRadio;
	private JRadioButton otherRadio;
	private ButtonGroup genderGroup;
	 
	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		
		nameLabel = new JLabel("Name: ");
		occupationLabel = new JLabel("Occupation: ");
		nameField = new JTextField(10); //int represents number of characters long the field will be
		occupationField = new JTextField(10);
		ageList = new JList();
		empCombo = new JComboBox();
		citizenCheck = new JCheckBox();
		taxField = new JTextField(10);
		taxLabel = new JLabel("Tax ID: ");
		maleRadio = new JRadioButton("Male");
		femaleRadio = new JRadioButton("Female");
		otherRadio = new JRadioButton("Other");
		genderGroup = new ButtonGroup();
		submitButton = new JButton("Submit");
		
		//set mnemonic for Submit button
		
		submitButton.setMnemonic(KeyEvent.VK_S);
		
		//set up rest of mnemonics
		nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
		nameLabel.setLabelFor(nameField);
		
		//sets string that will be received when one is pressed/selected
		maleRadio.setActionCommand("Male");
		femaleRadio.setActionCommand("Female");
		otherRadio.setActionCommand("Other");

		
		maleRadio.setSelected(true);
		
		//set up Button group
		
		genderGroup.add(maleRadio);
		genderGroup.add(femaleRadio);
		genderGroup.add(otherRadio);
		
		//set up tax ID
		
		taxField.setEnabled(false);
		taxLabel.setEnabled(false);
		
		citizenCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				boolean isTicked = citizenCheck.isSelected();
				taxLabel.setEnabled(isTicked);
				taxField.setEnabled(isTicked);
			}
		});
		
		//set up list box
		
		DefaultListModel ageModel = new DefaultListModel();
		ageModel.addElement(new AgeCategory(0, "Under 18"));
		ageModel.addElement(new AgeCategory(1, "18 to 65"));
		ageModel.addElement(new AgeCategory(2, "65 or older"));
		ageList.setModel(ageModel);
		
		ageList.setPreferredSize(new Dimension(110, 70));
		ageList.setBorder(BorderFactory.createEtchedBorder());
		ageList.setSelectedIndex(1);  //indices start at 0
		
		
		//set up combo box
		
		DefaultComboBoxModel empModel = new DefaultComboBoxModel();
		empModel.addElement("Employed");
		empModel.addElement("Self-Employed");
		empModel.addElement("Unemployed");
		empCombo.setModel(empModel);
		empCombo.setSelectedIndex(0);
		empCombo.setEditable(true);
		
		
		
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = nameField.getText();
				String occupation = occupationField.getText();
				AgeCategory ageCat = (AgeCategory)ageList.getSelectedValue();
				String empCat = (String)empCombo.getSelectedItem();
				String taxId = taxField.getText();
				boolean usCitizen = citizenCheck.isSelected();
				String gender = genderGroup.getSelection().getActionCommand();
				
				System.out.println(empCat);
				
				FormEvent ev = new FormEvent(this, name, occupation, ageCat.getId(), empCat, taxId, usCitizen, gender); //this refers to FormPanel
				
				if(formListener != null) {
					formListener.formEventOccurred(ev);
				}
			}
			
		});
		
		Border innerBorder = BorderFactory.createTitledBorder("Add Person");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		layoutComponents();
	}
	
	
	public void layoutComponents() {
		
		setLayout(new GridBagLayout());
		//GridBagConstraints is used in conjunction with GridBagLayout
		GridBagConstraints gc = new GridBagConstraints();
		
		//initial set-up of the GridBag layout
		//necessary when going to be used otherwise will cause problems
		
		
		//******************First Row**********************
		
		gc.gridy = 0;  //position in y
		
		gc.weightx = 1;  //weight of x coordinate relative to other x coordinates
		gc.weighty = .1;  //weight of y coordinate relative to other y coordinates
		
		gc.gridx = 0;  //position in x
		gc.insets = new Insets(0, 0, 0, 5); //allows for insertion of pixels around object
											//this line inserts 5 pixels after nameLabel
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;  //where the added objects are anchored in reference to the
													//they are on
		add(nameLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0, 0, 0, 0);
		add(nameField, gc);
		
		//*******************Second Row****************************
		
		gc.gridy++;
		
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		add(occupationLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		add(occupationField, gc);
		
		//**********************Third Row*****************************
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = .1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Age:"), gc);
				
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(ageList, gc);
		
		//**********************Fourth Row*****************************
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = .1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Employment:"), gc);
				
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(empCombo, gc);
		
		//**********************Fourth Row*****************************
		
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = .1;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("US Citizen:"), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(citizenCheck, gc);
		
		//**********************Fourth Row*****************************
		
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = .1;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(taxLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(taxField, gc);
		
		//**********************Fourth Row*****************************
		
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = .01;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Gender: "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(maleRadio, gc);
		
		//**********************Fourth Row*****************************
		
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = .01;

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(femaleRadio, gc);
		
		//**********************Fourth Row*****************************
		
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = .1;

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(otherRadio, gc);
		
		//***********************Fifth Row*****************************
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 2;
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(submitButton, gc);
	}
	
	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}
}


class AgeCategory{
	
	private int id;
	private String text;
	
	public AgeCategory(int id, String text) {
		
		this.id = id;
		this.text = text;
	}
	
	public String toString() {
		
		return this.text;
	}
	
	public int getId() {
		return this.id;
	}
}
