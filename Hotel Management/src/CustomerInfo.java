import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;//for shortcut of not using any loop concept in fetching content from sql and feeding to table applied in step  
//Also note that above package must be in classpath in properties-buildpath-library-classpath 
public class CustomerInfo extends JFrame implements ActionListener {
	JTable  table;
	JButton back;
	
	CustomerInfo()
	{
		
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel l1 = new JLabel("Document Type");
		l1.setBounds(10,10,100,20);
		add(l1);
		
		JLabel l2 = new JLabel("Number");
		l2.setBounds(160,10,100,20);
		add(l2);
		
		JLabel l3 = new JLabel("Name");
		l3.setBounds(290,10,100,20);
		add(l3);
		
		JLabel l4 = new JLabel("Gender");
		l4.setBounds(410,10,100,20);
		add(l4);
		
		JLabel l5 = new JLabel("Country");
		l5.setBounds(540,10,100,20);
		add(l5);
		
		JLabel l6 = new JLabel("Room Number");
		l6.setBounds(640,10,100,20);
		add(l6);
		
		JLabel l7 = new JLabel("Checkin time");
		l7.setBounds(760,10,100,20);
		add(l7);
		
		JLabel l8 = new JLabel("Deposit");
		l8.setBounds(900,10,100,20);
		add(l8);
		
		table = new JTable();
		table.setBounds(0,40,1000,400);
		add(table);
		
		try
		{
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery("select * from customer");//to get all values from room sql and store in result set
			table.setModel(DbUtils.resultSetToTableModel(rs));//*1****** used instead of looping to insert content from sql to table ****1*
						
			
		}
		catch(Exception e)
		{
			e.printStackTrace();			
		}
		
		
		back = new JButton("Back");
		back.setBackground(Color.BLACK);
		back.setForeground(Color.WHITE);
		back.addActionListener(this);
		back.setBounds(420,500,120,30);
		add(back);
		
		
		setBounds(180,130,1000,600);
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		setVisible(false);
		new Reception();
	}

	public static void main(String[] args) {
		new CustomerInfo();

	}

}


