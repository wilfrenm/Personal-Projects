import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Hotel extends JFrame implements ActionListener{//ActionListener for action purpose in button

	Hotel()
	{
		setSize(1200,565);
		setLocation(100,100);
		
		ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/first.jpg"));
		JLabel image=new JLabel(i1);
		add(image);
		JLabel text=new JLabel("HOTEL MANAGEMENT SYSTEM");
		text.setBounds(20,430,1000,100);
		text.setForeground(Color.WHITE);
		text.setFont(new Font("serif",Font.PLAIN,50));
		image.add(text);
		
		JButton next=new JButton("Next");
		next.setBounds(1000,450,150,50);
		next.setBackground(Color.WHITE);
		next.setForeground(Color.MAGENTA);
		next.addActionListener(this);
		next.setFont(new Font("serif",Font.PLAIN,25));
		image.add(next);
		
		
		setVisible(true);
		
		while(true)//for Blinking of the Hotel management 
		{
			text.setVisible(false);
			try {
				Thread.sleep(500);
			}
			catch (Exception e){
				e.printStackTrace();
			}
			text.setVisible(true);
			try {
				Thread.sleep(500);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	public void actionPerformed(ActionEvent ae)// method override because if not used line 4 Hotel la error varum
	{
		setVisible(false);
		new Login();
	}
	public static void main(String[] args) 
	{
		new Hotel();
		
	}

}
