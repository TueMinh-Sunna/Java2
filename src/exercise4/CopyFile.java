package exercise4;

import java.lang.reflect.InvocationTargetException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class CopyFile extends JFrame{
	
	private JTextField tfFrom;
	private JTextField tfTo;
	private JButton btnCopy;
	private JProgressBar progress;

	public CopyFile() {
		setTitle("Copy File");
		setSize(400, 200);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Box b, b1, b2, b3 ,b4;
		add(b = Box.createVerticalBox());
		
		b.add(b1 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10));
		b.add(b2 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10));
		b.add(b3 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10));
		b.add(b4 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10));
		
		JLabel lblFrom, lblTo;
		b1.add(lblFrom = new JLabel("From: ")); b1.add(tfFrom = new JTextField());
		b2.add(lblTo = new JLabel("To: ")); b2.add(tfTo = new JTextField());
		
		b3.add(btnCopy = new JButton("Copy...."));
		b4.add(progress = new JProgressBar());
		progress.setStringPainted(true);
		
		b.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		lblTo.setPreferredSize(lblFrom.getPreferredSize());
		
		tfFrom.setText("C:\\Users\\ngthi\\OneDrive\\Documentos\\data.txt");
		tfTo.setText("C:\\Users\\ngthi\\OneDrive\\Documentos\\dest.txt");
		
		btnCopy.addActionListener((e) -> {
			
			
		});
	}
	
	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		SwingUtilities.invokeLater(() -> new CopyFile().setVisible(true));
	}

}
