package prj.com.book.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import prj.com.book.connection.Connections;
import prj.com.book.vo.BookVo;

public class BookDaoImpl {
	/**
	 * @Author : TeamPrjBook
	 * @Date : 2024. 11. 27.
	 */

	private Connection conn = Connections.getConn();
	private PreparedStatement ps;
	private ResultSet rs;

// 회원가입시 데이터 입력 메서드
	public int insert(BookVo vo) throws ClassNotFoundException, SQLException {

		int succ = 0;

		String userId = vo.getUserId();
		String pw = vo.getPw();
		String name = vo.getName();
		String address = vo.getAddress();
		String phone = vo.getPhone();
		String email = vo.getEmail();

		String sql = "INSERT INTO user() VALUES(?,?,?,?,?,?)";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, pw);
			ps.setString(3, name);
			ps.setString(4, address);
			ps.setString(5, phone);
			ps.setString(6, email);

			succ = ps.executeUpdate();

			System.out.println("회원가입이 완료되었습니다. 환영합니다!");

			System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯ '" + name + "' 님 회원 정보 ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
			System.out.println("id = " + userId);
			System.out.println("password = " + pw);
			System.out.println("name = " + name);
			System.out.println("address = " + address);
			System.out.println("phone = " + phone);
			System.out.println("email = " + email);
			System.out.println();
		} catch (Exception e) {
			System.out.println("아이디가 중복되어 회원가입이 불가합니다.");
			System.out.println("처음부터 다시 회원가입을 진행해주세요.");
		}

		return succ;

	} // insert

	// 로그인시 id,password 와 동일한 정보 출력해주는 메서드
	public BookVo loginData(String userId, String pw) throws ClassNotFoundException, SQLException {

		BookVo member = null;

		String sql = "" + "SELECT id, pw, name, address, phone, email " + "FROM user " + "WHERE ID IN (?,?) ";

		ps = conn.prepareStatement(sql);
		ps.setString(1, userId);
		ps.setString(2, pw);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			member = new BookVo();
			member.setUserId(rs.getString("id"));
			member.setPw(rs.getString("pw"));
			member.setName(rs.getString("name"));
			member.setAddress(rs.getString("address"));
			member.setPhone(rs.getString("phone"));
			member.setEmail(rs.getString("email"));
		}

		return member;
	} // loginData

// 유저 아이디 조회 메서드
	public ResultSet checkId(String userId) {
		String sql = "SELECT * FROM user WHERE id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("유저 아이디 조회 정보를 확인해주세요");
			System.out.println("checkNum() Exception!!!");
		}
		return rs;
	} // ResultSet

//도서 회원번호 조회 메서드
	public ResultSet checkRsNum(String id) {
		String sql = "SELECT * FROM book_reservation WHERE id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println("도서 회원번호 정보를 확인해주세요");
			System.out.println("checkRsNum() Exception!!!");
		}
		return rs;
	} // checkRsNum()

// 도서 번호 조회 메서드
	public ResultSet checkNum(String book_cd) {
		String sql = "SELECT * FROM book WHERE book_cd = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, book_cd);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println("도서 번호 조회 정보를 확인해주세요");
			System.out.println("checkNum() Exception!!!");
		}
		return rs;
	} // checkNum()

	// 도서 제목 조회 메서드
	public ResultSet checkBook(String book_name) {
		String sql = "SELECT * FROM book WHERE UPPER(book_name) LIKE UPPER(?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, book_name);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println("도서 제목 조회 정보를 확인해주세요");
			System.out.println("checkBook() Exception!!!");
		}
		return rs;
	} // checkBook()

// 도서 정보 등록 메서드
	public int insertBook(BookVo dto) {
		int succ = 0;
		String sql = "INSERT INTO book VALUES(?, ?, ?, ?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getBookCd());
			ps.setString(2, dto.getBookName());
			ps.setString(3, dto.getBookPublisher());
			ps.setString(4, dto.getBookAuthor());
			succ = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("도서 정보 등록을 확인해주세요");
			System.out.println("insertBook() Exception!!!");
		}
		return succ;
	} // insertBook()

//	도서 정보 목록 메서드
	public ArrayList<BookVo> selectBookAll(ArrayList<BookVo> list) {
		String sql = "SELECT * FROM book ORDER BY book_cd ASC";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String book_cd = rs.getString("book_cd");
				String book_name = rs.getString("book_name");
				String book_publisher = rs.getString("book_publisher");
				String book_author = rs.getString("book_author");

				BookVo dto = new BookVo(book_cd, book_name, book_publisher, book_author);
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println("도서 정보 목록을 확인해주세요");
			System.out.println("selectBookAll() Exception!!!");
		}
		return list;
	} // selectBookAll()

