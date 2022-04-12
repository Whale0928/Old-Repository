package edu.kh.rental.member.model.dao;

import static edu.kh.rental.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.rental.member.model.vo.Client;
import edu.kh.rental.member.model.vo.Manager;

public class ManagerDAO {
	
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private Properties prop = null;

	public ManagerDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("manager-sql.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**로그인
	 * @param conn
	 * @param mem
	 * @return manager
	 * @throws Exception
	 */
	public Manager login(Connection conn, Manager mem)throws Exception {
		Manager manager = null;
		try {
			String sql = prop.getProperty("login");
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, mem.getManagerId());
		pstmt.setString(2, mem.getManagerPw());
		
		rs = pstmt.executeQuery();
					
		if(rs.next()) {
			manager = new Manager();
			
			manager.setManagerNo(rs.getInt("MANAGER_NO"));
			manager.setManagerId(rs.getString("MANAGER_ID"));
			manager.setManagerPw(rs.getString("MANAGER_PW"));
			manager.setPhone(rs.getString("PHONE"));
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return manager;

	}

	/**관리중인 지점 조회
	 * @param conn
	 * @param managerLogin
	 * @return shopList
	 * @throws Exception
	 */
	public List<Manager> selectShop(Connection conn, Manager managerLogin)throws Exception{
		List<Manager> shopList = new ArrayList<Manager>();
		
		try {
			String sql = prop.getProperty("selectShop");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, managerLogin.getManagerNo());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Manager manager = new Manager();
				manager.setPlaceNo(rs.getInt("PLACE_NO"));
				manager.setPlaceName(rs.getString("PLACE_NM"));
				manager.setPlaceAddress(rs.getString("PLACE_ARD"));
				
				shopList.add(manager);
				}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return shopList;
	}


	/**관리중인 지점의 고객 조회
	 * @param conn
	 * @param managerNo
	 * @return ctList
	 * @throws Exception
	 */
	public List<Client> selectClient(Connection conn, int managerNo)throws Exception{
		List<Client> ctList = new ArrayList<Client>();
		
		try {
			String sql = prop.getProperty("selectClient");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, managerNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Client temp = new Client();
				temp.setClientNo(rs.getInt("CLIENT_NO"));
				temp.setName(rs.getString("CLIENT_NM"));
				temp.setResDate(rs.getInt("RES_DATE"));
				temp.setPlaceName(rs.getString("PLACE_NM"));
				ctList.add(temp);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return ctList;
	}

	/**예약 종료 세팅
	 * @param conn
	 * @param placeNum
	 * @return result
	 * @throws Exception
	 */
	public int endSet(Connection conn,int placeNum)throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("endSet");
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, placeNum);
			
			result = pstmt.executeUpdate();
					
		}finally {
			close(pstmt);
		}
		
		return result;
	}

}
