# 포트폴리오 관리 프로젝트
- Framework :  Spring 4.3.3 (JDK 1.8) + myBatis + tiles3
- DBMS     : MySQL
- 프로젝트명: Pofoland
- 사명 : HST (Happy Sinchon Toz)

## 0. 사용자
- 회원가입
- 인증(이메일)
- 사용자 간 팔로우
- 쪽지
- 점수, 레벨(권한 관리)

## 1. 포트폴리오 관리
- 포트폴리오 작성
- 포트폴리오 업로드
- 공유 (카카오톡, 페이스북 등으로 뷰어로 보여줄 수 있게)
- 미리보기
- 댓글
- 좋아요


## 2. 커뮤니티
 - CRUD
 - 답글
 - 공유
 
** div 에 id, class 등 잘 달아놓을 것 **

## UI 설계
템플릿(SELECT) - http://shapebootstrap.net/item/1525204-cluster-free-creative-portfolio-bootstrap-template/live-demo

## DB 설계
- DB Info

		Amazon RDS
		DB Engine -v : MariaDB 10.0.24
		DB name : pofoland
		Backup Retention Period : 3 days
		Allocated Storage : 5 GB
		Storage Type : SSD




## Architecture
### VO
- VO
		BaseVO - 페이징, 검색 정보 등 공통부분을 모아놓고 모든 VO 는 BaseVO 를 상속받는다.