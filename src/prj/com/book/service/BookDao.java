/**
 * 
 */
package prj.com.book.service;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
				
				BookVo dto = new BookVo(bookCd, bookName, bookAuthor, bookPublisher);

				int succ = dao.insertBook(dto);
				if (succ > 0) {
					System.out.println(bookCd + "번 도서 정보가 등록되었습니다.");
				} else {
					System.out.println(bookCd + "번 도서 정보가 등록 실패하였습니다.");
				}
			} 
		} catch (Exception e) {
			System.out.println("도서 정보 등록 서브 화면 정보를 확인해주세요!!!");     
			System.out.println("bookInsertInput() Exception!!!");
		}
	} // bookInsertInput()


	// 도서 목록
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
			System.out.println("도서 정보 삭제를 확인해주세요!!!");
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
			System.out.println("도서 정보 수정을 확인해주세요!!!");
			System.out.println("bookUpdateInput() Exception!!!");
		}
	} // bookUpdateInput()
	
	// 도서 목록 대여
    public void bookBorrowInput() {
        System.out.println("도서 대여 화면입니다."); 
        ArrayList<BookVo> list = new ArrayList<>();
        BookDaoImpl dao = new BookDaoImpl();
        list = dao.selectBookAll(list);
        dao.display(list);  //  도서 전체 목록 보여주기 
        System.out.print("\n빌리고 싶으신 도서의 제목을 검색해주세요.");
            try {
                while(true) { // 도서 제목이 올바르게 입력될 때까지 입력 반복
                String searchTitle = scanner.nextLine();
                ResultSet rs0 = dao.checkBook(searchTitle);
                ArrayList<BookVo> listSearch = new ArrayList<>();
                if (rs0.next() != true) {   // 해당 번호가 없으면 
                    System.out.println("해당하는 도서는 존재하지 않습니다.");
                    System.out.println("다시 입력해주세요");
                    continue;
                } else { // 도서 제목이 올바르게 입력되면 해당 도서 정보 출력 
                dao.selectBookTitle(listSearch, searchTitle);
                dao.display(listSearch);
                break;
                }
            }
            } catch (Exception e) {
//                e.printStackTrace();
                System.out.println("도서 목록 대여를 확인해주세요(첫번째)!!!");
                System.out.println("bookBorrowInput() Exception!!!");
            } 
        System.out.print("\n회원님의 아이디를 입력해주세요> ");
        String userId = scanner.nextLine(); //  아이디 입력 받기 
        ResultSet rs = dao.checkId(userId);
        try {
            if(rs.next() != true) { // 해당 아이디가 없으면
                System.out.println(userId + "번 회원은 등록되어 있지 않습니다.");
                System.out.println("초기 화면으로 돌아갑니다.");
                bookBorrowInput();
            } else {}
        } catch (Exception e) {
//            e.printStackTrace();
        	System.out.println("도서 목록 대여를 확인해주세요(두번째)!!!");
            System.out.println("bookBorrowInput() Exception!!!");
        } 
         
        BookDao input = new BookDao(scanner);
        System.out.print("\n도서 번호를 입력해주세요> ");
        String bookCd = scanner.nextLine(); //  도서 번호 입력 받기 
        ResultSet rs1 = dao.checkNum(bookCd);
        try {
            if(rs1.next() != true) { // 해당 도서 번호가 없으면
                System.out.println(bookCd + "해당 도서는 등록되어 있지 않습니다.");
            } else {
                BookVo dto = new BookVo(userId, bookCd);
                int succ = dao.borrowBook(dto);
                if(succ > 0) {
                    String today = null;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.WEEK_OF_MONTH, 2); 
                    today = sdf.format(cal.getTime()); // 2주 뒤 날짜 출력
                    System.out.println(bookCd + "번 도서를 대여하였습니다.");
                    System.out.println("반납 기간은" + today + "까지입니다!");
                } else {
                    System.out.println(bookCd + "번 도서는 이미 다른 사람이 대여하였습니다.");
                }
            }
        } catch (Exception e) {
                System.out.println("도서 목록 대여를 확인해주세요(세번째)!!!");
                System.out.println("bookBorrowInput() Exception!!!");
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
				System.out.println("도서 반납 화면을 확인해주세요!!!");
				System.out.println("bookReturnInput() Exception!!!");
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
	            System.out.println("도서 대여 예약확인을 확인해주세요!!!");
	            System.out.println("bookReservation() Exception!!!");
	        }
	}// bookOrderInput()
	    
	    // 도서 대여예약 등록 
	        public void bookReservationInput() {
	            
	            LocalDate now = LocalDate.now();
	            LocalDate now2 = now.plusDays(2);
	            
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");          
	            
	            System.out.println("\n도서 대여예약 등록 화면입니다.");
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
	                System.out.println("도서 대여예약 등록을 확인해주세요!!!");
	                System.out.println("bookReservationInput() Exception!!!");
	            }
	        } // bookInsertInput()
	    
		}// BookDao

		


