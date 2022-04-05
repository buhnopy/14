package ex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDao1 {
	
	private MemberDao1() {}
	private static MemberDao1 instance = new MemberDao1();
	public static MemberDao1 getInstance() {
		return instance;
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url      = "jdbc:mysql://localhost:3306/login_ex?serverTimezone=UTC";
			String user     = "root";		
			String password = "1234";  
			
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public boolean insertMember(MemberDto1 memberDto1) {
		boolean isFirstMember = false;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE ID = ?");
			pstmt.setString(1, memberDto1.getId());
			rs = pstmt.executeQuery();
			
			if (!rs.next()) {
				pstmt = conn.prepareStatement("INSERT INTO MEMBER VALUES(?,?,?,NOW())");
				pstmt.setString(1, memberDto1.getId());
				pstmt.setString(2, memberDto1.getPasswd());
				pstmt.setString(3, memberDto1.getName());
				pstmt.executeUpdate();
				isFirstMember = true;
				
			}
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return isFirstMember; 
	}
	
	public boolean loginMember(String id, String passwd) {
		
		boolean isValidMember = false;
		
		try {
			conn = getConnection();
			pstmt = prepareStatement("SELECT * FROM MEMBER WHERE ID = ? AND PASSWD = ?");
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				isValidMember = true;
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return isValidMember;
	}
	
	
	
}
