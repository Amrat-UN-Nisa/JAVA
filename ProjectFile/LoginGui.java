import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginGui implements ActionListener {
	JFrame jf;
	JLabel Username,Password,msg,library;
	JTextField userName,password;
	JButton Login; 
	DBTasks dbt;
	
	LoginGui()
	{
		jf=new JFrame("");
		dbt=new DBTasks();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(null);
		
		msg = new JLabel("");
		msg.setBounds(140, 130, 200, 30);
		userName = new JTextField("");
		userName.setBounds(130,90,170,30);
		password = new JPasswordField("");
		password.setBounds(130,140,170,30);
		Username=new JLabel("Username");
		Password = new JLabel("Password");
		Username.setBounds(50,90,170,30);
		Password.setBounds(50,140,170,30);
		Login=new JButton("LOGIN");
		Login.setBounds(170,200,90,30);
		Login.addActionListener(this);
		library=new JLabel("LIBRARY MANAGEMENT SYSTEM");
		library.setBounds(130,30,210,30);
		library.setForeground(Color.white);
		jf.getContentPane().setBackground(Color.BLACK);
		Login.setBorder(new LineBorder(Color.BLACK));
		Login.setBackground(Color.GRAY);
		Login.setForeground(Color.BLACK);
		Username.setForeground(Color.WHITE);
		Password.setForeground(Color.WHITE);
		jf.add(library);
		jf.add(msg);
		jf.add(userName);
		jf.add(password);
		jf.add(Password);
		jf.add(Username);
		jf.add(Login);
		jf.setTitle("Library Management System");
		
		
        jf.setSize(470,400);
		jf.setVisible(true);		
	}

	public static void main(String[]args)
	{
		new LoginGui();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==Login)
		{
			String username = userName.getText();
			String pass = password.getText();
			String Msg = dbt.Login(username, pass);
			if (!Msg.equals("Wrong username/password"))
			{
				jf.dispose();
				new DBGui();
			}
			else 
			{
				msg.setText(Msg);
			}
		}
	}
}


