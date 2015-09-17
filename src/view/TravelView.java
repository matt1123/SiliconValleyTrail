package view;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import model.*;

public class TravelView extends JPanel implements TravelObserver {

	private Adventure adventure;
	private JLabel locLabel;
	
	private JPanel travPanel;
	private JComboBox<String> cityChoices;
	
	private int toTravel;
	private City destination;
	
	
	public TravelView(Adventure a){
		a.addTravelObserver(this);
		adventure = a;

		setLayout(new BorderLayout());
		
		JPanel norPan = new JPanel();
		norPan.setLayout(new BorderLayout());
		
		String message = null;
		try{
			message = "in " + adventure.getCurrentCity().getName();
		}catch (InTransitException e){
			message = "travelling" ;
		}
		
		locLabel = new JLabel("You are currently " + message + 
				" on day " + a.getDay());
		locLabel.setHorizontalAlignment(getWidth());
		
		

		norPan.add(locLabel, BorderLayout.CENTER);
		add(norPan);
		
		
		travPanel = new JPanel();
		setLayout(new GridLayout(2,1));
		JLabel travTo = new JLabel("Travel to ");
	
		travPanel.add(travTo);
		
		
		int numCities = adventure.getCities().length;
		String[] cityNames = new String[numCities];
		for(int i=0; i<numCities; i++){
			cityNames[i] = adventure.getCities()[i].getName();
		}
		cityChoices = new JComboBox<String>(cityNames);
		
		travPanel.add(cityChoices);
		
		
		JButton travNow = new JButton("Travel");
		travNow.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e) {
				City next = null;
				String nextChoice = (String) cityChoices.getSelectedItem();
				City[] cities = adventure.getCities();
				for(int i=0; i<cities.length; i++){
					if(cities[i].getName() == nextChoice){
						next = cities[i];
					}
				}
				a.travel(next);
			}
		});
		
		travPanel.add(travNow);
		
		add(travPanel, BorderLayout.SOUTH);
		
	}


	@Override
	public void travelUpdate(Adventure adventure, int distance_to_destination,
			City destination) {
		String message = null;
		try{
			message = "in " + adventure.getCurrentCity().getName();
		}catch (InTransitException e){
			message = "travelling" ;
		}
		
		locLabel.setText("You are currently " + message + 
				" on day " + adventure.getDay());
		
	}
	
}
