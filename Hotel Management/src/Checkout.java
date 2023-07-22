import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
public class Checkout extends JFrame implements ActionListener{
	Choice ccustomer;
	JLabel lblroomnumber,lblcheckintime,lblcheckouttime,lblcus;
	JButton checkout,back,refresh;
	Date date;
	Checkout()
	{
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel text = new JLabel("Checkout");
		text.setBounds(100,10,100,30);
		text.setForeground(Color.BLUE);
		text.setFont(new Font("Tahoma",Font.PLAIN,20));
		add(text);
		
		JLabel lblid = new JLabel("Name");
		lblid.setBounds(30,50,100,30);
		add(lblid);
		
		JLabel lblid2 = new JLabel("Customer ID");
		lblid2.setBounds(30,90,100,30);
		add(lblid2);
		
		lblcus = new JLabel("-");
		lblcus.setBounds(150,90,200,30);
		add(lblcus);
		
		ccustomer = new Choice();//instead of JComboBox Choice used, as in JComboBox direct input is given in paranthesis but in choice inputs can be updated again and again where as in JComboBox we cannot 
		ccustomer.setBounds(150,55,150,25);
		add(ccustomer);
		
		JLabel lblroom = new JLabel("Room Number");
		lblroom.setBounds(30,130,100,30);
		add(lblroom);
		
		lblroomnumber = new JLabel("-");
		lblroomnumber.setBounds(150,130,100,30);
		add(lblroomnumber);
		
		JLabel lblcheckin= new JLabel("Checkin Time");
		lblcheckin.setBounds(30,180,100,30);
		add(lblcheckin);
		
		lblcheckintime = new JLabel("-");
		lblcheckintime.setBounds(150,180,180,30);
		add(lblcheckintime);
		
		JLabel lblcheckout= new JLabel("Checkout Time");
		lblcheckout.setBounds(30,230,100,30);
		add(lblcheckout);
		
		date = new Date();
		lblcheckouttime = new JLabel("-");
		lblcheckouttime.setBounds(150,230,180,30);
		add(lblcheckouttime);
		
		checkout = new JButton("Checkout");
		checkout.setBackground(Color.BLACK);
		checkout.setForeground(Color.WHITE);
		checkout.setBounds(30,280,90,30);
		checkout.addActionListener(this);
		add(checkout);
		
		refresh = new JButton("Refresh");
		refresh.setBackground(Color.BLACK);
		refresh.setForeground(Color.WHITE);
		refresh.setBounds(130,280,90,30);
		refresh.addActionListener(this);
		add(refresh);
		
		back = new JButton("Back");
		back.setBackground(Color.BLACK);
		back.setForeground(Color.WHITE);
		back.setBounds(230,280,90,30);
		back.addActionListener(this);
		add(back);
		
		try
		{
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery("select * from customer");
			while(rs.next()) //choice from sql updating all elements
			{
				ccustomer.add(rs.getString("name"));
// Since below entered code initially displays another values
//				lblcus.setText(rs.getString("document")+" No. "+rs.getString("number"));
//				lblroomnumber.setText(rs.getString("room"));
//				lblcheckintime.setText(rs.getString("checkintime"));
				
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/sixth.jpg"));
		Image i5 = i4.getImage().getScaledInstance(400, 250, Image.SCALE_DEFAULT);
		ImageIcon i6 = new ImageIcon(i5);
		JLabel image = new JLabel(i6);
		image.setBounds(350,50,400,250);
		add(image);
		
		setBounds(300,150,800,400);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == checkout)
		{
			String query1 = "delete from customer where name = '"+ccustomer.getSelectedItem()+"'";
			String query2 = "update room set availability = 'Available' where roomnumber = '"+lblroomnumber.getText()+"' ";
			
			try
			{
				Conn c = new Conn();
				c.s.executeUpdate(query1);
				c.s.executeUpdate(query2);
				
				JOptionPane.showMessageDialog(null,"Checkout done");
				
				setVisible(false);
				new Reception();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		else if(ae.getSource() == refresh)
		{
			String query="select * from customer where name = '"+ccustomer.getSelectedItem()+"'";
			try
			{
				Conn c = new Conn();
				ResultSet rs = c.s.executeQuery(query);
				while(rs.next())
				{
					lblcus.setText(rs.getString("document")+" No. "+rs.getString("number"));
					lblroomnumber.setText(rs.getString("room"));
					lblcheckintime.setText(rs.getString("checkintime"));
					lblcheckouttime.setText(""+date);
				}
				
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,"Error");
				System.out.println("Error in updating");			
			}
		}
		
		else
		{
			setVisible(false);
			new Reception();
		}
	}
	
	public static void main(String args[])
	{
		new Checkout();
	}
}