// 출력 메서드
	public void display(ArrayList<BookVo> list) {
		if (list.size() == 0) {
			System.out.println("검색된 결과가 없습니다.");
			System.out.println("다시 실행해주세요");
		} else {
			for (BookVo dto : list) {
				System.out.print(dto.getBookCd() + "\t");
				System.out.print(dto.getBookName() + "\t");
				System.out.print(dto.getBookPublisher() + "\t");
				System.out.print(dto.getBookAuthor() + "\n");
			}
		}
	} // display()

// 도서 제목 검색 메서드
	public void selectBookTitle(ArrayList<BookVo> list, String searchTitle) {
		String sql = "SELECT * FROM book WHERE UPPER(book_name) LIKE UPPER(?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + searchTitle + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				String book_cd = rs.getString("book_cd");
				String book_name = rs.getString("book_name");
				String book_publisher = rs.getString("book_publisher");
				String book_author = rs.getString("book_author");
				BookVo dto = new BookVo(book_cd, book_name, book_publisher, book_author);
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println("도서 제목 검색을 확인해주세요");
			System.out.println("selectBookTitle() Exception!!!");
		}
	} // selectBookTitle()

// 도서 정보 삭제 메서드
	public int deleteBook(String book_cd) {
		int succ = 0;
		String sql = "DELETE FROM book WHERE book_cd = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, book_cd);
			succ = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("도서 정보 삭제를 확인해주세요");
			System.out.println("deleteBook() Exception!!!");
		}
		return succ;

	} // deleteBook()

// 도서 정보 수정 메서드
	public int updateBook(BookVo dto) {
		int succ = 0;
		String sql = "UPDATE book SET book_name = ?, book_publisher = ?, ";
		sql += "book_author = ? WHERE book_cd = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getBookName());
			ps.setString(2, dto.getBookPublisher());
			ps.setString(3, dto.getBookAuthor());
			ps.setString(4, dto.getBookCd());
			succ = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("도서 정보 수정을 확인해주세요");
			System.out.println("updateBook() Exception!!!");
		}
		return succ;
	}// updateBook

// 도서 목록 대여 메서드 
	public int borrowBook(BookVo dto) {
		int succ = 0;
		String sql = "INSERT INTO book_borrow(id, book_cd, book_name, borrow_date, return_date) "
				+ " SELECT (SELECT id FROM user WHERE id = ?), "
				+ " book_cd, book_name, NOW(), DATE_ADD(NOW(), INTERVAL 2 WEEK) " + " FROM book WHERE book_cd = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getUserId());
			ps.setString(2, dto.getBookCd());
			succ = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("도서 목록 대여 정보를 확인해주세요");
			System.out.println("deleteBook() Exception!!!");
		}
		return succ;
	} // borrowBook

//도서 목록 반납 메서드 
	public int returnBook(BookVo dto) {
		int succ = 0;
		String sql = "INSERT INTO book_return(id, book_cd, book_name, borrow_date, return_date) "
				+ " SELECT (SELECT id FROM user WHERE id = ?), book_cd ar, book_name, "
				+ " (SELECT borrow_date FROM book_borrow WHERE book_cd = ar), NOW() " + " FROM book WHERE book_cd = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getUserId());
			ps.setString(2, dto.getBookCd());
			succ = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("도서 목록 반납 정보를 확인해주세요");
			System.out.println("deleteBook() Exception!!!");
		}
		return succ;
	} // returnBook

//도서 대여예약확인 메서드
	public void orderBook(ResultSet rs, String reservationCnt, String bookCd) {
		try {
			String title = rs.getString("book_name");
			String reserveDateBorrow = rs.getString("reserve_date_borrow");
			String id = rs.getString("id");

			String msg = "\n예약하신 도서 명은 " + title + "이고, ";//
			msg = "\n예약하신 회원 명은 " + id + "이고, ";
			msg += "예약 수량은 " + reservationCnt + "권 입니다.";
			msg += "대여 하실 날짜는" + reserveDateBorrow + "일 까지입니다.";

//   System.out.println(msg);
		} catch (Exception e) {
			System.out.println("도서 대여 예약확인 정보를 확인해주세요");
			System.out.println("orderBook() Exception!!!");
		}
	} // orderBook()

//	도서 대여 예약 메서드
	public int reserveBorrow(BookVo dto) {
		int succ = 0;
		String sql = "INSERT INTO book_reservation VALUES(?,?,?,?,?) ";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getUserId());
			ps.setString(2, dto.getBookCd());
			ps.setString(3, dto.getBookName());
			ps.setString(4, dto.getResrveDate());
			ps.setString(5, dto.getReserveDateBorrow());
			succ = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("도서 대여예약 정보를 확인해주세요");
			System.out.println("reserveBorrow() Exception!!!");
		}
		return succ;
	} // reserveBorrow

// DB Close
	public void dbClose() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			System.out.println("db정보를 잘 닫았는지 확인해주세요");
			System.out.println("dbClose() Exception!!!");
		}
	} // dbClose

}
