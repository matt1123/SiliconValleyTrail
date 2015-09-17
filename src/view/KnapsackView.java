package view;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import model.*;
import supplies.*;

public class KnapsackView extends JPanel implements Observer {

	private Adventure adventure;
	private Supplies[] supplies;
	private int numSupplies;
	private String[] wordsArray;
	private JList<String> list;
	private JPanel tabPan;
	private DefaultListModel<String> listModel;
	

	public KnapsackView(Adventure a){
		adventure = a;
		a.getSquad().getKnapsack().addObserver(this);
	
				
		supplies = a.getSquad().getKnapsack().getSupplies();
		numSupplies = supplies.length;
		
		setLayout(new BorderLayout());
		
		JLabel knapPanel = new JLabel("Knapsack:");
		knapPanel.setHorizontalAlignment(getWidth());
		add(knapPanel, BorderLayout.NORTH);
		
		
		String[] nameArr = new String[numSupplies];
		String[] amtArr = new String[numSupplies];
		
		for(int i=0; i<numSupplies; i++){
			String name = supplies[i].getName();
			int amt = supplies[i].getAmount();
			String amount = Integer.toString(amt);
			if(supplies[i] instanceof Food){
				Food f =(Food) supplies[i];
				int exp = f.getDaysTillExpiration();
				String e = Integer.toString(exp);
				nameArr[i] = amount + " of " + name + " expires in " 
						+ e + " days.";
			}else{
				nameArr[i] = amount + " of " + name;
			}

		}
		
		listModel=new DefaultListModel<String>();
		for(int i=0; i<nameArr.length; i++){
			listModel.addElement(nameArr[i]);
		}
		
		list = new JList<String>(listModel);
	
		tabPan = new JPanel();
		tabPan.add(list);
		tabPan.validate();
		add(tabPan);




		
		
	}

	@Override
	public void update(Observable o, Object arg) {
		
		supplies = adventure.getSquad().getKnapsack().getSupplies();
		numSupplies = supplies.length;
		
		String[] nameArr = new String[numSupplies];
		String[] amtArr = new String[numSupplies];
		
		for(int i=0; i<numSupplies; i++){
			String name = supplies[i].getName();
			int amt = supplies[i].getAmount();
			String amount = Integer.toString(amt);
			if(supplies[i] instanceof Food){
				Food f =(Food) supplies[i];
				int exp = f.getDaysTillExpiration();
				String e = Integer.toString(exp);
				nameArr[i] = amount + " of " + name + " expires in " 
						+ e + " days.";
			}else{
				nameArr[i] = amount + " of " + name;
			}


		}


		listModel.removeAllElements();
		
		for(int i=0; i<nameArr.length; i++){
			listModel.addElement(nameArr[i]);
		}

	}
	
}
