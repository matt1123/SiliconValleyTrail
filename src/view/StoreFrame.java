package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;

import model.*;
import supplies.*;

@SuppressWarnings("serial")
public class StoreFrame extends JFrame {

	private JPanel main_panel;
	private JComboBox<String> itemChoices;
	private JSpinner amount;
	private String[] forSale;
	private City thisCity;
	private Icon img;
	
	StoreFrame(Adventure a){
		try {
			thisCity = a.getCurrentCity();
		} catch (InTransitException e) {
		}
		
		this.setTitle("Welcome to " + thisCity.getName() +"'s Store");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		main_panel = new JPanel();
		//int amtItems = thisCity.getStore().getItemNames().length;

		forSale = thisCity.getStore().getItemNames();

		itemChoices = new JComboBox<String>(forSale);


		JLabel buyLab = new JLabel("Buy");
		amount = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		

		JButton purchase = new JButton("Purchase");

		purchase.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e) {
				double ob = a.getSquad().getBalance();

				int toBuy = (Integer) amount.getValue();
				
				String itemToBuy = itemChoices.getSelectedItem().toString();
				
				double cost = 0;
				try {
					cost = thisCity.getStore().getPrice(itemToBuy);
				} catch (ItemNotForSaleException e1) {
				}
				

				a.getSquad().setBalance(ob - (toBuy*cost) );
				
				Supplies item = null;
				switch(itemToBuy){
				case "bison jerky":
					item = new BisonJerky(toBuy);
					break;
				case "burrito":
					item = new Burrito(toBuy);
					break;
				case "button-up":
					item = new ButtonUp(toBuy);
					break;
				case "coffee":
					item = new Coffee(toBuy);
					break;
				case "fleece":
					item = new Fleece(toBuy);
					break;
				case "ramen noodles":
					item = new Ramen(toBuy);
					break;
				case "Red Bison":
					item = new RedBison(toBuy);
					break;
				case "sushi":
					item = new Sushi(toBuy);
					break;
				case "t-shirt":
					item = new TShirt(toBuy);
					break;
				}
				
				a.getSquad().getKnapsack().add(item);
				
				
			}
		});
		
		
				String mappath = "././images/storeprices.jpg";
		img = new ImageIcon(mappath);
		JLabel m = new JLabel(img);
		m.setVisible(true);
		m.setBounds(0,0,900,600);

		main_panel.add(m, BorderLayout.NORTH);
		
		
		main_panel.add(buyLab, BorderLayout.SOUTH);
		main_panel.add(amount,BorderLayout.SOUTH);
		main_panel.add(itemChoices, BorderLayout.SOUTH);
		main_panel.add(purchase, BorderLayout.SOUTH);



		this.setContentPane(main_panel);
		this.pack();
		this.setVisible(true);

	}
	
}
