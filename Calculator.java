import java.awt.*;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import javax.swing.*;

public class Calculator  extends JFrame {

	
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menuBar;                                 // belka z "plik" itd
	private JMenu file;
	private JMenu edit;
	private JMenu help;
	private JMenuItem close;                                 // zawartosc menu "plik" w gornej belce
	private JMenuItem copy;
	private JMenuItem view;
	private JMenuItem about;
	
	private JTextArea display;    //JTextArea moze uzywac funkcji append, w przeciwienstwie do JTextfield
	
	private JButton clear;
	private JButton equals;
	private JButton zero;
	private JButton decimal;
	private JButton posNeg;
	private JButton one;
	private JButton two;
	private JButton three;
	private JButton four;
	private JButton five;
	private JButton six;
	private JButton seven;
	private JButton eight;
	private JButton nine;
	
	private JButton addition;
	private JButton substraction;
	private JButton division;
	private JButton multiplication;
	
	private double tempFirst = 0.0;
	
	private boolean[] operation = new boolean[4];
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());       //Dziêki temu kalk bedzie wygladal 
			                                                                           // jak z systemu, na ktorym jest uruchomiony.
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException    // try/catch na bledy wyskakujace, gdy sie nie uda
				| UnsupportedLookAndFeelException e) {                                      // oraz komunikat
			
			System.out.println("Nie uda³o siê za³adowaæ wygl¹du z systemu.");
		}
		
		new Calculator();
}
	
	public Calculator() {
		super("Calculator");
		sendMenuBar();
		sendDisplay();
		sendButtons();
		sendUI(this);
		
	}
	
	private void sendMenuBar() {
		menuBar = new JMenuBar();                                                // funkcje sterujace ...funkcjami
		file = new JMenu(" File ");
		edit = new JMenu (" Edit ");
		help = new JMenu (" Help ");
		close = new JMenuItem("Close");
		copy = new JMenuItem ("Copy");
		view = new JMenuItem ("View Help");
		about = new JMenuItem ("About this Calculator");
		setJMenuBar(menuBar);
		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(help);
		
		close.addActionListener(new ActionListener() {                         // listener dla polecenia close i zamykanie okna
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);                                                  // system exit 0 zamyka okno
				
			}
		});
		
		copy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {                            // kopiowanie i schowek
				String showDisplay = display.getText();                            // w showDisplay wrzucamy to, co napisalismy - getText
				StringSelection string = new StringSelection(showDisplay);          // wbudowany schowek nazywamy system i wrzucamy w niego stringi
				Clipboard system = Toolkit.getDefaultToolkit().getSystemClipboard();
				system.setContents(string, string);
			}
		});
		
		view.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Nothing to show for now", "Calculator", JOptionPane.OK_OPTION);
				
			}
		});
		
		about.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "A Project for Programming Class", "Calculator", JOptionPane.OK_OPTION);
				
			}
		});
		
		file.add(close);
		edit.add(copy);
		help.add(view);
		help.add(about);
		
	}
	
private void sendDisplay() {
		display = new JTextArea("0");
		display.setBounds(10, 10, 280, 55);
		display.setEditable(false);
		display.setFont(new Font("Arial",Font.PLAIN,32));
		add(display);
		
	}

