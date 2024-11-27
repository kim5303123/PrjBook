------------------
-- BOOK DATABASE
------------------

-- 데이터 베이스 생성 
CREATE DATABASE book_db
	DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_general_ci;

USE book_db;

-- 테이블 생성, 사용자 
CREATE TABLE user(
	id VARCHAR(20) PRIMARY KEY NOT NULL COMMENT '사용자 아이디',
    pw VARCHAR(20) NOT NULL COMMENT '사용자 비밀번호',
    name VARCHAR(20) NOT NULL COMMENT '사용자 이름',
    address VARCHAR(30) NOT NULL COMMENT '사용자 주소'
    ) COMMENT '사용자';

select * from user;

-- 사용자 속성 추가 
INSERT INTO USER VALUES('a1', 'a1', '김김김', '서울마포구');
INSERT INTO USER VALUES('b1', 'b1', '이이이', '인천남동구');
INSERT INTO USER VALUES('c1', 'c1', '박박박', '인천미추홀구');
INSERT INTO USER VALUES('d1', 'd1', '최최최', '서울행복구');
INSERT INTO USER VALUES('e1', 'e1', '홍홍홍', '서울강남구');

select * from book;

-- 테이블 생성, 도서 
CREATE TABLE book(
	book_cd VARCHAR(20) PRIMARY KEY NOT NULL COMMENT '도서 번호', 
    book_name VARCHAR(20) NOT NULL COMMENT '도서명',			
    book_author VARCHAR(20) NOT NULL COMMENT '저자',
    book_publisherVARCHAR(20) NOT NULL COMMENT '출판사', 	
    -- book_num int not null);					


-- 도서 속성 추가 
INSERT INTO book VALUES('1', '우리는 사랑 안에 살고 있다', '조정연', '21세기북스');
INSERT INTO book VALUES('2', '소년이 온다', '한강', '창비');
INSERT INTO book VALUES('3', '채식주의자', '한강', '창비');
INSERT INTO book VALUES('4', '일의 감각', '조수용', 'REFERENCE BY B');
INSERT INTO book VALUES('5', '베스트셀러넥서스', '유발 하라리', '김영사');

SELECT * FROM Book_Borrow;
DROP TABLE Book_Borrow;

-- 테이블 생성, 도서 대여 
CREATE TABLE book_borrow (
		id VARCHAR(20) PRIMARY KEY COMMENT '유저 아이디',
        book_cd VARCHAR(20) NOT NULL COMMENT '도서 번호',
		book_name VARCHAR(20) NOT NULL COMMENT '도서 제목',
    	book_date DATETIME NOT NULL COMMENT '대여 날짜', 
    	return_date DATETIME NOT NULL COMMENT '반납 날짜', 
		FOREIGN KEY(id) REFERENCES 
			User(id) ON DELETE CASCADE,
		FOREIGN KEY(book_cd) REFERENCES 
			Book(book_cd) ON DELETE CASCADE
        ) COMMENT '도서 대여';
        
-- 테이블 생성, 도서 반납 
CREATE TABLE book_return ( 
		id VARCHAR(20) PRIMARY KEY COMMENT '유저 아이디',
        book_cd VARCHAR(20) NOT NULL COMMENT '도서 번호',
    	book_name VARCHAR(20) NOT NULL COMMENT '도서 제목',
    	book_date DATETIME NOT NULL COMMENT '대여 날짜', 
    	return_date DATETIME NOT NULL COMMENT '반납 날짜',
		FOREIGN KEY(id) REFERENCES 
			User(id) ON DELETE CASCADE,
		FOREIGN KEY(book_cd) REFERENCES 
			Book(book_cd) ON DELETE CASCADE
        ) COMMENT '도서 반납';
 
-- 테이블 생성, 도서 예약
CREATE TABLE book_reservation ( 
		id VARCHAR(20) PRIMARY KEY COMMENT '유저 아이디',
        book_cd VARCHAR(20) NOT NULL COMMENT '도서 번호',
    	book_name VARCHAR(20) NOT NULL COMMENT '도서 제목',
    	reserve_date DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '예약 날짜',
		FOREIGN KEY(id) REFERENCES 
			User(id) ON DELETE CASCADE,
		FOREIGN KEY(book_cd) REFERENCES 
			Book(book_cd) ON DELETE CASCADE
        ) COMMENT '도서 반납';

DROP TABLE Book_Reservation;        
DROP TABLE book_Borrow;
	SELECT * FROM Book_Reservation;

INSERT INTO book_borrow(id, book_cd, book_name) 
		VALUE('a1', 1, '우리는 사랑 안에 살고 있다');   

SELECT uer.id, uer.pw, uer.name, uer.address, res.book_cd, res.reserve_date
FROM user uer INNER JOIN Book_Reservation res
				ON uer.id = res.id;
	