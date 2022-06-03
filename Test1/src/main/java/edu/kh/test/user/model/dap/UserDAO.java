package edu.kh.test.user.model.dap;

import static edu.kh.test.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import edu.kh.test.user.model.vo.User;

public class UserDAO {

	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;

	/**
	 * 유저 검색 dao
	 * @param conn
	 * @param userNo
	 * @return user
	 * @throws Exception
	 */
	public User selectUser(Connection conn, int userNo) throws Exception {
		User user = null;

		try {
			String sql = "SELECT * FROM TB_USER WHERE USER_NO = '" + userNo + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				user = new User();
				user.setUserNo(rs.getInt("USER_NO"));
				user.setUserId(rs.getString("USER_ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setUserAge(rs.getInt("USER_AGE"));
			}
		} finally {
			close(rs);
			close(stmt);
		}
		return user;
	}
}
