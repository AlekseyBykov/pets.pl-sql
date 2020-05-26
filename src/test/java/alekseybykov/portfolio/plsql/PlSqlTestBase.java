package alekseybykov.portfolio.plsql;

import alekseybykov.portfolio.plsql.utils.DBConnector;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PlSqlTestBase {

	private static Connection connection = DBConnector.getInstance().getConnection();

	@BeforeClass
	public static void enableDbmsOutput() throws SQLException {
		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate("begin dbms_output.enable(null); end;");
		}
	}

	@AfterClass
	public static void disableDbmsOutput() throws SQLException {
		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate("begin dbms_output.disable; end;");
		}
	}

	protected String perform(String plSqlCode) throws SQLException {
		try (Statement statement = connection.createStatement();
			 CallableStatement callableStatement = connection.prepareCall(
			" declare "
			+ " l_line varchar2(255); "
			+ " l_done number; "
			+ " l_buffer long; "
			+ "begin "
			+ " loop "
			+ " exit when length(l_buffer)+255 > :maxbytes OR l_done = 1; "
			+ " dbms_output.get_line( l_line, l_done ); "
			+ " l_buffer := l_buffer || l_line || chr(10); "
			+ " end loop; "
			+ " :done := l_done; "
			+ " :buffer := l_buffer; "
			+ "end;" )) {

			callableStatement.registerOutParameter( 2, java.sql.Types.INTEGER );
			callableStatement.registerOutParameter( 3, java.sql.Types.VARCHAR );
			statement.execute(plSqlCode);

			for(;;) {
				callableStatement.setInt( 1, 32000 );
				callableStatement.executeUpdate();
				if (callableStatement.getInt(2) == 1) {
					return callableStatement.getString(3).trim();
				}
			}
		}
	}
}
