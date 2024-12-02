/**
 * 
 */
package prj.com.book.main;

import java.sql.SQLException;
import java.util.Scanner;

import prj.com.book.service.BookDao;
import prj.com.book.service.BookDaoImpl;
import prj.com.book.service.NewConsole;
import prj.com.book.vo.BookVo;

/**
 * @Author : TeamPrjBook
 * @Date : 2024. 11. 27.
 */
public class BookMainApp {

	/**
	 * @Author : TeamPrjBook
	 * @Date : 2024. 11. 27.
	 * @Method : main
	 * @return : void
	 */

	public static void st() {
		
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
//			메인메뉴 호출
			menuPrint();
			System.out.print("메뉴를 입력하세요> ");
			String menu = scanner.nextLine();
			BookDao input = new BookDao(scanner);
			
			if(menu.equalsIgnoreCase("i")) {
				input.bookInsertInput();
			} else if (menu.equalsIgnoreCase("a")) {
				input.bookSearchAllInput();
			} else if (menu.equalsIgnoreCase("d")) {
				input.bookDeleteInput();
			} else if (menu.equalsIgnoreCase("u")) {
				input.bookUpdateInput();
			} else if (menu.equalsIgnoreCase("s")) {
				input.bookSearchTitleInput();
			} else if (menu.equalsIgnoreCase("b")) {
				input.bookBorrowInput();
			} else if (menu.equalsIgnoreCase("r")) {
				input.bookReturnInput();
			}else if (menu.equalsIgnoreCase("o")) {                
                input.bookReservationInput();
			}else if (menu.equalsIgnoreCase("BR")) {
                input.bookSearchAllInput();
                input.bookReservation();
			} else if(menu.equalsIgnoreCase("e")) {
				System.out.print("정말 종료하시겠습니까(Y/N)> ");
				String isExit = scanner.nextLine();
				if(isExit.equalsIgnoreCase("y")) {
					System.out.println("도서 관리 프로그램을 종료합니다.");
					BookDaoImpl dao = new BookDaoImpl();
					dao.dbClose();
					System.exit(0);
					break;
				} else if (isExit.equalsIgnoreCase("n")) {
					System.out.println("취소하셨습니다.");
					continue;
				} else {
					System.out.println("잘못 입력하셨습니다.");
					continue;
				} // if, else if, else
			} else {
				System.out.println("잘못 입력하셨습니다.");
				continue;
			} // if else
		} // while()
	}

//	로그인
	public static void sten() throws ClassNotFoundException, SQLException {
		
		
		NewConsole console = new NewConsole();
		
		EXIT:
		while(true) {
			int menu = console.inputNoticeMenu(); // 입력창 
			switch (menu) {
			case 1: //회원가입				
				console.insertMenu();
				break;
			case 2: // 로그인
				console.inputLoginMenu();
				st();
				break;
			case 3: // 종료
				System.out.println("종료되었습니다. BYE ");
				break EXIT;
			default :
				System.out.println("<< 메뉴는 1~3사이 숫자만 입력 가능합니다.");
				break;
			}
		}
	}
	
	
	public static void menuPrint() {
		System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯ 도서 관리 ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
		System.out.println("도서 정보 등록 : I");
		System.out.println("도서 목록 보기 : A");
		System.out.println("도서 제목 검색 : S");
		System.out.println("도서 정보 삭제 : D");
		System.out.println("도서 정보 수정 : U");
		System.out.println("도서 목록 대여 : B");
		System.out.println("도서 목록 반납 : R");
		System.out.println("도서 주문 예약 : O");
		System.out.println("도서 목록 예약확인 : BR");
		System.out.println("도서 관리 종료 : E");
		System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		sten();

		
	} // main()
	
}
