import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class AddEmployee extends JFrame implements ActionListener 
{
	JTextField tfname,tfemail,tfphone,tfage,tfsalary,tfaadhar;
	JRadioButton rbmale,rbfemale;
	JButton submit;
	JComboBox cbjob;
	
	AddEmployee()
	{
		setLayout(null);
		
		//LABEL NAME 
		JLabel lblname=new JLabel("NAME");
		lblname.setBounds(60,30,120,30);
		lblname.setFont(new Font("Tahoma",Font.PLAIN,17));
		add(lblname);
		
		//LABEL TEXT FIELD
		tfname=new JTextField();
		tfname.setBounds(200,30,150,30);
		add(tfname);
		
		//LABEL AGE NAME 
		JLabel lblage=new JLabel("AGE");
		lblage.setBounds(60,80,120,30);
		lblage.setFont(new Font("Tahoma",Font.PLAIN,17));
		add(lblage);
		
		//LABEL AGE TEXT FIELD
		tfage=new JTextField();
		tfage.setBounds(200,80,150,30);
		add(tfage);		
		
		//LABEL gender NAME 
		JLabel lblgender=new JLabel("GENDER");
		lblgender.setBounds(60,130,120,30);
		lblgender.setFont(new Font("Tahoma",Font.PLAIN,17));
		add(lblgender);
		
		rbmale=new JRadioButton("Male");
		rbmale.setBounds(200,130,70,30);
		rbmale.setFont(new Font("Tahoma",Font.PLAIN,17));
		rbmale.setBackground(Color.WHITE);
		add(rbmale);
		
		rbfemale=new JRadioButton("Female");
		rbfemale.setBounds(280,130,100,30);
		rbfemale.setFont(new Font("Tahoma",Font.PLAIN,17));
		rbfemale.setBackground(Color.WHITE);
		add(rbfemale);
		
		ButtonGroup bg=new ButtonGroup();//for selecting any one in radio button either male or female 
		bg.add(rbmale);
		bg.add(rbfemale);
		
		JLabel lbljob=new JLabel("JOB");
		lbljob.setBounds(60,180,120,30);
		lbljob.setFont(new Font("Tahoma",Font.PLAIN,17));
		add(lbljob);
		
		//Drop down list is string 
		String str[]= {"Front Desk Clerks","Porters","HouseKeeping","Kitchen  Staff","Manager","Room Service","Chefs","Waiter/Waitress","Accountant"};
		cbjob=new JComboBox(str);//for this drop down list string with default values are important
		cbjob.setBounds(200,180,150,30);
		cbjob.setBackground(Color.WHITE);
		add(cbjob);
		
		//LABEL SALARY 
		JLabel lblsalary=new JLabel("SALARY");
		lblsalary.setBounds(60,230,120,30);
		lblsalary.setFont(new Font("Tahoma",Font.PLAIN,17));
		add(lblsalary);
				
		//LABEL TEXT FIELD
		tfsalary=new JTextField();
		tfsalary.setBounds(200,230,150,30);
		add(tfsalary);
		
		//LABEL PHONE 
		JLabel lblphone=new JLabel("PHONE");
		lblphone.setBounds(60,280,120,30);
		lblphone.setFont(new Font("Tahoma",Font.PLAIN,17));
		add(lblphone);
				
		//LABEL TEXT FIELD
		tfphone=new JTextField();
		tfphone.setBounds(200,280,150,30);
		add(tfphone);
		
		//LABEL EMAIL 
		JLabel lblemail=new JLabel("EMAIL");
		lblemail.setBounds(60,330,120,30);
		lblemail.setFont(new Font("Tahoma",Font.PLAIN,17));
		add(lblemail);
				
		//LABEL TEXT FIELD
		tfemail=new JTextField();
		tfemail.setBounds(200,330,150,30);
		add(tfemail);
		
		//LABEL EMAIL 
		JLabel lblaadhar=new JLabel("AADHAR");
		lblaadhar.setBounds(60,380,120,30);
		lblaadhar.setFont(new Font("Tahoma",Font.PLAIN,17));
		add(lblaadhar);
				
		//LABEL TEXT FIELD
		tfaadhar=new JTextField();
		tfaadhar.setBounds(200,380,150,30);
		add(tfaadhar);
		
		submit=new JButton("SUBMIT");
		submit.setBackground(Color.BLACK);
		submit.setForeground(Color.WHITE);//ie,. "SUBMIT" ka  color 
		submit.setBounds(200,430,150,30);
		submit.addActionListener(this);
		add(submit);
		
		ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/tenth.jpg"));//from system
		Image i2=i1.getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT);//setting size (scaling) as image
		ImageIcon i3=new ImageIcon(i2);//converting image to imageicon
		JLabel image=new JLabel(i3);//imageicon to label
		image.setBounds(380,60,450,350);//(_,_,_,X) X-->is for height of image by this cropping can be done 
		add(image);
		
		getContentPane().setBackground(Color.WHITE);//background color white
		setBounds(250,160,850,540);
		setVisible(true);
		
		
	}
	
	public void actionPerformed(ActionEvent ae)  //Action EVENT used NOTE ****
	{
		String name=tfname.getText();
		String age=tfage.getText();
		String salary=tfsalary.getText();
		String phone=tfphone.getText();
		String email=tfemail.getText();
		String aadhar=tfaadhar.getText();
		
		String gender=null;
		
		//for checking all values are entered 
		if(name.equals(""))
		{
			JOptionPane.showMessageDialog(null,"Name should not be empty");
			return;
		}
		if(email.equals(""))
		{
			JOptionPane.showMessageDialog(null,"Email should not be empty");
			return;
		}
		if(age.equals(""))
		{
			JOptionPane.showMessageDialog(null,"Age should not be empty");
			return;
		}
		if(salary.equals(""))
		{
			JOptionPane.showMessageDialog(null,"Salary should not be empty");
			return;
		}
		if(phone.equals(""))
		{
			JOptionPane.showMessageDialog(null,"Phone number should not be empty");
			return;
		}
		if(aadhar.equals(""))
		{
			JOptionPane.showMessageDialog(null,"Aadhar should not be empty");
			return;
		}
		
		
		//Checking what radio button is selected
		if(rbmale.isSelected()) {
			gender = "Male";
		}
		else if(rbfemale.isSelected()){
			gender = "Female";
		}
		
		//Obtaining the selected item from drop down list
		String job = (String) cbjob.getSelectedItem();//it returns object so to convert to string type cast the result using "(String)"
		
		//Storing values obtained from above to mysql so initially make mysql query  there
		//ie, execute "create table employee(name varchar(25),age varchar(10), gender varchar(15),job varchar(30),salary varchar(15),phone varchar(15),email varchar(40),aadhar varchar(20));"
		try
		{
			Conn conn = new Conn();//creating connection with sql by calling another class
			
			//storing the sql query in a string called query
			String query="insert into employee values('"+name+"','"+age+"','"+gender+"','"+job+"','"+salary+"','"+phone+"','"+email+"','"+aadhar+"')";
			
			//to update query "conn.s.executeUpdate(string_name)" conn-->Connection object created here, s--->Statement Object
			conn.s.executeUpdate(query);	
			
			JOptionPane.showMessageDialog(null,"Employee added Successfully");
			
			setVisible(false);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) 
	{
		new AddEmployee();
	}

}
