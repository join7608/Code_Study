package 자바25_프로그밍연습2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SubDel extends Frame implements ActionListener {
	private Dimension dimen, dimen1;
	private int xpos, ypos;
	Font font20 = new Font("SansSerif", Font.PLAIN, 20);
	Font font30 = new Font("SansSerif", Font.BOLD, 30);
	
	Label lbTitle = new Label("회원삭제");
	Label lbName = new Label("이름 : ");
	Label lbList = new Label("회원리스트");

	TextField tfName = new TextField();	

	Button btnSearch = new Button("검색");
	Button btnSearchAll = new Button("전체보기");
	Button btnDel = new Button("삭제하기");
	
	TextArea ta = new TextArea();
	
	public SubDel()
	{
		super("회원삭제");
		this.setSize(500,600);
		this.center();
		this.init();
		this.start();
		this.setVisible(true);
	}
	void init() {		
		this.setLayout(null);
		this.add(lbTitle);this.add(lbName);
		this.add(tfName);this.add(ta);this.add(tfName);		
		this.add(btnSearch);this.add(btnSearchAll);
		this.add(btnDel);
		this.add(lbList);
		
		lbTitle.setBounds(170, 50, 200, 30);lbTitle.setFont(font30);
		lbName.setBounds(50, 100, 100, 30);lbName.setFont(font20);
		tfName.setBounds(150, 100, 150, 30);tfName.setFont(font20);
		btnSearch.setBounds(350, 100, 100, 30);btnSearch.setFont(font20);
		btnSearchAll.setBounds(350, 150, 100, 30);btnSearchAll.setFont(font20);
		btnDel.setBounds(50, 200, 400, 70);btnDel.setFont(font20);
		lbList.setBounds(50, 300, 200, 30);lbList.setFont(font20);
		ta.setBounds(50, 350, 400, 200);ta.setFont(font20);
		
	}
	void start()
	{
		btnSearch.addActionListener(this);
		btnSearchAll.addActionListener(this);
	}
	void center() {
		dimen = Toolkit.getDefaultToolkit().getScreenSize();
		dimen1 = this.getSize();
		xpos = (int) (dimen.getWidth() / 2 - dimen1.getWidth() / 2);
		ypos = (int) (dimen.getHeight() / 2 - dimen1.getHeight() / 2);
		this.setLocation(xpos, ypos);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				delClose();
			}
		});
	}
	void delClose()
	{
		this.setVisible(false);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSearch)
		{
			System.out.println("검색대상:"+tfName.getText());
			search(tfName.getText());
		}
		else if(e.getSource() == btnSearchAll)
		{
			System.out.println("전체검색");			
			searchAll();
		}
		
	}
	void search(String name)
	{
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException ee) {
			System.out.println("드라이브가 연결안됨.");
			System.exit(0);
		}
		Connection conn = null;
		String url = "jdbc:mysql://127.0.0.1:3306/dw202?"
				+ "useUnicode=true&characterEncoding=utf8";				
		String id = "root";
		String pass = "qwer";
		Statement stmt = null;
		ResultSet rs = null;
		String query = "select * from member";
		try {
			conn = DriverManager.getConnection(url, id, pass);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				if(rs.getString(4).equals(name))
				{
					ta.setText(rs.getInt(1) + " / " + rs.getString(2)
					+ " / " + rs.getString(3)
					+ " / " + rs.getString(4)
					+ " / " + rs.getString(5));
				}
			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException ee) {
			System.err.println("error = " + ee.toString());
		}
	}
	void searchAll()
	{
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException ee) {
			System.out.println("드라이브가 연결안됨.");
			System.exit(0);
		}
		Connection conn = null;
		String url = "jdbc:mysql://127.0.0.1:3306/dw202?"
				+ "useUnicode=true&characterEncoding=utf8";				
		String id = "root";
		String pass = "qwer";
		Statement stmt = null;
		ResultSet rs = null;
		String query = "select * from member";
		try {
			conn = DriverManager.getConnection(url, id, pass);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			String result="";
			while (rs.next()) {
				result += rs.getInt(1) + " / " + rs.getString(2)
				+ " / " + rs.getString(3)
				+ " / " + rs.getString(4)
				+ " / " + rs.getString(5)+"\n";			
			}
			ta.setText(result);
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException ee) {
			System.err.println("error = " + ee.toString());
		}
	}

}









