import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class UpdateRoom extends JFrame implements ActionListener{
	
	Choice ccustomer;
	JTextField tfroom,tfavailable,tfstatus,tfpaid,tfpending;
	JButton check,update,back;
	
	UpdateRoom()
	{
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel text = new JLabel("Update Room Status");
		text.setFont(new Font("Tahoma",Font.PLAIN,25));
		text.setBounds(30,20,250,30);
		text.setForeground(Color.BLUE);
		add(text);
		
//		JLabel lblid = new JLabel("Name");
//		lblid.setBounds(30,80,100,20);
//		add(lblid);
//		
//		ccustomer = new Choice();//instead of JComboBox Choice used, as in JComboBox direct input is given in paranthesis but in choice inputs can be updated again and again where as in JComboBox we cannot 
//		ccustomer.setBounds(200,80,150,25);
//		add(ccustomer);
//		
//		try
//		{
//			Conn c = new Conn();
//			ResultSet rs = c.s.executeQuery("select * from customer");
//			while(rs.next()) //choice from sql updating all elements
//			{
//				ccustomer.add(rs.getString("name"));
//				
//			}
//			
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
		
		JLabel lblroom = new JLabel("Room Number");
		lblroom.setBounds(30,80,100,20);
		add(lblroom);
		
		tfroom = new JTextField();
		tfroom.setBounds(200,80,150,25);
		add(tfroom);
		
		JLabel lblname = new JLabel("Availability");
		lblname.setBounds(30,140,100,20);
		add(lblname);
		
		JLabel lblname1 = new JLabel("(Available/Occupied)");
		lblname1.setBounds(10,160,150,20);
		add(lblname1);
		
		tfavailable = new JTextField();
		tfavailable.setBounds(200,140,150,25);
		add(tfavailable);
		
		JLabel lblcheckin = new JLabel("Cleaning Satus");
		lblcheckin.setBounds(30,210,100,20);
		add(lblcheckin);
		
		JLabel lblcheckin1 = new JLabel("(Cleaned / Dirty)");
		lblcheckin1.setBounds(25,230,150,20);
		add(lblcheckin1);
		
		tfstatus = new JTextField();
		tfstatus.setBounds(200,210,150,25);
		add(tfstatus);
		
		check = new JButton("Check");
		check.setBackground(Color.BLACK);
		check.setForeground(Color.WHITE);
		check.setBounds(30,300,100,30);
		check.addActionListener(this);
		add(check);
		
		update = new JButton("Update");
		update.setBackground(Color.BLACK);
		update.setForeground(Color.WHITE);
		update.setBounds(150,300,100,30);
		update.addActionListener(this);
		add(update);
		
		back = new JButton("Back");
		back.setBackground(Color.BLACK);
		back.setForeground(Color.WHITE);
		back.setBounds(270,300,100,30);
		back.addActionListener(this);
		add(back);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/seventh.jpg"));
		Image i2 = i1.getImage().getScaledInstance(500,300,Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		image.setBounds(400,50,500,300);
		add(image);
		
		
		setBounds(200,150,980,450);
		setVisible(true);
		
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()  == check) 
		{
//			String id = tfroom.getText();
//			String query = "select * from room where roomnumber = '"+id+"'";
			try
			{
				Conn c = new Conn();
//				ResultSet rs = c.s.executeQuery(query);
//				while(rs.next()) 
//				{
//					//setting values for text field "setText(rs.getString("sql_name")" 
//					tfroom.setText(rs.getString("room"));//setting room number from sql to here in text field
////					tfavailable.setText(rs.getString("name"));//"room" and "name" are field in sql
////					tfstatus.setText(rs.getString("checkintime"));
//					
//				}
				//The below statement is for getting actual price of room and to calculate the balance amount from deposit to room price
				ResultSet rs2 = c.s.executeQuery("select * from room where roomnumber = '"+tfroom.getText()+"'");
				while(rs2.next()) 
				{
					tfavailable.setText(rs2.getString("availability"));
					tfstatus.setText(rs2.getString("cleaning_status"));
					
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		//To update the new value after payment 
		else if(ae.getSource() == update)
		{
			String room = tfroom.getText();
			String available = tfavailable.getText();
			String status = tfstatus.getText();
			
			try
			{
				Conn c = new Conn();
//				Note here exceuteUpdate is used to update any value
//				****** also note the command used here *********
				c.s.executeUpdate("update room set availability='"+available+"',cleaning_status = '"+status+"' where roomnumber = '"+room+"'");
				JOptionPane.showMessageDialog(null,"Data Updated Successfully");
				new Reception();
				setVisible(false);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			new Reception();
			setVisible(false);
		}
	}
	
	public static void main(String[] args) {
		new UpdateRoom();

	}

}


