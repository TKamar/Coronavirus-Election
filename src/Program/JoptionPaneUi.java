package Program;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class JoptionPaneUi implements Printable {

	@Override
	public void print(String str) {
		JOptionPane.showMessageDialog(null, str);
	}
	@Override
	public String getString(Scanner s) {
		return s.next();
	}
	
	@Override
	public int getInt(Scanner s) {
		return s.nextInt();
	}
}
