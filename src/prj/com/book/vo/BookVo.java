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
	String book_cd;
//	도서이름
	String book_name;
//	작가
	String book_author;
//	출판사
	String book_publisher;
//	도서대여 
	String borrow_dete; 
//	도서반납
	String return_date; 
//	도서예약 
	String resrve_date;

//	기본생성자
	public BookVo() {
	}

//	생성자
	public BookVo(String book_name, String book_author, String book_publisher) {
		super();
		this.book_name = book_name;
		this.book_author = book_author;
		this.book_publisher = book_publisher;
	}

//	매개변수 생성자
	public BookVo(String book_cd, String book_name, String book_author, String book_publisher) {
		super();
		this.book_cd = book_cd;
		this.book_name = book_name;
		this.book_author = book_author;
		this.book_publisher = book_publisher;
	}

//	게터 / 세터
	public String getBook_cd() {
		return book_cd;
	}

	public void setBook_cd(String book_cd) {
		this.book_cd = book_cd;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getBook_author() {
		return book_author;
	}

	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}

	public String getBook_publisher() {
		return book_publisher;
	}

	public void setBook_publisher(String book_publisher) {
		this.book_publisher = book_publisher;
	}

	public String getBorrow_dete() {
		return borrow_dete;
	}

	public void setBorrow_dete(String borrow_dete) {
		this.borrow_dete = borrow_dete;
	}

	public String getReturn_date() {
		return return_date;
	}

	public void setReturn_date(String return_date) {
		this.return_date = return_date;
	}

	public String getResrve_date() {
		return resrve_date;
	}

	public void setResrve_date(String resrve_date) {
		this.resrve_date = resrve_date;
	}

	//	toString
	@Override
	public String toString() {
		return "BookVo [book_cd=" + book_cd + ", book_name=" + book_name + ", book_author=" + book_author
				+ ", book_publisher=" + book_publisher + "]";
	}


	
	
}