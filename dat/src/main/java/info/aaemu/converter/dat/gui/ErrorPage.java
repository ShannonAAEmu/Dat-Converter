package info.aaemu.converter.dat.gui;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class ErrorPage extends JDialog {

	public ErrorPage() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/info/aaemu/converter/dat/imgs/aa.png")));
		setTitle("Error");
		getContentPane().setBackground(new Color(34, 36, 49));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 170, 112);
		getContentPane().setLayout(null);
		JLabel lblYouSelectedWrone = new JLabel("You selected wrong file");
		lblYouSelectedWrone.setForeground(new Color(153, 102, 255));
		lblYouSelectedWrone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblYouSelectedWrone.setBounds(10, 11, 139, 15);
		getContentPane().add(lblYouSelectedWrone);

		JButton btnNewButton = new JButton("OK");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg) {
				dispose();
			}
		});
		btnNewButton.setBounds(35, 39, 90, 25);
		getContentPane().add(btnNewButton);
		centreWindow(this);
		setVisible(true);
	}
	
	public static void centreWindow(ErrorPage frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
}
