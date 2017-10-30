/* 
 * =============================
 * 프로그램 설명 : 장바구니에 들어있는 제품 정보를 조회, 제품 구매, 삭제기능을 위한 DAO 　　
 * プログラムの説明:カ-トに入っている製品情報を照会、製品の購買、削除機能のためのDAO
 * 작성자 : 김덕현　　
 * 作成者 : キム・ドクヒョン
 * 최초 작성일자 : 　2017-07-17
 * 最初の作成日付 :   2017-07-17
 * 최종 수정일 : 　　
 * 最終の修正日 :
 * 수정 내용 : 　　
 * 修正の内容 :
 * =============================
 * */

package com.bs.mycart.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bs.util.DBUtil;
import com.bs.view.MainFrame;
import com.bs.vo.CartVO;
import com.bs.vo.ClothBasicVO;

public class MyCartDAO {
   //장바구니 조회  
   //カ-トを照会
   public ArrayList<CartVO> getCartAndImage(String c_id) throws Exception {
      
      ArrayList<CartVO> list = new ArrayList<CartVO>();
      String dml = "select c_id"
            + ", c_product_code"
            + ", c_product_name"
            + ", c_size, c_price"
            + ", c_brand,c_material"
            + ",image_path "
            + "from cart"
            + ", image_table "
            + "where c_product_code = image_name "
            + "and c_id= ? "
            ;
      
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      CartVO retval = null;
      
      try {
         con = DBUtil.getConnection();
         
         pstmt = con.prepareStatement(dml);
         //조회 조건 
	 //照会の条件
         pstmt.setString(1, MainFrame.id);
         
         
         rs = pstmt.executeQuery();//select 일 경우 사용　
				   //selectの場合に使用
         while (rs.next()) {
            retval = new CartVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
            list.add(retval);
         }
      } catch (SQLException se) {
         System.out.println(se);
      } catch (Exception e) {
         System.out.println(e);
      } finally {
         try {
            if (rs != null)
               rs.close();
            if (pstmt != null)
               pstmt.close();
            if (con != null)
               con.close();
         } catch (SQLException se) {
         }
      }
      return list;
   }
   //삭제하기　　
   //削除する
   public ArrayList<CartVO> getDelete(String c_id, String pCode) throws Exception {
      ArrayList<CartVO> list = new ArrayList<CartVO>();
      String dml = "delete from cart where c_id= ? and c_product_code= ? LIMIT 1";//장바구니에 같은 제품코드를 가진 제품은 하나만 삭제한다　　
										  //カ-トに同じ製品コードを持った製品は一つだけ削除する

      Connection con = null;
      PreparedStatement pstmt = null;
      int status=10;//삭제여부를 알기 위한 변수　　
		    //削除するかどうかを知るための変数
      CartVO retval = null;
      
      try {
         con = DBUtil.getConnection();
         
         pstmt = con.prepareStatement(dml);
         //조회 조건 　　
	 //照会の条件
         pstmt.setString(1, c_id);
         pstmt.setString(2, pCode);
         status = pstmt.executeUpdate();//delete,insert 일 경우 사용　
					//delete,insertの場合に使用
//         System.out.println(status);
         if(status==1){//해당 레코드가 삭제된 경우 　　
		       //該当のレコードが削除された場合
//            System.out.println("삭제 완료!");
         }
         else{//해당 레코드가 삭제되지 않은 경우 　　
	      //該当のレコードが削除されていない場合
            System.out.println("삭제 할 내용을 찾을 수 없습니다.!");
         }
      } catch (SQLException se) {
         System.out.println(se);
         se.printStackTrace();
      } catch (Exception e) {
         System.out.println(e);
      } finally {
         try {
            if (pstmt != null)
               pstmt.close();
            if (con != null)
               con.close();
         } catch (SQLException se) {
            se.printStackTrace();
         }
      }
      return list;
   }
   //상품 구매　　
   //商品購買
      public ClothBasicVO setClosetInfo(ClothBasicVO cbvo) throws Exception {

         String dml = "INSERT INTO CLOSET " + " "
                     + "(ID"
                     + ", PRODUCT_CODE"
                     + ", PRODUCT_NAME"
                     + ", BUYDATE)" 
                     + " VALUES " + " (?, ?, ?,DATE_FORMAT(now(), '%Y-%m-%d')) LIMIT 1";//동일한 제품은 1개만 구입  
											//同じ一の製品は1個だけ購入
         Connection con = null;
         PreparedStatement pstmt = null;
         ClothBasicVO retval = null;
         
         try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(dml);
            
            pstmt.setString(1, cbvo.getId());
            pstmt.setString(2, cbvo.getProductCode());
            pstmt.setString(3, cbvo.getProductName());
            
            int i = pstmt.executeUpdate();
            retval = new ClothBasicVO();
            retval.setStatus(i); 
            
         } catch (SQLException e) {
            System.out.println("e=[" + e + "]");
         } catch (Exception e) {
            System.out.println("e=[" + e + "]");
         } finally {
            try {
               if (pstmt != null)
                  pstmt.close();
               if (con != null)
                  con.close();
            } catch (SQLException e) {
            }
         }
         return retval;
      }

   //옷정보를 조회　　
   //服の情報を参照
      public ArrayList<ClothBasicVO> getClothInfo(String productCode) throws Exception {
         
         ArrayList<ClothBasicVO> list = new ArrayList<ClothBasicVO>();
         
         String dml = "SELECT "
               + "PRODUCT_CODE"
               + ",PRODUCT_NAME"
               + ",SIZE"
               + ",BRAND"
               + ",MATERIAL"
               + ",BUYDATE"
               + ",PRICE"
               + " FROM "
               + "CLOTH_BASIC "
               + "WHERE PRODUCT_CODE =?";
         
         Connection con = null;
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         ClothBasicVO retval = null;
         
         try {
            con = DBUtil.getConnection();
            
            pstmt = con.prepareStatement(dml);
            //조회 조건 　　
	    //照会の条件
            pstmt.setString(1, productCode);
            
            rs = pstmt.executeQuery();
            while (rs.next()) {
               retval = new ClothBasicVO(rs.getString(1)
                                       , rs.getString(2)
                                       , rs.getString(3)
                                       , rs.getString(4)
                                       , rs.getString(5)
                                       , rs.getString(6)
                                       , rs.getInt(7));
               list.add(retval);
//               System.out.println(retval);
            }
         } catch (SQLException se) {
            System.out.println(se);
         } catch (Exception e) {
            System.out.println(e);
         } finally {
            try {
               if (rs != null)
                  rs.close();
               if (pstmt != null)
                  pstmt.close();
               if (con != null)
                  con.close();
            } catch (SQLException se) {
            }
         }
         return list;
      }
}
