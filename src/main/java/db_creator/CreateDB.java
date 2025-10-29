package db_creator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateDB {
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nova_news_6?useSSL=false", "root", "1234");
		
		String sql_insert_roles = "INSERT INTO roles (name) VALUE (?)";
		String sql_insert_user_status = "INSERT INTO user_status (user_status) VALUE (?)";
		String sql_insert_news_status = "INSERT INTO news_status (title) VALUE (?)";
		
		PreparedStatement ps = connection.prepareStatement(sql_insert_roles);
		PreparedStatement ps1 = connection.prepareStatement(sql_insert_user_status);
		PreparedStatement ps2 = connection.prepareStatement(sql_insert_news_status);
		
		
		ps.setString(1, "user");
		ps.executeUpdate();
		
		ps.setString(1, "reporter");
		ps.executeUpdate();
		
		ps.setString(1, "moderator");
		ps.executeUpdate();
		
		ps.setString(1, "admin");
		ps.executeUpdate();
		
		ps.setString(1, "super_admin");
		ps.executeUpdate();
		
		ps1.setString(1, "active");
		ps1.executeUpdate();
		
		ps1.setString(1, "blocked");
		ps1.executeUpdate();
		
		ps2.setString(1, "published");
		ps2.executeUpdate();

		ps2.setString(1, "draft");
		ps2.executeUpdate();

		ps2.setString(1, "archived");
		ps2.executeUpdate();
		
		connection.close();
	}

}
