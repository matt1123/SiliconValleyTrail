package view;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.*;

import model.Adventure;
import model.City;
import model.InTransitException;
import model.TravelObserver;

@SuppressWarnings("serial")
public class MapView extends JPanel implements TravelObserver {

    private Icon map;
    private Icon dot;
    private JLayeredPane lp;
    private Adventure adventure;
    private JLabel d;
    private int x;
    private int y;
    
	  public MapView(Adventure a) {
		  adventure = a;
		  a.addTravelObserver(this);
		  
		  
		  lp = new JLayeredPane();
		  lp.setPreferredSize(new Dimension(900, 600));
		  
		  JLabel erMess = new JLabel("       Improper import. "
		  		+ "move the contents of 'images' to a new folder titled 'images' under "
		  		+ "the JavaProject (NOT IN SRC FOLDER!)");
		  erMess.setVisible(true);
		  erMess.setBounds(0,0,900,600);
		  lp.add(erMess, new Integer(0));
		  
		  String mappath = "././images/map-of-united-states-2.gif";
		  map = new ImageIcon(mappath);
		  JLabel m = new JLabel(map);
		  m.setVisible(true);
		  m.setBounds(0,0,900,600);
		  lp.add(m, new Integer(1));
		  
		  x = 0;
		  y = 0;
		  
		  relocation();
		  
		  String dotPath = "././images/redDot.gif";
		  dot = new ImageIcon(dotPath);
		  d = new JLabel(dot);
		  d.setVisible(true);
		  d.setBounds(0,0,x,y);
		  lp.add(d, new Integer(2));

		  lp.setSize(1000, 1000);
		  lp.setVisible(true);

		  add(lp);

	  }

	  @Override
	  public void travelUpdate(Adventure adventure, int distance_to_destination,
			  City destination) {

		  refresh();
	  }
	  
	  private void refresh() {
		  relocation();
		  Container parent = d.getParent();
		  parent.remove(d);
		  String dotPath = "/Users/matthewkrause/Desktop/redDot.gif";
		  dot = new ImageIcon(dotPath);
		  d = new JLabel(dot);
		  d.setVisible(true);
		  d.setBounds(0,0,x,y);
		  lp.add(d, new Integer(2));

		  lp.setSize(1000, 1000);
		  lp.setVisible(true);
		  repaint();
	  }
	  
	  private void relocation(){
		  try{
			  switch (adventure.getCurrentCity().getName()){
			  case "Chapel Hill":
				  x = 1475;
				  y = 550;
				  break;
			  case "Charlotte":
				  x = 1410;
				  y = 580;
				  break;
			  case "Atlanta":
				  x = 1300;
				  y = 650;
				  break;
			  case "Washington D.C.":
				  x = 1500;
				  y = 400;
				  break;
			  case "New York":
				  x = 1590;
				  y = 325;
				  break;
			  case "Chicago":
				  x = 1160;
				  y = 340;
				  break;
			  case "Austin":
				  x = 860;
				  y = 800;
				  break;
			  case "Minneapolis":
				  x = 1000;
				  y = 240;
				  break;
			  case "Seattle":
				  x = 180;
				  y = 50;
				  break;
			  case "San Francisco":
				  x = 90;
				  y = 420;
				  break;
			  case "Palo Alto":
				  x = 110;
				  y = 435;
				  break;
			  }
		  }catch(InTransitException e){


		  }


	  } 


}
