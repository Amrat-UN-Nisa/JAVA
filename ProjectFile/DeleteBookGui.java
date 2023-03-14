import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteBookGui implements ActionListener {
	JFrame jf;
	JLabel Bookid,msg,library;
	JTextField Bid;
	JButton DeleteBook,Back;
	DBTasks dbt;
	
	DeleteBookGui()
	{
		jf=new JFrame("");
		dbt=new DBTasks();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(null);
		jf.setSize(470,400);
		Bid = new JTextField("");
		Bid.setBounds(130,100,170,30);
		Bookid=new JLabel("Book id");
		msg = new JLabel("");
		msg.setBounds(130,150,300,30);
		msg.setFont(new Font("Bold", Font.BOLD, 12));
		msg.setForeground(Color.white);
		Bookid.setBounds(30,100,210,30);
		DeleteBook=new JButton("Delete Book");
		DeleteBook.setBounds(40,200,140,30);
		DeleteBook.addActionListener(this);
		Back=new JButton("Back");
		Back.setBounds(200,200,140,30);
		Back.addActionListener(this);
		library=new JLabel("LIBRARY MANAGEMENT SYSTEM");
		library.setBounds(130,30,210,30);
		jf.getContentPane().setBackground(Color.BLACK);
		DeleteBook.setBorder(new LineBorder(Color.WHITE));
		Back.setBorder(new LineBorder(Color.WHITE));
		DeleteBook.setBackground(Color.green);
		Back.setBackground(Color.red);
		DeleteBook.setForeground(Color.BLACK);
		Back.setForeground(Color.BLACK);
		library.setFont(new Font("Bold", Font.BOLD, 12));
		library.setForeground(Color.white);
		Bookid.setFont(new Font("Serif", Font.BOLD, 12));
		Bookid.setForeground(Color.white);
		jf.add(library);
		jf.add(Back);
		jf.add(msg);
		jf.add(Bid);
		jf.add(Bookid);
		jf.add(DeleteBook);
		jf.setTitle("Delete BOOKS");		
		jf.setVisible(true);

	}

	public static void main(String[]args)
	{
		new DeleteBookGui();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==DeleteBook)
		{
				String Msg = dbt.DeleteBook(Bid.getText());		
				msg.setText(Msg);
		}
		else if(e.getSource() == Back)
		{
			jf.dispose();
			new DBGui();
			
		}
	}

	
}
