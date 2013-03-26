package common;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import enums.DialogType;

public class DialogManager {

	public static void showDialog(String title, String message, JFrame owner,DialogType type){
		switch(type){
		case INFO: JOptionPane.showMessageDialog(owner,message,title, JOptionPane.INFORMATION_MESSAGE);
			break;
		case ERROR: JOptionPane.showMessageDialog(owner,message,title, JOptionPane.WARNING_MESSAGE);
			break;
		case WARNING: JOptionPane.showMessageDialog(owner,message,title, JOptionPane.ERROR_MESSAGE);
			break;
		default: JOptionPane.showMessageDialog(owner,message,title, JOptionPane.INFORMATION_MESSAGE);
			break;
		}
	}
}
