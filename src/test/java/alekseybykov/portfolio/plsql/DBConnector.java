package alekseybykov.portfolio.plsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

final public class DBConnector {

	private static DBConnector instance;
	private Connection connection;

	public static DBConnector getInstance() {
		if (instance == null) {
			instance = new DBConnector();
		} else {
			try {
				if (instance.getConnection().isClosed()) {
					instance = new DBConnector();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public Connection getConnection() {
		return connection;
	}

	private DBConnector() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@dbserver:1521/ora";
			String username = "scott";
			String password = "tiger";
			this.connection = DriverManager.getConnection(url, username, password);
			connection.setAutoCommit(false);
		} catch (ClassNotFoundException | SQLException cnfe) {
			cnfe.printStackTrace();
		}
	}
}
