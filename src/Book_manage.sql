------------------
-- BOOK DATABASE
-----------------

CREATE DATABASE book_db;
DROP DATABASE book_db;

CREATE DATABASE book_db
	DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_general_ci;

USE book_db;
SELECT CURRENT_USER;

-- 테이블 생성, 도서 대여 
CREATE TABLE Book_Borrow (
	id VARCHAR(20) PRIMARY KEY COMMENT '유저 아이디',
    book_name VARCHAR(20) COMMENT '도서 제목',
    book_date DATETIME COMMENT '대여 날짜', 
    return_date DATETIME COMMENT '반납 날짜', 
    FOREIGN KEY(id) REFERENCES 
		User(id) ON DELETE SET NULL,
	FOREIGN KEY(book_name) REFERENCES 
		Book(book_name) ON DELETE SET NULL
        ) COMMENT '도서 대여';
        
-- 테이블 생성, 도서 반납 
CREATE TABLE Book_return ( 
	id VARCHAR(20) PRIMARY KEY COMMENT '유저 아이디',
    book_name VARCHAR(20) COMMENT '도서 제목',
    book_date DATETIME COMMENT '대여 날짜', 
    return_date DATETIME COMMENT '반납 날짜',
	FOREIGN KEY(id) REFERENCES 
		User(id) ON DELETE SET NULL,
	FOREIGN KEY(book_name) REFERENCES 
		Book(book_name) ON DELETE SET NULL
        ) COMMENT '도서 반납';
 
-- 테이블 생성, 도서 예약
CREATE TABLE Book_Reservation ( 
	id VARCHAR(20) PRIMARY KEY COMMENT '유저 아이디',
    book_name VARCHAR(20) COMMENT '도서 제목',
    book_cd VARCHAR(20) COMMENT '도서 번호', 
    reserve_date DATETIME COMMENT '예약 날짜',
	FOREIGN KEY(id) REFERENCES 
		User(id) ON DELETE SET NULL,
	FOREIGN KEY(book_name) REFERENCES 
		Book(book_name) ON DELETE SET NULL
        ) COMMENT '도서 반납';
	