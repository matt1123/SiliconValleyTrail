package view;

import java.awt.*;
import java.util.*;

import javax.swing.*;

import person.*;
import person.Person.Status;


public class PersonView extends JPanel implements Observer {

	private Person person;
	private JLabel name;
	private JLabel hp;
	private JLabel skill;
	private JLabel status;
	

	public PersonView(Person p){
		person = p;
		p.addObserver(this);
		
		setLayout(new GridLayout(4,1));
		person = p;
		name = new JLabel(p.getName());
		hp = new JLabel("HP: " + p.getCurrentHP() + "/" + p.getMaxHP());
		skill = new JLabel("Skill: " + p.getSkill());
		status = new JLabel("Status: " + p.getStatus().toString());

		add(name);
		add(hp);
		add(skill);
		add(status);
		
		Font font = name.getFont();
		Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
		name.setFont(boldFont);
		

		Color c = Color.GREEN;
		if(p.getStatus() == Status.DYSENTERY){
			c = Color.RED;
		}else if(p.getStatus() == Status.NOROVIRUS){
			c = Color.YELLOW;
		}else{
			c = Color.GREEN;
		}

		this.setBorder(BorderFactory.createLineBorder(c));

	}

		
		
		@Override
		public void update(Observable o, Object arg) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					hp.setText("HP: " + person.getCurrentHP() + "/" + person.getMaxHP());
					skill.setText("Skill: " + person.getSkill());
					status.setText("Status: " + person.getStatus().toString());
					
					Color c = Color.GREEN;
					if(person.getStatus() == Status.DYSENTERY){
						c = Color.RED;
					}else if(person.getStatus() == Status.NOROVIRUS){
						c = Color.YELLOW;
					}else{
						c = Color.GREEN;
					}
					
					setBorder(BorderFactory.createLineBorder(c));
				}
				
			});
		}

	
}

