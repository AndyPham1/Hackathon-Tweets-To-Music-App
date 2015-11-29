package com.tunegenerator.hashtune;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TuneGeneratorTest {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		String text;
		int[] infoArray = new int[3];
		boolean init = false;
		
		Dimension dimension = new Dimension(100, 100);
		JFrame frame = new JFrame("HashTune");
		frame.setLayout(new GridBagLayout());
		JPanel panel = new JPanel();
		JLabel label1 = new JLabel("HashTune");
		JTextField text1 = new JTextField(20);
		JButton button1 = new JButton("GENERATE");
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		button1.setBorderPainted(true);
		button1.setFocusPainted(false);
		button1.setContentAreaFilled(false);
		button1.setPreferredSize(dimension);
		frame.getContentPane().add(panel);
		frame.setSize(1000, 650);
		panel.setSize(1000, 650);
		panel.add(text1);
		label1.setFont(new Font("Caviar Dreams", 1, 80));
		panel.add(label1);
		panel.add(button1);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		centreWindow(frame);
		
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(e.getSource() == button1 && !text1.getText().equals("")){
					/*text = text1.getText();
					text1.setText("");
					init = true;*/
					final String temp = text1.getText();
					text1.setText("");
					runAll(temp, parameterize(temp));
				}
			}
		});
		
		/*while(true){
			if(button1.getModel().isPressed() && !text1.getText().equals("")){
				System.out.println(true);
				text = text1.getText();
				text1.setText(null);
				init = true;
				button1.disable();
				infoArray = parameterize(text);
				
				EditXML.makeChanges(infoArray[0], infoArray[1], infoArray[2]);
				SoundHelixPlay.play(text);
				button1.enable();
			}
		}*/
		
	}

	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
	
	public static int[] parameterize(String text) {
        int length = text.length();

        int sumValue = 0;
        for(int i = 0; i < length; i++){
            sumValue += text.charAt(i);
        }

        int positionValue = 0;
        for(int i = 0; i < length; i++){
            positionValue += i * text.charAt((text.length()-1) - i);
        }
        positionValue %= 80;

        return new int[]{length, sumValue, positionValue};
    }
	
	public static void runAll(String var, int[] infoArray){
		EditXML.makeChanges(infoArray[0], infoArray[1], infoArray[2]);
		SoundHelixPlay.play(var);
	}
}
