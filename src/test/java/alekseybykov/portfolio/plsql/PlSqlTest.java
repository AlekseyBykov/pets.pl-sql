package alekseybykov.portfolio.plsql;

import org.junit.Test;
import java.sql.SQLException;
import static org.junit.Assert.assertEquals;

public class PlSqlTest extends PlSqlTestBase {

	@Test
	public void test() throws SQLException {
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
