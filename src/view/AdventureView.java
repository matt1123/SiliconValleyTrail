package view;

import java.awt.*;

import javax.swing.*;

import person.*;
import person.Person.Status;
import model.*;

public class AdventureView extends JPanel {

	private Adventure adventure;
	
	public AdventureView(Adventure adventure) {
		this.adventure = adventure;
		
				setLayout(new BorderLayout());

		//---------------West------------------------
				
		//Person Stuff on the north west
		JPanel westCont = new JPanel();
		westCont.setLayout(new BorderLayout());
		
		JPanel personCont = new JPanel();
		personCont.setLayout(new GridLayout(5,1));
		
		//Travel, store, feed in south
		JPanel southCont = new JPanel();
		southCont.setLayout(new GridLayout(3,1));

		
		//Get the squad and the players
		Person[] people = new Person[adventure.getSquad().getNumPlayers()];
		Squad s = adventure.getSquad();
		for(int i=0; i<5; i++){
			people[i] = s.getPlayer(i);
		}

		
		//Make each personView into a single cell
		PersonView[] pv = new PersonView[5];
		for(int i=0; i<5; i++){
			pv[i] = new PersonView(people[i]);
		}
		
		
		//put each person cell into the GUI
		add(westCont, BorderLayout.WEST);
		westCont.add(personCont, BorderLayout.NORTH);
		
		for(int i=0; i<5; i++){
			personCont.add(pv[i]);
		}
		
		
		
		
		//---------------South---------------------------
		
		add(southCont, BorderLayout.SOUTH);
		southCont.add(new TravelView(adventure));
		southCont.add(new FeedView(adventure));
		
		southCont.add(new StoreView(adventure));
		
		//---------------Center Map-----------------------
		
		add(new MapView(adventure), BorderLayout.CENTER);
		
		//---------------Knapsack East---------------------
		
		KnapsackView kv = new KnapsackView(adventure);
		kv.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		kv.setPreferredSize(new Dimension(220, 600));
		add(kv, BorderLayout.EAST);
				

		
	}
}
