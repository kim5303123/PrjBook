/**
 * 
 */
package prj.com.book.service;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import prj.com.book.vo.BookVo;

/**
 * @Author : TeamPrjBook
 * @Date : 2024. 11. 27.
 * 
 *       DAO(Data Access Object)는 DB를 사용해 데이터를 조화하거나 조작하는 기능을 전담하도록 만든 오브젝트
 */
public class BookDao {

	private Scanner scanner;

	public BookDao(Scanner scanner) {
		this.scanner = scanner;
	}

public void membership() {
        
        System.out.println("\n회원 정보 등록 화면입니다.");
        System.out.print("등록할 회원번호를 입력하세요> ");
        String id = scanner.nextLine();
        BookDaoImpl dao = new BookDaoImpl();
        ResultSet rs = dao.checkRsNum(id);
        try {
            if (rs.next() == true) { // 검색 결과가 있다.
                System.out.println(id + "는 이미 등록되어 있습니다!");
                System.out.println("다른 번호를 입력해주세요!");
            } else { // 검색 결과가 없다 : 사용 가능 → 추가 정보 입력받는다.
                System.out.print("비밀번호를 입력하세요> ");
                String pw = scanner.nextLine();
                System.out.print("이름을 입력하세요> ");
                String name = scanner.nextLine();
                System.out.print("주소를 입력하세요> ");
                String address = scanner.nextLine();
                System.out.print("핸드폰번호를 입력하세요> ");
                String phone = scanner.nextLine();
                System.out.print("이메일을 입력하세요> ");
                String email = scanner.nextLine();
                
                
                BookVo dto = new BookVo(id, pw, name, address, phone, email);
                int succ = dao.insertBook(dto);
                if (succ > 0) {
                    System.out.println(id + "회원 정보가 등록되었습니다.");
                } else {
                    System.out.println(id + "회원 정보가 등록 실패하였습니다.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("bookInsertInput() Exception!!!");
        }
        
    }
	
	// 도서 정보 등록 서브 화면 
	public void bookInsertInput() { 
 
		System.out.println("\n도서 정보 등록 화면입니다.");
		System.out.print("등록할 도서의 번호를 입력하세요> ");
		String bookCd = scanner.nextLine(); 
		BookDaoImpl dao = new BookDaoImpl();
		ResultSet rs = dao.checkNum(bookCd);
		try {
			if (rs.next() == true) { // 검색 결과가 있다.
				System.out.println(bookCd + "번 도서는 이미 등록되어 있습니다!");
				System.out.println("다른 번호를 입력해주세요!");
			} else { // 검색 결과가 없다 : 사용 가능 → 추가 정보 입력받는다.
				System.out.print("제목을 입력하세요> ");
				String bookName = scanner.nextLine();
				System.out.print("출판사를 입력하세요> ");
				String bookAuthor = scanner.nextLine();
				System.out.print("저자를 입력하세요> ");
				String bookPublisher = scanner.nextLine();
//				System.out.print("단가를 입력하세요> ");
//				int cost = Integer.parseInt(scanner.nextLine());
//				System.out.println(num + "\t" + title + "\t" + company + "\t" + name + "\t" + cost); // 제대로 나오는지 확인하는 라인

//				BookVo dto = new BookVo(num, title, company, name, cost);
				BookVo dto = new BookVo(bookCd, bookName, bookAuthor, bookPublisher);

				int succ = dao.insertBook(dto);
				if (succ > 0) {
					System.out.println(bookCd + "번 도서 정보가 등록되었습니다.");
				} else {
					System.out.println(bookCd + "번 도서 정보가 등록 실패하였습니다.");
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();     
			System.out.println("bookInsertInput() Exception!!!");
		}
	} // bookInsertInput()

	// 테스트
	// 도서도서
	// 도서 목록 보기
	// 도서
	public void bookSearchAllInput() {
		System.out.println("\n도서 목록 보기 화면입니다.");
		ArrayList<BookVo> list = new ArrayList<>();
		BookDaoImpl dao = new BookDaoImpl();
		list = dao.selectBookAll(list);
		dao.display(list);
	} // bookSearchAllInput()

	// 도서 제목 검색
	public void bookSearchTitleInput() {
		System.out.println("\n도서 제목 검색 화면입니다.");
		System.out.print("검색할 도서의 제목을 입력하세요> ");
		String searchTitle = scanner.nextLine();
		ArrayList<BookVo> list = new ArrayList<>();
		BookDaoImpl dao = new BookDaoImpl();
		dao.selectBookTitle(list, searchTitle);
		dao.display(list);
	} // bookSearchTitleInput()

	// 도서 정보 삭제
	public void bookDeleteInput() {
		System.out.println("\n도서 정보 삭제 화면입니다.");
		System.out.print("삭제할 도서의 번호를 입력하세요> ");
		String bookCd = scanner.nextLine();
		BookDaoImpl dao = new BookDaoImpl();
		ResultSet rs = dao.checkNum(bookCd);

		try {
			if (rs.next() != true) { // 해당 번호가 없으면
				System.out.println(bookCd + "번 도서는 등록되어 있지 않습니다.");
			} else {
				int succ = dao.deleteBook(bookCd);
				if (succ > 0) {
					System.out.println(bookCd + "번 도서정보가 삭제되었습니다.");
				} else {
					System.out.println(bookCd + "번 도서정보 삭제에 실패했습니다.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("bookDeleteInput() Exception!!!");
		}
	} // bookDeleteInput()

	// 도서 정보 수정
	public void bookUpdateInput() {

		System.out.println("\n도서 정보 수정 화면입니다.");
		System.out.print("수정할 도서의 번호를 입력하세요> ");
		String bookCd = scanner.nextLine();
		BookDaoImpl dao = new BookDaoImpl();
		ResultSet rs = dao.checkNum(bookCd);
		try {
			if (rs.next() != true) {
				System.out.println(bookCd + "번 도서는 등록되어 있지 않습니다.");
			} else {
				System.out.print("수정할 도서의 제목을 입력하세요> ");
				String bookName = scanner.nextLine();
				System.out.print("수정할 도서의 출판사를 입력하세요> ");
				String bookAuthor = scanner.nextLine();
				System.out.print("수정할 도서의 저자를 입력하세요> ");				
				String bookPublisher = scanner.nextLine();
				BookVo dto = new BookVo(bookCd, bookName, bookAuthor, bookPublisher);
				
				int succ = dao.updateBook(dto);
				if (succ > 0) {
					System.out.println(bookCd + "번 도서정보가 수정되었습니다.");
				} else {
					System.out.println(bookCd + "번 도서정보 수정에 실패했습니다.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("bookUpdateInput() Exception!!!");
		}
	} // bookUpdateInput()
	
	// 도서 목록 대여
		public void bookBorrowInput() {
			System.out.println("도서 대여 화면입니다."); 
			System.out.print("회원님의 아이디를 입력해주세요> ");
			String userId = scanner.nextLine();
			ArrayList<BookVo> list = new ArrayList<>();
			BookDaoImpl dao = new BookDaoImpl();
			ResultSet rs = dao.checkId(userId);
			list = dao.selectBookAll(list);
			dao.display(list);
			System.out.print("\n도서 목록을 참고하시거나 빌리고 싶으신 도서를 검색해주세요." 
					+ "\n이미 도서번호를 알고 계신다면 엔터를 입력해주세요."
					+ "\n>");
			BookDao input = new BookDao(scanner);
			System.out.print("도서 번호를 입력해주세요> ");
			String bookCd = scanner.nextLine();
			ResultSet rs1 = dao.checkNum(bookCd);
			try {
				if(rs1.next() != true) { // 해당 번호가 없으면
					System.out.println(bookCd + "번 회원은 등록되어 있지 않습니다.");
				} else { //	해당 번호가 있으면 
					BookVo dto = new BookVo(userId, bookCd);
					
					
					int succ = dao.borrowBook(dto);
					if(succ > 0) {
						System.out.println(bookCd + "번 도서를 대여하였습니다!");
					} else {
						System.out.println(bookCd + "번 도서는 이미 다른 사람이 대여하였습니다.");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("bookDeleteInput() Exception!!!");
			}
			
		}
//		도서 반납 화면
		public void bookReturnInput() {
			System.out.println("도서 반납 화면입니다."); 
			System.out.print("회원님의 아이디를 입력해주세요> ");
			String userId = scanner.nextLine();
			BookDaoImpl dao = new BookDaoImpl();
			ResultSet rs2 = dao.checkId(userId);
			System.out.print("\n반납하실 도서의 번호를 입력해주세요.>");
			BookDao input = new BookDao(scanner);
			String bookCd = scanner.nextLine();
			ResultSet rs3 = dao.checkNum(bookCd);
			try {
				if(rs2.next() != true) { // 해당 번호가 없으면
					System.out.println(userId + "번 회원은 등록되어 있지 않습니다.");
				} else { //	해당 번호가 있으면 
					BookVo dto = new BookVo(userId, bookCd);
					int succ = dao.returnBook(dto);
					if(succ > 0) {
						System.out.println(bookCd + "번 도서를 반납하였습니다!");
					} else {
						System.out.println(bookCd + "번 도서는 대여하신 도서가 아닙니다.");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("bookDeleteInput() Exception!!!");
			}
			
		}
		// 도서 대여 예약확인
	    public void bookReservation() {
	        System.out.println("\n도서 대여예약 신청확인 화면입니다");       
	        System.out.print("회원번호를 입력하세요> ");
	        String id = scanner.nextLine();
	        BookDaoImpl dao = new BookDaoImpl();
	        ResultSet rs = dao.checkRsNum(id);
	        try {
	            if(rs.next() != true) { 
	                System.out.println("입력하신 회원 " + id + "는 등록되어 있지 않습니다.");
	            } else {
	                System.out.print("대여예약할 도서의 번호를 입력하세요> ");     
	                String bookCd = scanner.nextLine();
	                System.out.print("대여예약할 도서의 수량을 입력하세요> ");
	                String reservationCnt = scanner.nextLine();
	                String reserveDateBorrow = rs.getString(5);
	                
	                dao.orderBook(rs, reservationCnt, bookCd);
	                
	                String title = rs.getString("book_Name");
//	              
	                String msg = "\n예약하신 도서 명은 " + title + "이고, ";
	                msg = "\n예약하신 회원 명은 " + id + "이고, ";
	                
	                msg += "대여예약 수량은 " + reservationCnt + "권 입니다.";
	                
	                msg += "대여 하실 날짜는 " + reserveDateBorrow + "일 까지입니다.";
	                
	                
	                System.out.println(msg);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("bookOrderInput() Exception!!!");
	        }
	}// bookOrderInput()
	    
	    // 도서 대여예약 등록 
	        public void bookReservationInput() {
	            
	            LocalDate now = LocalDate.now();
	            LocalDate now2 = now.plusDays(2);
	            
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");          
	            
	            System.out.println("\n도서 대여예약 등록 화면입니다.");
//	          System.out.print("등록할 도서의 번호를 입력하세요> ");
//	          String bookCd = scanner.nextLine();
	            System.out.print("회원번호를 입력하세요> ");
	            String id = scanner.nextLine();
	            BookDaoImpl dao = new BookDaoImpl();
	            ResultSet rs = dao.checkRsNum(id);
	            try {
	                if (rs.next() == true) { // 검색 결과가 있다.
	                    System.out.println(id + "회원은 이미 예약하셨습니다!");
	                    System.out.println("다른 번호를 입력해주세요!");
	                } else { // 검색 결과가 없다 : 사용 가능 → 추가 정보 입력받는다.
	                    System.out.print("도서번호을 입력하세요> ");
	                    String bookCd = scanner.nextLine();
	                    System.out.print("도서제목을 입력하세요> ");
	                    String bookName = scanner.nextLine();
	                    String resrveDate = now.format(formatter);
	                    String reserveDateBorrow = now2.format(formatter);
	                    BookVo dto = new BookVo(id, bookCd, bookName, resrveDate, reserveDateBorrow);
	                    int succ = dao.reserveBorrow(dto);
	                    if (succ > 0) {
	                        System.out.println(bookCd + "번 도서 정보가 예약되었습니다.");
	                    } else {
	                        System.out.println(bookCd + "번 도서 정보가 예약 실패하였습니다.");
	                    }
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	                System.out.println("bookInsertInput() Exception!!!");
	            }
	        } // bookInsertInput()
	    
		}// BookDao

		


