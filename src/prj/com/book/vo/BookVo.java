package prj.com.book.vo;

/**
 * @Author : TeamPrjBook
 * @Date : 2024. 11. 27.
 * 
 *       DTO(VO) : 자바 계층간 데이터를 주고 받기 위한 데이터 객체 - 로직이 없는 순수 데이터 객체 - 기본 생성자가 반드시
 *       있어야 한다. - toString(), equals() 메서드는 만들기도 한다.
 */

public class BookVo {
//	도서번호
	String bookCd;;
//	도서이름
	String bookName;
//	작가
	String bookAuthor;
//	출판사
	String bookPublisher;
//	도서대여 
	String borrowDate;
//	도서반납
	String returnDate;
//  도서예약한날짜
	String resrveDate;
//  도서예약마감날짜
	String reserveDateBorrow;
//  유저 ID
	String userId;
//  패스워드
	String pw;
//  이름
	String name;
//  주소
	String address;
//  전화번호
	String phone;
//  이메일
	String email;

//	기본생성자
	public BookVo() {
	}


//	매개변수 생성자(1)
	public BookVo(String bookCd) {
		super();
		this.bookCd = bookCd;
	}

//	매개변수 생성자(2)
	public BookVo(String userId, String bookCd) {
		super();
		this.userId = userId;
		this.bookCd = bookCd;
	}

//	매개변수 생성자(3)
	public BookVo(String bookName, String bookAuthor, String bookPublisher) {
		super();
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookPublisher = bookPublisher;
	}
	
//	매개변수 생성자(4)
	public BookVo(String bookCd, String bookName, String bookAuthor, String bookPublisher) {
		super();
		this.bookCd = bookCd;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookPublisher = bookPublisher;
	}

//  매개변수 생성자(5)
	public BookVo(String userId, String bookCd, String bookName, String resrveDate, String reserveDateBorrow) {
		super();
		this.userId = userId;
		this.bookCd = bookCd;
		this.bookName = bookName;
		this.resrveDate = resrveDate;
		this.reserveDateBorrow = reserveDateBorrow;
	}

//  매개변수 생성자(6)
	public BookVo(String userId, String pw, String name, String address, String phone, String email) {
		super();
		this.userId = userId;
		this.pw = pw;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}

//	게터 / 세터
	public String getBookCd() {
		return bookCd;
	}

	public void setBookCd(String bookCd) {
		this.bookCd = bookCd;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookPublisher() {
		return bookPublisher;
	}

	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}

	public String getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getResrveDate() {
		return resrveDate;
	}

	public void setResrveDate(String resrveDate) {
		this.resrveDate = resrveDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReserveDateBorrow() {
		return reserveDateBorrow;
	}

	public void setReserveDateBorrow(String reserveDateBorrow) {
		this.reserveDateBorrow = reserveDateBorrow;
	}	

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// toString
	@Override
	public String toString() {
		return "BookVo [bookCd=" + bookCd + ", bookName=" + bookName + ", bookAuthor=" + bookAuthor + ", bookPublisher="
				+ bookPublisher + ", borrowDate=" + borrowDate + ", returnDate=" + returnDate + ", resrveDate="
				+ resrveDate + ", reserveDateBorrow=" + reserveDateBorrow + ", userId=" + userId + ", pw=" + pw
				+ ", name=" + name + ", address=" + address + ", phone=" + phone + ", email=" + email + "]";
	}



}