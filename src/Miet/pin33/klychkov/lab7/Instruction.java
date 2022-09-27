package Miet.pin33.klychkov.lab7;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

public class Instruction extends JFrame{	
	
	private  JTextPane textEditor = null;
    // —тили редактора
    private  Style     heading    = null; // стиль заголовка
    private  Style     normal     = null; // стиль текста

    private  final  String      STYLE_heading = "heading",
                                STYLE_normal  = "normal" ,
                                FONT_style    = "Times New Roman";
    private final String[][] RULES = {
    		
    };
    
    private final String[][] KEYBOARDS = {
    		
    };
    
    GamePanel gp;

	Instruction(GamePanel gp){
		this.gp = gp;
		choiceInstruction();
	}
	public void choiceInstruction() {
		JFrame chF = new JFrame();
		JPanel chP = new JPanel();
		chP.setLayout(null);
				
		Button rulesB = new Button("Rules");
		rulesB.setBounds(50, 50, 200, 50);
		rulesB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {					
				gameInstruction();
				chF.dispose();							
			}
		});
		Button keyboardsB = new Button("Keyboards");
		keyboardsB.setBounds(50, 100, 200, 50);
		Button back = new Button("Back");
		back.setBounds(50, 150, 200, 50);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {					
				chF.dispose();
				gp.menu.menuFrame();						
			}
		});		
		
		chP.add(rulesB);
		chP.add(keyboardsB);
		chP.add(back);
		
		
		chF.add(chP);
		chF.setSize(300, 365);
		chF.setTitle("Choice instruction");
		chF.setResizable(false);
		chF.setLocationRelativeTo(null);		
		chF.setVisible(true);
	}
	public void gameInstruction() {
		
		JFrame giF = new JFrame();
		JPanel giP = new JPanel();
		giP.setLayout(new BorderLayout());
		JTextArea textp = new JTextArea();
		textp.setEditable(false);
		textp.setLineWrap(true);
	    textp.setWrapStyleWord(true);

       
		try (FileInputStream fis = new FileInputStream("D:\\Java projects\\Lab_7\\Rules.txt");
	             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
	             BufferedReader reader = new BufferedReader(isr)
	        ) {

	            String str;
	            while ((str = reader.readLine()) != null) {	            	
	            	textp.append(str);
	            	textp.append("\n");
	                System.out.println(str);
	            }

	    } catch (IOException e) {
	            e.printStackTrace();
	    }
		textp.setCaretPosition(0);
		final JScrollPane scrollPane = new JScrollPane(textp);
		scrollPane
          .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane
         .setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
		giP.add(scrollPane, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		Button back = new Button("Back");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				
				giF.dispose();
				choiceInstruction();						
			}
		});
		
		Button menu = new Button("Menu");
		menu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				
				giF.dispose();
				gp.menu.menuFrame();						
			}
		});
		
		buttonPanel.add(back);
		buttonPanel.add(menu);
		
		giP.add(buttonPanel, BorderLayout.SOUTH);
		
		giF.setContentPane(giP);
		giF.setSize(600, 300);
		giF.setTitle("Choice instruction");
		giF.setResizable(false);
		giF.setLocationRelativeTo(null);		
		giF.setVisible(true);
	}
	public void keyboardShortcutsInstruction() {
		
	}
	
}
