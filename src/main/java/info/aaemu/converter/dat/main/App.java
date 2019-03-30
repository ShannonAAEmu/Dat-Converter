package info.aaemu.converter.dat.main;

import java.io.IOException;

import javax.swing.SwingUtilities;

import info.aaemu.converter.dat.gui.Gui;

public class App {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new Gui();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
