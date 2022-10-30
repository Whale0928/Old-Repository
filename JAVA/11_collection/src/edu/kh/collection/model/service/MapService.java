package edu.kh.collection.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.kh.collection.model.vo.Member;

public class MapService {

	// Map : key 와 value 한 쌍이 데이터가 되어 이를 모아둔 객체.

	// key를 모아두면 set의 특징 (중복 x)
	// value를 모아두면 List의 특징 (중복 o)

	public void ex1() {

		// HashMap : Map의 자식 클래스중 가장 대표되는 Map

		Map<Integer, String> map = new HashMap<Integer, String>();

		// Map.put(Integer Key , String Value) :추가 add와 같은 역활
		map.put(1, "홍길동");
		map.put(2, "고길동");
		map.put(3, "촤길동");
		map.put(4, "남길동");
		map.put(5, "북길동");

		// 키의 중복 덮어쓰기
		map.put(1, "홍홍홍");

		// value의 중복
		map.put(5, "북길동");

		System.out.println(map);
	}

	public void ex2() {
		// Map사용예제

		// vo (값 저장용 객체)는 특정 데이터 묶음의 재사용이 많은 경우 주로 사용
		// - 그러나 재사용이 적은 vo는 오히려 코드 낭비이다.
		// - Map을 이용해서 vo와 비슷한 코드를 작성할 수 있다

		// 1) VO 버전
		Member mem = new Member();
		// 값 세팅
		mem.setId("user");
		mem.setPw("1234");
		mem.setAge(12);

		System.out.println(mem.getId());
		System.out.println(mem.getPw());
		System.out.println(mem.getAge());

		System.out.println("----------------");

		// 2) Map버전
		Map<String, Object> map = new HashMap<String, Object>();
		// Value가 Object 타입 == 어떠한 객체는 Value에 들어올 수 있다.

		// 값 세팅
		map.put("id", "user");
		map.put("pw", "1234");
		map.put("age", 20);
		// int = > Integer(AutoBoxing) = > 대입 => Object

		System.out.println(map.get("id"));
		System.out.println(map.get("pw"));
		System.out.println(map.get("age"));

		// **Map에 저장된 데이터 순차적으로 접근하기
		// Map에서 키만 모아두면 set의 특징을 가진다.
		// set의 중복이 없다
		// -> 이를 활용할 수 있도록 Map에서 KeySet() 메서드를 제공
		// -> key만 모아서 Set으로 반환

		Set<String> set = map.keySet();
		System.out.println(set);
		System.out.println(" ===============");
		for (String key : set) {
			System.out.println(map.get(key));
			// key가 반복될 때 마다 변경
			// 변경된 key에 맞는 map의 value가 출력
		} 
		//map에 저장된 데이터가 많거나 
		//어떤 key가 있는지 불문명 할 때
		//또는 map에 있는 저장된 모든 데이터에 접근해야 할때 
		//keySet() + 향상된 for문 코드를 사용한다.

	}
	
	public void ex3() {
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		for(int i=0; i<10; i++) {
			Map<String,Object> map = new HashMap<String, Object>();
			
			//Map의 데이터 추가.
			map.put("id", "user0"+i);
			map.put("pw", "pass0"+i);
			
			//Map을 List에 추가
			list.add(map);
		}	
		//for문 종료시 list에는 10개의 Map객체가 추가 되어 있다
		
		//List에 저장된 Map에서 Key "id"인 경우의 value를 모두 출력
		
		//향상된 forans
		for(Map<String,Object> temp : list) {
			System.out.println(temp.get("pw"));
		}
	}
}
