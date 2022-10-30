package edu.kh.rental.member.model.service;


import static edu.kh.rental.common.JDBCTemplate.close;
import static edu.kh.rental.common.JDBCTemplate.commit;
import static edu.kh.rental.common.JDBCTemplate.getConnection;
import static edu.kh.rental.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import edu.kh.rental.member.model.dao.RentalDAO;
import edu.kh.rental.member.model.vo.Client;
import edu.kh.rental.member.model.vo.Place;
import edu.kh.rental.member.model.vo.Reservation;

public class ClientService {

	RentalDAO dao = new RentalDAO();
	
	/**중복체크
	 * @param memberId
	 * @return result
	 * @throws Exception
	 */
	public int duplicateCheck(String memberId)throws Exception{
		Connection conn = getConnection();
		int result = dao.duplicateCheck(conn,memberId);
		close(conn);
	
		return result;	
		}

	/**회원가입
	 * @param signUpMember
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Client signUpMember)throws Exception{
		Connection conn = getConnection();
		
		int result = dao.signUp(conn,signUpMember);
		
		if(result > 0)commit(conn);
		else rollback(conn);
	
		close(conn);
		return result;
	}

	/**로그인
	 * @param mem
	 * @return LoginMember
	 * @throws Exception
	 */
	public Client login(Client mem)throws Exception{
		Connection conn = getConnection();
		Client loginMember = dao.login(conn,mem);			
		close(conn);
		return loginMember;
	}

	/**지점 조회
	 * @return listUp
	 * @throws Exception
	 */
	public List<Place> select()throws Exception{
		Connection conn = getConnection();
		List<Place> listUp= dao.select(conn);			
		close(conn);
		
		return listUp;
		}

	/**예약불가 시간 조회
	 * @param num
	 * @return scheeduleList
	 * @throws Exception
	 */
	public List<Reservation> scheduleList(int num)throws Exception{
		Connection conn = getConnection();
		List<Reservation> scheeduleList = dao.scheeduleList(conn,num);			
		close(conn);
		
		return scheeduleList;

	}

	/**예약 신청
	 * @param ctLogin
	 * @param num2
	 * @return result
	 * @throws Exception
	 */
	public int resUpdate(Reservation res)throws Exception{
		int result =0;
		Connection conn = getConnection();
		result = dao.resUpdate(conn,res);
		
		if(result > 0)commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	
	/**예약조회
	 * @param ctLogin
	 * @return
	 * @throws Exception
	 */
	public List<Reservation> selectMy(Client ctLogin)throws Exception{
		Connection conn = getConnection();
		
		List<Reservation> res = dao.selectMy(conn,ctLogin);
		
		close(conn);
		
		return res;
	}

	
}
