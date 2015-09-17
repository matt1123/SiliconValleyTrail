package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import model.*;

public class StoreView extends JPanel implements Observer {

	private double balance;
	private JLabel balLab;
	private JButton storeBut;
	private Adventure a;

	public StoreView(Adventure a){
		this.a = a;
		
		a.getSquad().getKnapsack().addObserver(this);
		
		setLayout(new FlowLayout());
		
		balance = a.getSquad().getBalance();
		balLab = new JLabel("Current balance: $" + balance + "0");
		
		storeBut = new JButton("Enter this City's Store");
		
		
		
		storeBut.addActionListener(new java.awt.event.ActionListener(){
			 public void actionPerformed(ActionEvent e) {
			        new StoreFrame(a).setVisible(true);
				 }
			    
		});
		
		add(storeBut);
		add(balLab);
		
	}

	@Override
	public void update(Observable o, Object arg) {


		balance = a.getSquad().getBalance();
		balLab.setText("Current balance: $" + balance + "0");
		repaint();
		setVisible(true);

	}

}
