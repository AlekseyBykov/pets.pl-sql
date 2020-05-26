package alekseybykov.portfolio.plsql.variables;

import alekseybykov.portfolio.plsql.PlSqlTestBase;
import org.junit.Test;
import java.sql.SQLException;
import static org.junit.Assert.assertEquals;

public class PlSqlVariablesTest extends PlSqlTestBase {

	@Test
	public void testSimpleExpression() throws SQLException {
		String plSqlCode =
			"declare \n"
               + "   v_sum varchar2(20) := 2 + 2*2;\n"
               + "begin\n"
               + "   dbms_output.put_line(v_sum);\n"
               + "end;\n";
		perform(plSqlCode);
		assertEquals(String.valueOf(6), perform(plSqlCode));
	}
}
