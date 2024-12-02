package prj.com.book.service;

import java.sql.SQLException;
import java.util.Scanner;

import prj.com.book.vo.BookVo;

public class NewConsole {
	/**
	 * @Author : 202-12
	 * @Date   : 2024. 12. 2.
	 */

	private BookDaoImpl dao;
	
	public NewConsole() {
		dao = new BookDaoImpl();
	}
	
	public int inputNoticeMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.print("⎯⎯⎯⎯⎯⎯⎯⎯⎯ 1.회원가입 / 2.로그인 / 3. 종료 >> ");		
		int menu = sc.nextInt();
		return menu;
	}
	
	public void insertMenu() throws ClassNotFoundException, SQLException{
		Scanner sc = new Scanner(System.in);
		System.out.println("회원가입을 위해 필요한 정보를 입력하세요.");
		System.out.print("userId = ");
		String userId = sc.nextLine();
		System.out.print("pw = ");
		String pw = sc.nextLine();
		System.out.print("name = ");
		String name = sc.nextLine();
		System.out.print("address = ");
		String address = sc.nextLine();
		System.out.print("phone = ");
		String phone = sc.nextLine();
		System.out.print("email = ");
		String email = sc.nextLine();
		
		//입력받은 데이터 member에 저장.
		BookVo member = new BookVo(userId, pw, name, address, phone, email);
		
		dao.insert(member); //member를 가지고 insert
		
	}
	
	public void inputLoginMenu() throws ClassNotFoundException, SQLException {
			Scanner sc = new Scanner(System.in);
			
			boolean b = true;
			while(b) {
			System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯ 회원 로그인 ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
			System.out.println(" 로그인을 위한 회원정보를 입력하세요 >> ");
			System.out.print(" id = ");
			String id = sc.nextLine();
			System.out.print(" password = ");
			String password = sc.nextLine();
			
			// id, password 값 가지고 DB에 저장된 값과 비교해준다.
			BookVo vo = dao.loginData(id,password);
			
			if(vo == null) { // vo 가 비어있을시 -> 일치하는 값 없을때.
				System.out.println("아이디나 비밀번호가 다릅니다. 다시 입력해주세요.");
				continue;
			}else { // vo에 id,password 매칭하는 값이 있을시?
				System.out.println("로그인 성공! 환영합니다.");	
				b = false;
			}	
		}
	}

}	//	NewConsole()



