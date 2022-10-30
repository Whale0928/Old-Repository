package edu.kh.rental.member.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.rental.member.model.vo.Client;
import edu.kh.rental.member.model.vo.Place;
import edu.kh.rental.member.model.vo.Reservation;

import static edu.kh.rental.common.JDBCTemplate.close;

public class RentalDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private Properties prop = null;

	public RentalDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("client-sql.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** ID 중복 체크
	 * @param conn
	 * @param memberId
	 * @return result
	 * @throws Exception
	 */
	public int duplicateCheck(Connection conn, String memberId)throws Exception{
				int result = 0;

				try {
					String sql = prop.getProperty("duplicateCheck");
					
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1,memberId);
					
					rs = pstmt.executeQuery(); 
					
					if(rs.next()) {
					result = rs.getInt(1);
					}
				}finally {
					close(rs);
					close(pstmt);
				}
				return result;
	}

	/**회원가입
	 * @param conn
	 * @param signUpMember
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Connection conn, Client signUpMember)throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("signUp");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, signUpMember.getClientId());
			pstmt.setString(2, signUpMember.getClientPw());
			pstmt.setString(3, signUpMember.getName());
			pstmt.setString(4, signUpMember.getPhone());
				
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
	
		return result;
	}

	/**로그인
	 * @param conn
	 * @param mem
	 * @return loginMember
	 * @throws Exception
	 */
	public Client login(Connection conn, Client mem)throws Exception{
		Client loginMember = null;
			
		try {
				String sql = prop.getProperty("login");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mem.getClientId());
			pstmt.setString(2, mem.getClientPw());
			
			rs = pstmt.executeQuery();
						
			if(rs.next()) {
				loginMember = new Client();
				loginMember.setClientNo(rs.getInt("CLIENT_NO"));
				loginMember.setClientId(rs.getString("CLIENT_ID"));
				loginMember.setClientPw(rs.getString("CLIENT_PW"));
				loginMember.setName(rs.getString("CLIENT_NM"));
				loginMember.setPhone(rs.getString("PHONE"));
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return loginMember;
	}
	
	/**지점조회
	 * @param conn
	 * @return listUp
	 * @throws Exception
	 */
	public List<Place> select(Connection conn)throws Exception{
		List<Place> listUp = new ArrayList<Place>();
		
		try {
			String sql = prop.getProperty("select");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
						
			while(rs.next()) {
				Place temp = new Place();
				temp.setPlaceNo(rs.getInt("PLACE_NO"));
				temp.setPlaceName(rs.getString("PLACE_NM"));
				temp.setPlaceAddress(rs.getString("PLACE_ARD"));
				listUp.add(temp);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return listUp;
	}

	/**예약 불가 시간 조회
	 * @param conn
	 * @param num
	 * @return scheeduleList
	 * @throws Exception
	 */
	public List<Reservation> scheeduleList(Connection conn,int num)throws Exception{
		List<Reservation> scheeduleList = new ArrayList<Reservation>();
		
		try {
			String sql = prop.getProperty("selectRes");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,num);
			
			rs = pstmt.executeQuery();
			
						
			while(rs.next()) {
				Reservation temp = new Reservation();
				temp.setResDate(rs.getInt("RES_DATE"));
				scheeduleList.add(temp);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return scheeduleList;
	}

	/**예약 신청
	 * @param conn
	 * @param res
	 * @return result
	 * @throws Exception
	 */
	public int resUpdate(Connection conn,Reservation res)throws Exception{
		int result = 0;
		try {
			String sql = prop.getProperty("resUpdate");
			
			pstmt = conn.prepareStatement(sql);
			
			//지역 번호 , 고객번호 , 예약일
			pstmt.setInt(1, res.getPlaceNo()); 
			pstmt.setInt(2, res.getClientNo()); 
			pstmt.setInt(3, res.getResDate()); 
		
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}

	/**예약조회
	 * @param conn
	 * @param ctLogin
	 * @return res
	 * @throws Exception
	 */
	public List<Reservation> selectMy(Connection conn,Client ctLogin)throws Exception{
		
		List<Reservation> res = new ArrayList<Reservation>();
		
		try {
			String sql = prop.getProperty("selectMy");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, ctLogin.getClientNo());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Reservation temp = new Reservation();
						
				
				temp.setPlaceName(rs.getString("PLACE_NM"));
				temp.setClientName(rs.getString("CLIENT_NM"));
				temp.setResDate(rs.getInt("RES_DATE"));
				res.add(temp);
			}
			
		}finally{
			close(rs);
			close(pstmt);
		}
		
		return res;
	}
	
	
	

}