private void sendButtons() {
	
	division = new JButton("/");
	division.setBounds(220, 70, 65, 55);
	division.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			setTempFirst(Double.parseDouble(display.getText()));
			display.setText("0");
			operation[0] = true;
		}
	});
	add(division);
	
	multiplication = new JButton("*");
	multiplication.setBounds(220, 132, 65, 55);
	multiplication.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			setTempFirst(Double.parseDouble(display.getText()));
			display.setText("0");
			operation[1] = true;
		}
	});
	add(multiplication);
	
	substraction = new JButton("-");
	substraction.setBounds(220, 194, 65, 55);
	substraction.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			setTempFirst(Double.parseDouble(display.getText()));
			display.setText("0");
			operation[2] = true;
			
		}
	});
	add(substraction);
	
	addition = new JButton("+");
	addition.setBounds(220, 256, 65, 55);
	addition.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			setTempFirst(Double.parseDouble(display.getText()));
			display.setText("0");
			operation[3] = true;			
		}
	});
	add(addition);
	
	equals = new JButton("=");
	equals.setBounds(10, 318, 135, 55);
	equals.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(operation[0])
				display.setText(Double.toString(getTempFirst() / Double.parseDouble(display.getText())));
			else if (operation[1])
				display.setText(Double.toString(getTempFirst() * Double.parseDouble(display.getText())));
			else if (operation[2])
				display.setText(Double.toString(getTempFirst() - Double.parseDouble(display.getText())));
			else if (operation[3])
				display.setText(Double.toString(getTempFirst() + Double.parseDouble(display.getText())));
			if(display.getText().endsWith(".0"))
				display.setText(display.getText().replace(".0", ""));
			setTempFirst(0.0);
			for(int i = 0; i <= 3; i++)
				operation[i] = false;
		}
	});
	add(equals);
	
	clear = new JButton("C");
	clear.setBounds(150, 318, 135, 55);
	clear.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			display.setText("0");
			setTempFirst(0.0);
			for(int i = 0; i <= 3; i++){
				operation[i] = false;
			}
		}
	});
	add(clear);
	
	zero = new JButton("0");
	zero.setBounds(10, 256, 65, 55);
	zero.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (display.getText().length() > 12)
				return;
			if(display.getText().equalsIgnoreCase("0")) {      // jesli mamy tylko zero na TextField, to bedzie zamieniane na to co klikniemy 
				display.setText("0");
				return;
			}
			display.append("0");                  //jak nazwa wskazuje dokladamy koolejny cyfry do sibie
					
		}
	});
	add(zero);
	
	decimal = new JButton(".");
	decimal.setBounds(80, 256, 65, 55);
	decimal.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (display.getText().contains("."))
				return;
			if(display.getText().equalsIgnoreCase("0")) {      
				display.setText(".");
				return;
			}
			display.append(".");                  //jak nazwa wskazuje dokladamy koolejny cyfry do sibie
		
		}
	});
	add(decimal);
	
	posNeg = new JButton("+/-");
	posNeg.setBounds(150, 256, 65, 55);
	posNeg.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(display.getText().equalsIgnoreCase("0"))
				return;
			display.setText(Double.toString(Double.parseDouble(display.getText()) * (-1)));
			if(display.getText().endsWith(".0"))
				display.setText(display.getText().replace(".0", ""));
		}
	});
	add(posNeg);
	
	one = new JButton("1");
	one.setBounds(10, 194, 65, 55);
	one.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (display.getText().length() > 12)
				return;
			if(display.getText().equalsIgnoreCase("0")) {      // jesli mamy tylko zero na TextField, to bedzie zamieniane na to co klikniemy 
				display.setText("1");
				return;
			}
			display.append("1");                  //jak nazwa wskazuje dokladamy koolejny cyfry do sibie
		}
		
	});
	add(one);
	
	two = new JButton("2");
	two.setBounds(80, 194, 65, 55);
	two.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (display.getText().length() > 12)
				return;
			if(display.getText().equalsIgnoreCase("0")) {      
				display.setText("2");
				return;
			}
			display.append("2");                  
					
		}
	});
	add(two);
	
	three = new JButton("3");
	three.setBounds(150, 194, 65, 55);
	three.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (display.getText().length() > 12)
				return;
			if(display.getText().equalsIgnoreCase("0")) {       
				display.setText("3");
				return;
			}
			display.append("3");                  
			
		}
	});
	add(three);
	
	four = new JButton("4");
	four.setBounds(10, 132, 65, 55);
	four.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (display.getText().length() > 12)
				return;
			if(display.getText().equalsIgnoreCase("0")) {      
				display.setText("4");
				return;
			}
			display.append("4");                 
					
		}
	});
	add(four);
	
	five = new JButton("5");
	five.setBounds(80, 132, 65, 55);
	five.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (display.getText().length() > 12)
				return;
			if(display.getText().equalsIgnoreCase("0")) {      
				display.setText("5");
				return;
			}
			display.append("5");                 
					
		}
	});
	add(five);
	
	six = new JButton("6");
	six.setBounds(150, 132, 65, 55);
	six.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (display.getText().length() > 12)
				return;
			if(display.getText().equalsIgnoreCase("0")) {       
				display.setText("6");
				return;
			}
			display.append("6");                  
			
		}
	});
	add(six);
	
	seven = new JButton("7");
	seven.setBounds(10, 70, 65, 55);
	seven.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (display.getText().length() > 12)
				return;
			if(display.getText().equalsIgnoreCase("0")) {       
				display.setText("7");
				return;
			}
			display.append("7");                 
		}
		
	});
	add(seven);
	
	eight = new JButton("8");
	eight.setBounds(80, 70, 65, 55);
	eight.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (display.getText().length() > 12)
				return;
			if(display.getText().equalsIgnoreCase("0")) {       
				display.setText("8");
				return;
			}
			display.append("8");
		}
		
	});
	add(eight);
	
	nine = new JButton("9");
	nine.setBounds(150, 70, 65, 55);
	nine.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (display.getText().length() > 12)
				return;
			if(display.getText().equalsIgnoreCase("0")) {       
				display.setText("9");
				return;
			}
			display.append("9");
		}
	});
	add(nine);
}

	private void sendUI(Calculator app) {                                          //ustawienia generalne wygladu i rozmiaru itp
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(305, 430);
		app.setLayout(null);
		app.setResizable(false);
		app.setLocationRelativeTo(null);
		app.setVisible(true);
	}

	public double getTempFirst() {
		return tempFirst;
	}

	public void setTempFirst(double tempFirst) {
		this.tempFirst = tempFirst;
	}

}
