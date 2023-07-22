import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class UpdateCheck extends JFrame implements ActionListener{
	Choice ccustomer;
	JTextField tfroom,tfname,tfcheckin,tfpaid,tfpending;
	JButton check,update,back;
	
	UpdateCheck()
	{
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel text = new JLabel("Update Status");
		text.setFont(new Font("Tahoma",Font.PLAIN,20));
		text.setBounds(90,20,200,30);
		text.setForeground(Color.BLUE);
		add(text);
		
		JLabel lblid = new JLabel("Name");
		lblid.setBounds(30,80,100,20);
		add(lblid);
		
		ccustomer = new Choice();//instead of JComboBox Choice used, as in JComboBox direct input is given in paranthesis but in choice inputs can be updated again and again where as in JComboBox we cannot 
		ccustomer.setBounds(200,80,150,25);
		add(ccustomer);
		
		try
		{
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery("select * from customer");
			while(rs.next()) //choice from sql updating all elements
			{
				ccustomer.add(rs.getString("name"));
				
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		JLabel lblroom = new JLabel("Room Number");
		lblroom.setBounds(30,120,100,20);
		add(lblroom);
		
		tfroom = new JTextField();
		tfroom.setBounds(200,120,150,25);
		add(tfroom);
		
		JLabel lblname = new JLabel("Customer ID");
		lblname.setBounds(30,160,100,20);
		add(lblname);
		
		tfname = new JTextField();//***  here name is used as customer id  NOTE  ***
		tfname.setBounds(200,160,150,25);
		add(tfname);
		
		JLabel lblcheckin = new JLabel("Checkin Time");
		lblcheckin.setBounds(30,200,100,20);
		add(lblcheckin);
		
		tfcheckin = new JTextField();
		tfcheckin.setBounds(200,200,150,25);
		add(tfcheckin);
		
		JLabel lblpaid = new JLabel("Amount Paid");
		lblpaid.setBounds(30,240,100,20);
		add(lblpaid);
		
		tfpaid = new JTextField();
		tfpaid.setBounds(200,240,150,25);
		add(tfpaid);
		
		JLabel lblpending = new JLabel("Pending Amount");
		lblpending.setBounds(30,280,100,20);
		add(lblpending);
		
		tfpending = new JTextField();
		tfpending.setBounds(200,280,150,25);
		add(tfpending);
		
		check = new JButton("Check");
		check.setBackground(Color.BLACK);
		check.setForeground(Color.WHITE);
		check.setBounds(30,340,100,30);
		check.addActionListener(this);
		add(check);
		
		update = new JButton("Update");
		update.setBackground(Color.BLACK);
		update.setForeground(Color.WHITE);
		update.setBounds(150,340,100,30);
		update.addActionListener(this);
		add(update);
		
		back = new JButton("Back");
		back.setBackground(Color.BLACK);
		back.setForeground(Color.WHITE);
		back.setBounds(270,340,100,30);
		back.addActionListener(this);
		add(back);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/nine.jpg"));
		JLabel image = new JLabel(i1);
		image.setBounds(400,50,500,300);
		add(image);
		
		
		setBounds(200,150,980,500);
		setVisible(true);
		
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()  == check) 
		{
			String cusname = ccustomer.getSelectedItem();
			String query = "select * from customer where name = '"+cusname+"'";
			try
			{
				Conn c = new Conn();
				ResultSet rs = c.s.executeQuery(query);
				while(rs.next()) 
				{
					//setting values for text field "setText(rs.getString("sql_name")" 
					tfroom.setText(rs.getString("room"));//setting room number from sql to here in text field
					tfname.setText(rs.getString("number"));//"room" and "name" are field in sql
					tfcheckin.setText(rs.getString("checkintime"));
					tfpaid.setText(rs.getString("deposit"));
					
				}
				//The below statement is for getting actual price of room and to calculate the balance amount from deposit to room price
				ResultSet rs2 = c.s.executeQuery("select * from room where roomnumber = '"+tfroom.getText()+"'");
				while(rs2.next()) 
				{
					String price = rs2.getString("price");
					//converting string to numbers for arithmetic calculation 
					int amountPaid = Integer.parseInt(price)-Integer.parseInt(tfpaid.getText());
					tfpending.setText(""+amountPaid);//converts number to string **** ""+var_name_of_integer *****
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
			String number = ccustomer.getSelectedItem();
			String room = tfroom.getText();
			String name = tfname.getText();
			String checkin = tfcheckin.getText();
			String deposit = tfpaid.getText();
			
			try
			{
				Conn c = new Conn();
//				Note here exceuteUpdate is used to update any value
//				****** also note the command used here *********
				c.s.executeUpdate("update customer set room='"+room+"',name = '"+name+"',checkintime = '"+checkin+"',deposit = '"+deposit+"' where number = '"+number+"'");
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
		new UpdateCheck();
		
		
		

	}

}
