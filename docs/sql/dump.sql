-- member data
insert into member(member_id, login_id, login_pw, username, email, phone, birth, gender, nickname,
                   nickname_last_modified_date, authority)
values (1, 'ssafy1', 'ssafy1', '임우택', 'ssafy1@ssafy.com', '01011111111', '010101', '1', '코코', '20230101', 'ADMIN'),
       (2, 'ssafy2', 'ssafy2', '이예리', 'ssafy2@ssafy.com', '01022222222', '010102', '2', '리온', '20230101', 'CLIENT'),
       (3, 'ssafy3', 'ssafy3', '정유빈', 'ssafy3@ssafy.com', '01033333333', '010103', '2', '삼성', '20230101', 'CLIENT');

-- notion data
insert into notion(notion_id, title, content, top, created_by, last_modified_by)
values (1, '4월 11일 (화) 시스템 점검 및 업데이트 안내', '더욱 괘적하고 안정적인 서비스 지원을 위해 점검이 진행될 예정입니다.', 1, 1, 1),
       (2, '개인정보 처리방침 개정 안내', 'Enjoy Trip의 개인정보 처리방침이 개정될 예정이니 서비스 이용 시 참고해주시기 바랍니다.', 0, 1, 1);

-- article data
insert into ARTICLE(article_id, member_id, title, content)
values (1, 2, '자유게시판 글 1', '자유게시판 내용 1'),
       (2, 2, '자유게시판 글 2', '자유게시판 내용 2'),
       (3, 2, '자유게시판 글 3', '자유게시판 내용 3'),
       (4, 2, '자유게시판 글 4', '자유게시판 내용 4'),
       (5, 2, '자유게시판 글 5', '자유게시판 내용 5'),
       (6, 2, '자유게시판 글 6', '자유게시판 내용 6'),
       (7, 2, '자유게시판 글 7', '자유게시판 내용 7'),
       (8, 2, '자유게시판 글 8', '자유게시판 내용 8'),
       (9, 2, '자유게시판 글 9', '자유게시판 내용 9'),
       (10, 2, '자유게시판 글 10', '자유게시판 내용 10'),
       (11, 2, '자유게시판 글 11', '자유게시판 내용 11'),
       (12, 2, '자유게시판 글 12', '자유게시판 내용 12');

insert into trip_plan (member_id, title)
values (1, '광주 맛집투어'),
       (2,'전국 익스트림 즐기기'),
       (3,'자전거 일주');