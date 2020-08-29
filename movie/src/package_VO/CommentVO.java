package package_VO;

import java.util.Calendar;
import java.util.Date;
/**
 * 한줄평VO
 * @author PC-13
 *
 */
public class CommentVO {
   private int comt_num; //기본키 (글번호)
   private Date comt_date; // 게시판에 찍힐 날짜   //수정
   private String comt_content; // 한줄평
   private int comt_grade; //평점
   private int movie_num; // 외래키 영화번호
   private String member_id; // 외래키 회원아이디
   private static int comt_sq = 0; // 시퀀스 번호
   private boolean isDeleted; // 삭제여부
   
   /**
    * 삭제설정 메서드
    */
   public void setDeleted() {
      this.isDeleted = !isDeleted;
   }
   
   public boolean getDeleted() {
      return isDeleted;
   }
   
   //시퀀스 초기화
   {
      comt_num = comt_sq;
      comt_sq++;
      
   }

   // get영역
   public int getComt_num() {
      return comt_num;
   }
   
   public Date getComt_date() {
      return comt_date;
   }

   public String getComt_content() {
      return comt_content;
   }

   public int getComt_grade() {
      return comt_grade;
   }

   public int getMovie_num() {
      return movie_num;
   }

   public String getMember_id() {
      return member_id;
   }
   
   // set영역
   public void setComt_date(Date comt_date) {
      this.comt_date = comt_date;
   }

   public void setComt_content(String comt_content) {
      this.comt_content = comt_content;
   }

   public void setComt_grade(int comt_grade) {
      this.comt_grade = comt_grade;
   }

   public void setMovie_num(int movie_num) {
      this.movie_num = movie_num;
   }

   public void setMember_id(String member_id) {
      this.member_id = member_id;
   }

   
}