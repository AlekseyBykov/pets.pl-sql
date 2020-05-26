package alekseybykov.portfolio.plsql;

import alekseybykov.portfolio.plsql.PlSqlTestBase;
import org.junit.Test;
import java.sql.SQLException;
import static org.junit.Assert.assertEquals;

public class PlSqlTest extends PlSqlTestBase {

	@Test
	public void testSimpleExpression() throws SQLException {
		String plSqlCode =
			"declare \n"
               + "   v_sum varchar2(20) := 2 + 2*2;\n"
               + "begin\n"
               + "   dbms_output.put_line(v_sum);\n"
               + "end;\n";
		assertEquals(String.valueOf(6), perform(plSqlCode));
	}

	@Test
	public void testUsingTypeAttribute() throws SQLException {
		String plSqlCode =
			"declare\n"
				+ "   v_id_type book.book_id%type;\n"
				+ "   v_id v_id_type%type;\n"
				+ "begin\n"
				+ "   v_id := 1;\n"
				+ "   dbms_output.put_line(v_id);\n"
				+ "end;\n";
		assertEquals(String.valueOf(1), perform(plSqlCode));
	}
}
