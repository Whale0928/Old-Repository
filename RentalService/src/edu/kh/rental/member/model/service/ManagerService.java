package edu.kh.rental.member.model.service;

import edu.kh.rental.member.model.dao.ManagerDAO;
import edu.kh.rental.member.model.vo.Client;
import edu.kh.rental.member.model.vo.Manager;
import static edu.kh.rental.common.JDBCTemplate.close;
import static edu.kh.rental.common.JDBCTemplate.commit;
import static edu.kh.rental.common.JDBCTemplate.getConnection;
import static edu.kh.rental.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;


public class ManagerService {
	
	Manager manager = new Manager();
	ManagerDAO dao = new ManagerDAO();
	
	/**로그인 메서드
	 * @param mem
	 * @return manager
	 * @throws Exception
	 */
	public Manager login(Manager mem)throws Exception{
		Connection conn = getConnection();
		
		Manager manager = dao.login(conn,mem);
		
		close(conn);
		return manager;
	}

	/**관리중인 지점 조회
	 * @param managerLogin
	 * @return
	 * @throws Exception
	 */
	public List<Manager> selectShop(Manager managerLogin)throws Exception{
		Connection conn = getConnection();
		
		List<Manager> selectShop = dao.selectShop(conn,managerLogin);
		
		close(conn);
		
		return selectShop;
	}

	/**관리중인 지점 고객 명단 조회
	 * @param managerNo
	 * @return ctList
	 * @throws Exception
	 */
	public List<Client> selectClient(int managerNo)throws Exception{
		List<Client> ctList = null;
		
		Connection conn = getConnection();
		
		ctList = dao.selectClient(conn,managerNo);
		
		close(conn);
		
		return ctList;
	}

	/**예약 마감 세팅
	 * @param placeNum
	 * @return result
	 * @throws Exception
	 */
	public int endSet(int placeNum)throws Exception{
		Connection conn  = getConnection();
		
		int result = dao.endSet(conn,placeNum);
		
		if(result>0)commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}
}
