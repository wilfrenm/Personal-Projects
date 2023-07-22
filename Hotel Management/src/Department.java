import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;//for shortcut of not using any loop concept in fetching content from sql and feeding to table applied in step  
//Also note that above package must be in classpath in properties-buildpath-library-classpath 
public class Department extends JFrame implements ActionListener {
	JTable  table;
	JButton back;
	
	Department()
	{
		
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel l1 = new JLabel("Department");
		l1.setBounds(140,10,100,20);
		add(l1);
		
		JLabel l2 = new JLabel("Budget");
		l2.setBounds(450,10,100,20);
		add(l2);
		
		table = new JTable();
		table.setBounds(0,50,700,350);
		add(table);
		
		try
		{
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery("select * from department");//to get all values from room sql and store in result set
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
		back.setBounds(280,400,120,30);
		add(back);
		
		
		setBounds(320,150,700,480);
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		setVisible(false);
		new Reception();
	}

	public static void main(String[] args) {
		new Department();

	}

}


