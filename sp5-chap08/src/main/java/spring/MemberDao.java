package spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class MemberDao {

	private static long nextId = 0;
	private JdbcTemplate jdbcTemplate;
	private Map<String, Member> map = new HashMap<>();

	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void update(Member member) {
		jdbcTemplate.update("update MEMBER set NAME = ?, PASSWORD = ? where EMAIL = ?",
				member.getName(),member.getPassword(),member.getEmail()); // 쿼리의 인덱스 파라미터 전달
		
	}
	
	public Member selectByEmail(String email) {
		List<Member> results = jdbcTemplate.query(
				"select * from MEMBER where EMAIL = ?", // 쿼리 실행 
				new RowMapper<Member>() {
					@Override
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException{
						Member member = new Member(
								rs.getString("EMAIL"),
								rs.getString("PASSWORD"),
								rs.getString("NAME"),
								rs.getTimestamp("REGDATE").toLocalDateTime());
						member.setId(rs.getLong("ID"));
						return member;
					}
				}, email); // EMAIL = '?' (인덱스 파라미터)
		
		return results.isEmpty()? null : results.get(0);
	}

	public void insert(final Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); // GeneratedKeyHolder 객체 생성
		jdbcTemplate.update(new PreparedStatementCreator() { // PreparedeStatementCreator 객체와 KeyHolder 객체를 파라미터로 갖는다
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException { // 객체 생성
				PreparedStatement pstmt = con.prepareStatement(
						"insert into MEMBER (EMAIL,PASSWORD,NAME,REGDATE)"+
						"values (?,?,?,?)",
						new String[] {"ID"} ); // ID 칼럼이 자동 키 칼럼
				pstmt.setString(1, member.getEmail());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());
				pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
				return pstmt;
			}
		}, keyHolder); // 생성한 keyHolder 전달
		Number keyValue = keyHolder.getKey(); //보관된 키값을 구함
		member.setId(keyValue.longValue()); //long 타입으로 변환 
	}

	public List<Member> selectAll() {
		List<Member> results = jdbcTemplate.query("select * from MEMBER", new RowMapper<Member>() {
			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException{
				Member member = new Member (
					rs.getString("EMAIL"),
					rs.getString("PASSWORD"),
					rs.getString("NAME"),
					rs.getTimestamp("REGDATE").toLocalDateTime());
				member.setId(rs.getLong("ID"));
				return member;
			}
		});
		return results;
	}
	
	public int count() {
		Integer count = jdbcTemplate.queryForObject("select count(*) from MEMBER", Integer.class);
		//queryForObject :  실행 결과 행이 한 개인 경우
		return count;
	}
}
