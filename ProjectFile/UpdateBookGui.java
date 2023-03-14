import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateBookGui implements ActionListener {
	JFrame jf;
	JLabel Btitle,Bstatus,Bpublisher,Btype, Msg,library, Bid;
	JTextField title,status,publisher,type, bid;
	JButton updateBook,Back, search;
	DBTasks dbt;
	
	UpdateBookGui(){
		jf=new JFrame("");
		dbt=new DBTasks();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(null);
		jf.setSize(470,400);
		Bid = new JLabel("Book Id");
		Bid.setBounds(30,60,210,30);
		bid= new JTextField("");
		bid.setBounds(130, 60, 170, 30);
		Btitle=new JLabel("Title");
		Btitle.setBounds(30,100,210,30);
		Bpublisher=new JLabel("Publisher");
		Bpublisher.setBounds(30,140,210,30);
		Bstatus=new JLabel("Status");
		Bstatus.setBounds(30,180,210,30);
		Btype=new JLabel("Type");
		Btype.setBounds(30,220,210,30);
		title=new JTextField("");
		title.setBounds(130,100,170,30);
		publisher=new JTextField("");
		publisher.setBounds(130,140,170,30);
		status=new JTextField("");
		status.setBounds(130,180,170,30);
		status.setEditable(false);
		type=new JTextField("");
		type.setBounds(130,220,170,30);
		search = new JButton("Search");
		search.setBounds(350, 60, 85, 30);
		search.addActionListener(this);
		updateBook=new JButton("Update Book");
		updateBook.setBounds(50,300,140,30);
		updateBook.addActionListener(this);
		Back=new JButton("Back");
		Back.setBounds(200,300,140,30);
		Back.addActionListener(this);
		Msg=new JLabel("");
		Msg.setBounds(70,260,300,30);
		Msg.setFont(new Font("Bold", Font.BOLD, 12));
		Msg.setForeground(Color.white);
		library=new JLabel("LIBRARY MANAGEMENT SYSTEM");
		library.setBounds(130,20,210,30);
		jf.getContentPane().setBackground(Color.BLACK);
		updateBook.setBorder(new LineBorder(Color.WHITE));
		Back.setBorder(new LineBorder(Color.WHITE));
		updateBook.setBackground(Color.green);
		search.setBackground(Color.orange);
		Back.setBackground(Color.red);
		updateBook.setForeground(Color.BLACK);
		Back.setForeground(Color.BLACK);
		library.setFont(new Font("Bold", Font.BOLD, 12));
		library.setForeground(Color.white);
		Btitle.setFont(new Font("Serif", Font.BOLD, 12));
		Bpublisher.setFont(new Font("Serif", Font.BOLD, 12));
		Bstatus.setFont(new Font("Serif", Font.BOLD, 12));
		Btype.setFont(new Font("Serif", Font.BOLD, 12));
		Bid.setForeground(Color.white);
		Btitle.setForeground(Color.white);
		Btype.setForeground(Color.white);
		Bstatus.setForeground(Color.white);
		Bpublisher.setForeground(Color.white);
		jf.setTitle("Update Book");
		jf.add(Bid);
		jf.add(bid);
		jf.add(search);
		jf.add(library);
		jf.add(Back);
		jf.add(Msg);
		jf.add(Btitle);
		jf.add(Bpublisher);
		jf.add(Bstatus);
		jf.add(Btype);
		jf.add(title);
		jf.add(publisher);
		jf.add(status);
		jf.add(type);
		jf.add(updateBook);		
		jf.setVisible(true);
	}

	public static void main(String[]args)
	{
		new UpdateBookGui();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==search)
		{
			String bookID = bid.getText();
			String[] bookDetails = dbt.GetBook(bookID);
			if (bookDetails!= null)
			{
				title.setText(bookDetails[1]);
				status.setText(bookDetails[2]);
				publisher.setText(bookDetails[3]);
				type.setText(bookDetails[4]);
			}
			else 
			{
				Msg.setText("Book not found");
			}
			
		}	
		else if(e.getSource()==updateBook)
		{
			String bookTitle = title.getText();
			String bookPublisher = publisher.getText();
			String BookStatus = status.getText();
			String Booktype = type.getText();
			if (!bookTitle.isEmpty() && !bookPublisher.isEmpty() && !Booktype.isEmpty())
			{				
				String msg = dbt.UpdateBook(bid.getText(), bookTitle, bookPublisher, Booktype);							
				Msg.setText(msg);
			}
			else 
			{
				Msg.setText("Please Enter all Details");				
			}
		}
		else if(e.getSource()==Back)
		{
			jf.dispose();
			new DBGui();
		}
	}
}
