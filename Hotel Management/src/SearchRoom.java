import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;//for shortcut of not using any loop concept in fetching content from sql and feeding to table applied in step  
//Also note that above package must be in classpath in properties-buildpath-library-classpath 
public class SearchRoom extends JFrame implements ActionListener {
	JTable  table;
	JButton back,submit;
	JComboBox bedType;
	JCheckBox available;
	
	SearchRoom()
	{
		
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel text = new JLabel("Search for Room");
		text.setFont(new Font("Tahoma",Font.PLAIN,20));
		text.setBounds(400,30,200,30);
		add(text);
		
		JLabel lblbed = new JLabel("Bed Type");
		lblbed.setBounds(50,100,100,20);
		add(lblbed);
		
		String a[] = {"Single Bed","Double Bed"};
		bedType = new JComboBox(a);
		bedType.setBounds(150,100,150,25);
		bedType.setBackground(Color.WHITE);
		add(bedType);
		
		available = new JCheckBox("Only display Available");
		available.setBounds(650,100,150,25);
		available.setBackground(Color.WHITE);
		add(available);
		
		
		
		JLabel l1 = new JLabel("Room Number");
		l1.setBounds(50,160,100,20);
		add(l1);
		
		JLabel l2 = new JLabel("Availability");
		l2.setBounds(270,160,100,20);
		add(l2);
		
		JLabel l3 = new JLabel("Cleaning Status");
		l3.setBounds(450,160,100,20);
		add(l3);
		
		JLabel l4 = new JLabel("Price");
		l4.setBounds(670,160,100,20);
		add(l4);
		
		JLabel l5 = new JLabel("Bed Type");
		l5.setBounds(870,160,100,20);
		add(l5);
		
		table = new JTable();
		table.setBounds(0,200,1000,300);
		add(table);
		
		try
		{
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery("select * from room");//to get all values from room sql and store in result set
			table.setModel(DbUtils.resultSetToTableModel(rs));//*1****** used instead of looping to insert content from sql to table ****1*
						
			
		}
		catch(Exception e)
		{
			e.printStackTrace();			
		}
		
		submit = new JButton("Submit");
		submit.setBackground(Color.BLACK);
		submit.setForeground(Color.WHITE);
		submit.addActionListener(this);
		submit.setBounds(300,520,120,30);
		add(submit);
		
		back = new JButton("Back");
		back.setBackground(Color.BLACK);
		back.setForeground(Color.WHITE);
		back.addActionListener(this);
		back.setBounds(500,520,120,30);
		add(back);
		
		
		setBounds(170,130,1000,600);
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == submit)
		{
			try
			{
				String query1 = "select * from room where bed_type = '"+bedType.getSelectedItem()+"' ";//to display all elements in room sql bed type with given data either single or double bed 
				String query2 = "select * from room where availability = 'Available' AND bed_type = '"+bedType.getSelectedItem()+"'";//to display only available in availability coloumn and also only selected bed type either single or double bed  
				
				Conn conn = new Conn();
				
				ResultSet rs;
				if(available.isSelected())//if available check box is selected then this if statement is executed
				{
					rs = conn.s.executeQuery(query2);
				}
				else//else part used because if check is not selected else statement is executed 
				{
					rs = conn.s.executeQuery(query1);
				}
				
				table.setModel(DbUtils.resultSetToTableModel(rs));//table values from sql are inserted into frame table ie; already existing table in frame is recreated to new format  
				
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			setVisible(false);
			new Reception();
		}
	}

	public static void main(String[] args) {
		new SearchRoom();

	}

}
