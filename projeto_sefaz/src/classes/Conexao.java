package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public static Connection faz_conexao() throws SQLException {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			return DriverManager.getConnection("jdbc:mysql://localhost/db_senhas", "root", "1234");
			
			
			
		} catch (ClassNotFoundException e) {
			
			throw new SQLException(e.getException());
			
			
		}
		
	}
	
	
}
