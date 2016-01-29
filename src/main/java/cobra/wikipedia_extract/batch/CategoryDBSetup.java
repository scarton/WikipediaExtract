package cobra.wikipedia_extract.batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cobra.wikipedia_extract.CatCollector;

public class CategoryDBSetup {
	final static Logger logger = LoggerFactory.getLogger(CatCollector.class);
	private final static String DB_NAME="WikiTopicsDB";
	private 
	Connection conn;

	public static void main(String[] args) throws SQLException {
		CategoryDBSetup app = new CategoryDBSetup();
		String path=args[0];

		app.getConnection(path);
		app.createDB();
	}

	public void getConnection(String path) throws SQLException {
		String dbUrl = "jdbc:derby:"+path+DB_NAME+";create=true";
		conn = DriverManager.getConnection(dbUrl);
	}

	public void createDB() throws SQLException {
		Statement stmt = conn.createStatement();

		// drop table
		// stmt.executeUpdate("Drop Table users");

		// create table
		stmt.executeUpdate("Create table users (id int primary key, name varchar(30))");

		// insert 2 rows
		stmt.executeUpdate("insert into users values (1,'tom')");
		stmt.executeUpdate("insert into users values (2,'peter')");

		// query
		ResultSet rs = stmt.executeQuery("SELECT * FROM users");

		// print out query result
		while (rs.next()) {
			System.out.printf("%d\t%s\n", rs.getInt("id"), rs.getString("name"));
		}
	}
}