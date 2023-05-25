package dbquery;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class DbQuery {
	private DataSource dataSource;

	public DbQuery(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public int count() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection(); // 커넥션 풀에서 커넥션을 가져와 활성상태
			try(Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select count(*) from MEMBER")){
				rs.next();
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if(conn != null)
				try {
					conn.close(); // 반환 후 유후(idle) 상태
				} catch (SQLException e) {
					
				}
		}
	}
}
