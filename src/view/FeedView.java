package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.List;

import person.*;
import person.Person.Status;
import supplies.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;

import javax.swing.*;

import model.*;

import java.util.ArrayList;

public class FeedView extends JPanel implements Observer {

	private Adventure adventure;
	
	private JButton feed;
	private JComboBox<String> foodChoices;
	private JLabel to;
	private JComboBox<String> peopleChoices;

	public FeedView(Adventure a){
		
		a.getSquad().getKnapsack().addObserver(this);

		setLayout(new FlowLayout());

		adventure = a;
		
		feed = new JButton("Feed");
		
		Food[] edibles = adventure.getSquad().getKnapsack().getEdibleSupplies();
		String[] choices = new String[edibles.length];

		for(int i=0; i<edibles.length; i++){
			choices[i] = edibles[i].getName();
		}
		
		JPanel toPannel = new JPanel();
		to = new JLabel("to (select a player)");
		to.setHorizontalAlignment(getWidth());
		
		
		foodChoices = new JComboBox<String>(choices);
		
		
		toPannel.add(to, BorderLayout.CENTER);
		
		feed.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String selFood = (String)foodChoices.getSelectedItem();
				String selPers = (String)peopleChoices.getSelectedItem();

				Person p = null;
				Food f = null;
				for(int i=0; i<a.getSquad().getNumPlayers(); i++){
					if(a.getSquad().getPlayer(i).getName() == selPers){
						p = a.getSquad().getPlayer(i);
					}
				}
				for(int i=0; i<a.getSquad().getKnapsack().getEdibleSupplies().length; i++){
					if(a.getSquad().getKnapsack().getEdibleSupplies()[i].getName() == selFood){
						f = a.getSquad().getKnapsack().getEdibleSupplies()[i];
					}
				}
				try {
					p.eat(f);
//					for (FeedViewObserver o : tvo) {
//						o.feedUpdate(this, f);
//					}
					
				} catch (NoFoodException e1) {
					a.getSquad().getKnapsack().remove(f);
				}
			}
		});
		
		add(feed);
		add(foodChoices);
		add(to);
		
		
		String[] people = new String[adventure.getSquad().getNumPlayers()];
		for(int i=0; i<people.length; i++){
			people[i] = adventure.getSquad().getPlayer(i).getName();
		}
		
		peopleChoices = new JComboBox<String>(people);
		add(peopleChoices);
		

	}

	@Override
	public void update(Observable o, Object arg) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				foodChoices.removeAllItems();
	
				Food[] edibles = adventure.getSquad().getKnapsack().getEdibleSupplies();
				
				for(int i=0; i<edibles.length; i++){
					
					foodChoices.addItem(edibles[i].getName());
				}
				
				foodChoices.setVisible(true);
				foodChoices.repaint();
				
			}
			
		});
		
	}
	
	
}
