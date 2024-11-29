package prj.com.book.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import prj.com.book.connection.Connections;
import prj.com.book.vo.BookVo;

public class BookDaoImpl {
	/**
	 * @Author : TeamPrjBook
	 * @Date   : 2024. 11. 27.
	 */

private Connection conn = Connections.getConn();	
private PreparedStatement ps;
private ResultSet rs;

// 유저 아이디 조회 
public ResultSet checkId(String userId) {
	String sql = "SELECT * FROM user WHERE id = ?";
	try {
		ps = conn.prepareStatement(sql);
		ps.setString(1, userId);
		rs = ps.executeQuery();
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("checkNum() Exception!!!");
	}
	return rs;
}


// 도서 번호 조회
public ResultSet checkNum(String book_cd) {
	String sql = "SELECT * FROM book WHERE book_cd = ?";
	try {
		ps = conn.prepareStatement(sql);
		ps.setString(1, book_cd);
		rs = ps.executeQuery();
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("checkNum() Exception!!!");
	}
	return rs;
} // checkNum()

// 도서 정보 등록
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
		e.printStackTrace();
		System.out.println("insertBook() Exception!!!");
	}
	return succ;
} // insertBook()

public ArrayList<BookVo> selectBookAll(ArrayList<BookVo> list) {
	String sql = "SELECT * FROM book ORDER BY book_cd ASC";
	try {
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()) {
			String book_cd = rs.getString("book_cd");
			String book_name = rs.getString("book_name");
			String book_publisher = rs.getString("book_publisher");
			String book_author = rs.getString("book_author");
			
			BookVo dto = new BookVo(book_cd, book_name, book_publisher, book_author);
			list.add(dto);
		}
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("selectBookAll() Exception!!!");
	}
	return list;
} // selectBookAll()

// 출력 메서드
public void display(ArrayList<BookVo> list) {
	if(list.size() == 0) { 
		System.out.println("검색된 결과가 없습니다.");
	} else {
		for (BookVo dto : list) {
			System.out.print(dto.getBookCd() + "\t");
			System.out.print(dto.getBookName() + "\t");
			System.out.print(dto.getBookPublisher() + "\t");
			System.out.print(dto.getBookAuthor() + "\n");
		}
	}
} // display()

// 제목 검색 메서드
public void selectBookTitle(ArrayList<BookVo> list, String searchTitle) {
	String sql = "SELECT * FROM book WHERE UPPER(book_name) LIKE UPPER(?)";
	try {
		ps = conn.prepareStatement(sql);
		ps.setString(1, "%" + searchTitle + "%");
		rs = ps.executeQuery();
		while(rs.next()) {
			String book_cd = rs.getString("book_cd");
			String book_name = rs.getString("book_name");
			String book_publisher = rs.getString("book_publisher");
			String book_author = rs.getString("book_author");
			BookVo dto = new BookVo(book_cd, book_name, book_publisher, book_author);
			list.add(dto);
		}
	} catch (Exception e) {
		e.printStackTrace();
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
		e.printStackTrace();
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
		e.printStackTrace();
		System.out.println("updateBook() Exception!!!");
	}
	return succ;
}

// 도서 목록 대여 메서드 
public int borrowBook(BookVo dto) {
	int succ = 0;
	String sql = "INSERT INTO book_borrow(id, book_cd, book_name, borrow_date, return_date) " +
				" SELECT (SELECT id FROM user WHERE id = ?), " +
				" book_cd, book_name, NOW(), DATE_ADD(NOW(), INTERVAL 2 WEEK) " +
				" FROM book WHERE book_cd = ?";
	try {
		ps = conn.prepareStatement(sql);
		ps.setString(1, dto.getUserId());
		ps.setString(2, dto.getBookCd());
		succ = ps.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("deleteBook() Exception!!!");
	}
	return succ;
}

//도서 목록 반납 메서드 
public int returnBook(BookVo dto) {
	int succ = 0;
	String sql = "INSERT INTO book_return(id, book_cd, book_name, borrow_date, return_date) " +
				" SELECT (SELECT id FROM user WHERE id = ?), book_cd ar, book_name, " +
						" (SELECT borrow_date FROM book_borrow WHERE book_cd = ar), NOW() " +
				" FROM book WHERE book_cd = ?";
	try {
		ps = conn.prepareStatement(sql);
		ps.setString(1, dto.getUserId());
		ps.setString(2, dto.getBookCd());
		succ = ps.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("deleteBook() Exception!!!");
	}
	return succ;
}


// 도서 주문 메서드
//public void orderBook(ResultSet rs, int cnt) {
//	try {
//		String title = rs.getString("book_name");
//		int cost = rs.getInt("cost");
//		int price = cnt * cost;
//		
//		DecimalFormat df = new DecimalFormat("￦#,##0");
//		String msg = "\n주문하신 도서 명은 " + title + "이고, ";
//		msg += "단가는 " + df.format(cost) + "원이며, ";
//		msg += "주문 수량은 " + cnt + "권 입니다.";
//		msg += "\n총 주문 금액은 " + df.format(price) + "원입니다.";
//		
//		System.out.println(msg);
//	} catch (Exception e) {
//		e.printStackTrace();
//		System.out.println("orderBook() Exception!!!");
//	}
//} // orderBook()

// DB Close
public void dbClose() {
	try {
		if(rs != null) rs.close();
		if(ps != null) ps.close();
		if(conn != null) conn.close();
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("dbClose() Exception!!!");
	}
}
	
	
}
