import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchBookGui implements ActionListener {
	JFrame jf;
	JLabel Btitle,library;
	JTextField title;
	JTextArea viewsearchbooksinfo;
	JButton SearchBook,Back;
	DBTasks dbt;
	
	SearchBookGui()
	{
		jf=new JFrame("");
		dbt=new DBTasks();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(null);
		jf.setSize(470,400);
		title = new JTextField("");
		title.setBounds(130,70,170,30);
		Btitle=new JLabel("Book title");
		Btitle.setBounds(30,70,210,30);
		SearchBook=new JButton("Search Book");
		SearchBook.setBounds(60,120,140,30);
		SearchBook.addActionListener(this);
		viewsearchbooksinfo=new JTextArea("");
		viewsearchbooksinfo.setBounds(50, 200, 300, 290);
		viewsearchbooksinfo.setWrapStyleWord(true);
		viewsearchbooksinfo.setLineWrap(true);
		viewsearchbooksinfo.setOpaque(false);
		viewsearchbooksinfo.setEditable(false);
		viewsearchbooksinfo.setFocusable(false);
		Back=new JButton("Back");
		Back.setBounds(230,120,140,30);
		Back.addActionListener(this);
		library=new JLabel("LIBRARY MANAGEMENT SYSTEM");
		library.setBounds(130,30,210,30);
		jf.getContentPane().setBackground(Color.BLACK);
		SearchBook.setBorder(new LineBorder(Color.WHITE));
		Back.setBorder(new LineBorder(Color.WHITE));
		SearchBook.setBackground(Color.green);
		Back.setBackground(Color.red);
		SearchBook.setForeground(Color.BLACK);
		Back.setForeground(Color.BLACK);
		library.setFont(new Font("Bold", Font.BOLD, 12));
		library.setForeground(Color.white);
		Btitle.setFont(new Font("Serif", Font.BOLD, 12));
		Btitle.setForeground(Color.white);
		viewsearchbooksinfo.setForeground(Color.white);
		jf.add(library);
		jf.add(Back);
		jf.add(viewsearchbooksinfo);
		jf.add(title);
		jf.add(Btitle);
		jf.add(SearchBook);
		jf.setTitle("Search BOOKS");		
		jf.setVisible(true);

	}

	public static void main(String[]args)
	{
		new SearchBookGui();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==SearchBook)
		{
			String msg = dbt.SearchBook(title.getText());		
			viewsearchbooksinfo.setText(msg);
		}
		else if(e.getSource() == Back)
		{
			jf.dispose();
			new DBGui();
			
		}
		
	}

	
}

