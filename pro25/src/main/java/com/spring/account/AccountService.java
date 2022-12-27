package com.spring.account;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/*서비스 클래스의 메서드는 단위 기능을 수행하므로 트랜잭션 애너테이션을 적용해 메서드별로 트랜잭션 적용*/

//@Transactional을 이용해 AccountService 클래스의 모든 메서드를 트랜잭션 적용
@Transactional(propagation=Propagation.REQUIRED)
public class AccountService {
	private AccountDAO accDAO;
	
	public void setaccDAO(AccountDAO accDAO) {
		this.accDAO = accDAO;
	} //속성 accDAO에 빈을 주입하기 위해 setter 구현
	
	public void sendMoney() throws Exception{
		accDAO.updateBalance1();
		accDAO.updateBalance2();
	}//sendMoney() 메서드 호출 시 accDAO의 두 개의 SQL문 실행
}
