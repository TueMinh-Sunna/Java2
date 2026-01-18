package exercise4;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class MyNotepad extends JFrame {
	private JProgressBar progressBar;
	private JTextArea textArea;

	public MyNotepad() {
		super("My Notepad");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

//        Tao thanh menu voi nut open, save, exit
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem openMenuItem = new JMenuItem("Open");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(exitMenuItem);
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);

		textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);
		add(scrollPane);

		add(progressBar = new JProgressBar(), BorderLayout.SOUTH);
		progressBar.setStringPainted(true);

		openMenuItem.addActionListener(e -> {
			JFileChooser fileChooser = new JFileChooser();
			int value = fileChooser.showOpenDialog(this);
			if (value == JFileChooser.APPROVE_OPTION) {
				String fileName = fileChooser.getSelectedFile().getAbsolutePath();
//				1. Loading without thread
//				loadingWithoutThread(textArea,fileName);
//				2. Loading with thread
//				Runnable task = new LoadingWithThread(textArea, fileName);
//				Thread thread = new Thread(task);
//				thread.start();
//				3. Loading with Swing Worker
				LoadingUsingSW task3 = new LoadingUsingSW(textArea, fileName);
				task3.execute();
				task3.addPropertyChangeListener(evt -> {
					if (evt.getPropertyName().equals("progress")) {
						progressBar.setValue((int) evt.getNewValue());
					}
				});
			}
		});
	}
	
	private void loadingWithoutThread(JTextArea textArea, String fileName) {
		//try with resources
		try (
			//AutoCloseable only
			FileReader reader = new FileReader(fileName);
			BufferedReader in = new BufferedReader(reader);
		) {
			while(in.ready()) {
				String line = in.readLine();
				textArea.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//mo
		//thao tac
		//dong
	}


	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new MyNotepad());
	}
}

//3. Using SwingWorker
class LoadingUsingSW extends SwingWorker<Long, Void> {
	private JTextArea textArea;
	private String fileName;
	
	public LoadingUsingSW(JTextArea textArea, String fileName) {
		super();
		this.textArea = textArea;
		this.fileName = fileName;
	}

	@Override
	protected Long doInBackground() throws Exception {
		
		long lines = 0L, i = 0L;
		try (Stream<String> stream = Files.lines(Path.of(fileName));) {
			lines = stream.count();
		}
		
		try (
				FileReader reader = new FileReader(fileName);
				BufferedReader in = new BufferedReader(reader);
			) {
				while(in.ready()) {
					String line = in.readLine();
					textArea.append(line + "\n");
					i++;
					setProgress((int) (i * 100 / lines));
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return lines;
	}
	
	@Override
	protected void done() {
		try {
			long lines = get();
			JOptionPane.showMessageDialog(null, lines);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		super.done();
	}
}

class LoadingWithThread implements Runnable {
	private JTextArea textArea;
	private String fileName;
	
	public LoadingWithThread(JTextArea textArea, String fileName) {
		super();
		this.textArea = textArea;
		this.fileName = fileName;
	}

	@Override
	public void run() {
		try (
				FileReader reader = new FileReader(fileName);
				BufferedReader in = new BufferedReader(reader);
			) {
				while(in.ready()) {
					String line = in.readLine();
					textArea.append(line + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
