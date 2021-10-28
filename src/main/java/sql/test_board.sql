create table test_board(
id number(4) primary key,  // 게시글 번호 
name varchar2(20),  //글 작성한 사용자 
title varchar2(100),   
content varchar2(300),
savedate date default sysdate,   //시스템의 날짜를 자동으로 넣어준다 
hit number(4) default 0,   //조회수 
idgroup number(4),
step number(4),
indent number(4)
);
create sequence test_board_seq;