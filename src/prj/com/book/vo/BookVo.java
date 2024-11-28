package prj.com.book.vo;

/**
 * @Author : TeamPrjBook
 * @Date : 2024. 11. 27.
 * 
 *       DTO(VO) : 자바 계층간 데이터를 주고 받기 위한 데이터 객체 - 로직이 없는 순수 데이터 객체 - 기본 생성자가 반드시
 *       있어야 한다. - toString(), equals() 메서드는 만들기도 한다.
 */
//public class BookVo {
//	/**
//	 * @Author : TeamPrjBook
//	 * @Date : 2024. 11. 27.
//	 */
//
//	private int num;
//	private String title, company, name;
//	private int cost;
//	
//	public BookVo() {}
//
//	public BookVo(int num, String title, String company, String name, int cost) {
//		super();
//		this.num = num;
//		this.title = title;
//		this.company = company;
//		this.name = name;
//		this.cost = cost;
//	}
//
//	public int getNum() {
//		return num;
//	}
//
//	public void setNum(int num) {
//		this.num = num;
//	}
//
//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public String getCompany() {
//		return company;
//	}
//
//	public void setCompany(String company) {
//		this.company = company;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public int getCost() {
//		return cost;
//	}
//
//	public void setCost(int cost) {
//		this.cost = cost;
//	}
	
	
	
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
//	도서예약 
	String resrveDate;

//	기본생성자
	public BookVo() {
	}

//	생성자
	public BookVo(String bookName, String bookAuthor, String bookPublisher) {
		super();
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookPublisher = bookPublisher;
	}

//	매개변수 생성자
	public BookVo(String bookCd, String bookName, String bookAuthor, String bookPublisher) {
		super();
		this.bookCd = bookCd;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookPublisher = bookPublisher;
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

	//	toString
	@Override
	public String toString() {
		return "BookVo [bookCd=" + bookCd + ", bookName=" + bookName + ", bookAuthor=" + bookAuthor + ", bookPublisher="
				+ bookPublisher + ", borrowDate=" + borrowDate + ", returnDate=" + returnDate + ", resrveDate="
				+ resrveDate + "]";
	}
	
}