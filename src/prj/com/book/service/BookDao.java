/**
 * 
 */
package prj.com.book.service;

import java.sql.ResultSet;
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

	// 도서 목록 보기
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

	// 도서 주문 신청
//	public void bookOrderInput() {
///		System.out.println("\n도서 주문 신청 화면입니다");
//		System.out.print("주문할 도서의 번호를 입력하세요> ");
//		int num = Integer.parseInt(scanner.nextLine());
//		BookDaoImpl dao = new BookDaoImpl();
//		ResultSet rs = dao.checkNum(num);
//		try {
//			if(rs.next() != true) { 
//				System.out.println("입력하신 " + num + "번 도서는 등록되어 있지 않습니다.");
//			} else {
//				System.out.print("주문할 도서의 수량을 입력하세요> ");
//				int cnt = Integer.parseInt(scanner.nextLine());
//				
//				dao.orderBook(rs, cnt);
//				
////				String title = rs.getString("title");
////				int cost = rs.getInt("cost");
////				int price = cnt * cost;
////				
////				DecimalFormat df = new DecimalFormat("￦#,##0");
////				String msg = "\n주문하신 도서 명은 " + title + "이고, ";
////				msg += "단가는 " + df.format(cost) + "원이며, ";
////				msg += "주문 수량은 " + cnt + "권 입니다.";
////				msg += "\n총 주문 금액은 " + df.format(price) + "원입니다.";
////				
////				System.out.println(msg);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("bookOrderInput() Exception!!!");
//		}
} // bookOrderInput()