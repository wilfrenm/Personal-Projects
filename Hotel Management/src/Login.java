import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;//for ResultSet in 72 line 
public class Login extends JFrame implements ActionListener{
	
	JTextField username;
	JPasswordField password;
	JButton login,cancel;
	
	Login()
	{
		getContentPane().setBackground(Color.WHITE);//option try without it
		
		setLayout(null);
		JLabel user=new JLabel("Username");
		user.setBounds(40,20,100,30);
		add(user);
		
		username=new JTextField();
		username.setBounds(150,20,150,30);
		add(username);
		
		JLabel pass=new JLabel("Password");
		pass.setBounds(40,70,100,30);
		add(pass);
		
		password=new JPasswordField();
		password.setBounds(150,70,150,30);
		add(password);
		
		
		login=new JButton("Login");
		login.setBounds(40,150,120,30);
		login.setBackground(Color.BLACK);
		login.setForeground(Color.WHITE);
		login.addActionListener(this);
		add(login);
		
		cancel=new JButton("Cancel");
		cancel.setBounds(180,150,120,30);
		cancel.setBackground(Color.BLACK);
		cancel.setForeground(Color.WHITE);
		cancel.addActionListener(this);
		add(cancel);
		
		
		ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
		Image i2=i1.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
		ImageIcon i3=new ImageIcon(i2);
		JLabel image=new JLabel(i3);
		image.setBounds(350,10,200,200);
		add(image);
		
		setBounds(500,200,600,300);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==login)//to check if login button clicked
		{
			String user=username.getText();//.getText() to get text from user 
			String pass=password.getText();
			
			try
			{
				Conn c=new Conn();
				//with the help of java hitting mysql query
				String query="select * from login where username = '"+ user +"' and password = '" +  pass + "'";//username(sql)-user(here) password(sql)-pass(here)
				//here '" given means user here is in double Quotes("") and in string but in sql single quote('') so that user and pass is converted to single quote by using single quote before and after double quotes
				
				//to execute mysql query
				ResultSet rs = c.s.executeQuery(query);//Conn class c and statement s in login class and resultset from myql is returned here using ResultSet function
				
				//To check if result has occurred from resulset
				if(rs.next()) 
				{
					setVisible(false);
					new Dashboard();//when details correct redirect to dash board
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Invalid username or password");// for pop up dialog box
					setVisible(false);
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(ae.getSource()==cancel)//to check if cancel button clicked
		{
			setVisible(false);
		}
	}
	
	public static void main(String args[])
	{
		new Login();
	}
}
