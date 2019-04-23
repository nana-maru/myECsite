package com.internousdev.ecsite.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.UserListDAO;
import com.internousdev.ecsite.dto.UserInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class UserListAction extends ActionSupport implements SessionAware{
	ArrayList<UserInfoDTO> userInfoDTOList = new ArrayList<UserInfoDTO>();
	private Map<String,Object> session;
	public String execute() throws SQLException {
		UserListDAO userListDAO = new UserListDAO();
		userInfoDTOList = userListDAO.getUserInfo();
		if(!(userInfoDTOList.size() > 0)) {
			userInfoDTOList = null;
		}
		String result = SUCCESS;
		return result;
}
	public Map<String,Object> getSession(){
		return session;
	}

	@Override
	public void setSession(Map<String,Object> session){
		this.session = session;
	}

	public ArrayList<UserInfoDTO> getUserInfoDTOList(){
		return this.userInfoDTOList;
	}
	public void setItemInfoDTOList(ArrayList<UserInfoDTO> userInfoDTOList) {
		this.userInfoDTOList = userInfoDTOList;
	}
}
