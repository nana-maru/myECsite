package com.internousdev.ecsite.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.MyPageDAO;
import com.internousdev.ecsite.dto.MyPageDTO;
import com.opensymphony.xwork2.ActionSupport;

public class MyPageAction extends ActionSupport implements SessionAware{
	public Map<String,Object> session;
	private String deleteFlg;
	private String message;
	ArrayList<MyPageDTO> myPageList = new ArrayList<MyPageDTO>();

	public String execute() throws SQLException{
		MyPageDAO myPageDAO = new MyPageDAO();

		if(!session.containsKey("id")){
			return ERROR;
		}
		//商品履歴を削除しない場合
		if(deleteFlg == null){
			String item_transaction_id = session.get("id").toString();
			String user_master_id = session.get("login_user_id").toString();
			myPageList = myPageDAO.getMyPageUserInfo(item_transaction_id, user_master_id);

		//商品履歴を削除する場合
		}else if(deleteFlg.equals("1")){
			delete();
		}
		String result = SUCCESS;
		return result;
	}

	public void delete() throws SQLException{
		MyPageDAO myPageDAO = new MyPageDAO();

		String item_transaction_id = session.get("id").toString();
		String user_master_id = session.get("login_user_id").toString();

		int res = myPageDAO.buyItemHistoryDelete(item_transaction_id, user_master_id);

		if(res > 0){
			session.put("message", "商品情報を正しく削除しました。");
		}else if(res == 0){
			session.put("message","商品情報の削除に失敗しました。");
		}
	}

	public String getDeleteFlg(){
		return deleteFlg;
	}

	public void setDeleteFlg(String deleteFlg){
		this.deleteFlg = deleteFlg;
	}

	public Map<String,Object> getSession(){
		return session;
	}

	@Override
	public void setSession(Map<String,Object> loginSessionMap){
		this.session = loginSessionMap;
	}

	public ArrayList<MyPageDTO> getMyPageList(){
		return this.myPageList;
	}
//
//	public ArrayList<MyPageDTO> setMyPageList(ArrayList<MyPageDTO> myPageList){
//		this.myPageList = myPageList;
//	}

	public String getMessage(){
		return message;
	}

	public void getMessage(String message){
		this.message = message;
	}
}
