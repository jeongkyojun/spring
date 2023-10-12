package spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

class MemberRowMapper implements RowMapper<Member>{

	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member member = new Member(
			rs.getString("EMAIL"), 
			rs.getString("PASSWORd"), 
			rs.getString("NAME"),
			rs.getTimestamp("REGDATE").toLocalDateTime());
		member.setId(rs.getLong("ID"));
		return member;
	}
	
}

public class MemberDao {

	private JdbcTemplate jdbcTemplate;

	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Member selectByEmail(String email) {
		List<Member> results = jdbcTemplate.query(
			"select * from MEMBER where EMAIL = ?", 
			new MemberRowMapper(), email );
		return results.isEmpty() ? null : results.get(0);
	}

	public List<Member> selectAll() {
		List<Member> results = jdbcTemplate.query(
			"select * from MEMBER", 
			new MemberRowMapper());
		return results;
	}
	
	public int count() {
		Integer count = jdbcTemplate.queryForObject(
			"select count(*) from MEMBER",Integer.class
			);
		return count;
	}
	
	public void insert(Member member) {
	}

	public void update(Member member) {
	}


}
