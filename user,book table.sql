Create table user(
	id varchar(20) primary key not null,
    pw varchar(20) not null,
    name varchar(20) not null,
    address varchar(30) not null);

select * from user;

insert into user values('a1', 'a1', '김김김', '서울마포구');
insert into user values('b1', 'b1', '이이이', '인천남동구');
insert into user values('c1', 'c1', '박박박', '인천미추홀구');
insert into user values('d1', 'd1', '최최최', '서울행복구');
insert into user values('e1', 'e1', '홍홍홍', '서울강남구');

select * from book;


Create table book(
	book_cd varchar(20) primary key not null, -- 도서번호
    book_name varchar(20) not null,			-- 도서명
    book_author varchar(20) not null,		-- 작가
    book_publisher varchar(20) not null,	-- 출판사
    book_num int not null);					-- 도서수

select * from book;

insert into book values('1', '우리는 사랑 안에 살고 있다', '조정연', '21세기북스', 7);
insert into book values('2', '소년이 온다', '한강', '창비', 4);
insert into book values('3', '채식주의자', '한강', '창비', 8);
insert into book values('4', '일의 감각', '조수용', 'REFERENCE BY B', 9);
insert into book values('5', '베스트셀러넥서스', '유발 하라리', '김영사', 13);
