package prj.book;

/**
 * @Author : TeamPrjBook
 * @Date : 2024. 11. 27.
 * 
 *       DTO(VO) : 자바 계층간 데이터를 주고 받기 위한 데이터 객체 - 로직이 없는 순수 데이터 객체 - 기본 생성자가 반드시
 *       있어야 한다. - toString(), equals() 메서드는 만들기도 한다.
 */
public class BookVo {
	/**
	 * @Author : TeamPrjBook
	 * @Date : 2024. 11. 27.
	 */

//	도서번호
	String book_cd;
//	도서이름
	String book_name;
//	작가
	String book_author;
//	출판사
	String book_publisher;

	
//	생성자
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

//	toString
	@Override
	public String toString() {
		return "BookVo [book_cd=" + book_cd + ", book_name=" + book_name + ", book_author=" + book_author
				+ ", book_publisher=" + book_publisher + "]";
	}

}
