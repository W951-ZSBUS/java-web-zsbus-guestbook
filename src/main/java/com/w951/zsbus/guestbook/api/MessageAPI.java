package com.w951.zsbus.guestbook.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.w951.util.action.CommonBaseAction;
import com.w951.util.bean.BeanUtil;
import com.w951.zsbus.guestbook.dto.MessageDTO;
import com.w951.zsbus.guestbook.entity.Message;
import com.w951.zsbus.guestbook.service.MessageService;

public class MessageAPI extends CommonBaseAction {
	private static final long serialVersionUID = -1L;
	private JSONObject result;
	private JSONArray resultArray;
	private Map<String, Object> request;
	private Map<String, Object> session;
	
	@Resource
	private MessageService messageService;
	
	//parames
	private int page;
	private int rows;
	
	// Action
	
	public String query() throws Exception {
		List<Message> list = messageService.queryPageList(page, rows, "messageCreatedate", "DESC");

		MessageDTO dto = null;
		List<MessageDTO> dtos = new ArrayList<MessageDTO>();
		if (list != null && list.size() > 0) {
			for (Message obj : list) {
				dto = new MessageDTO();
				BeanUtil.beanToBean(dto, obj);
				dtos.add(dto);
			}
		}

		jsonData.put("total", messageService.getCount());
		jsonData.put("rows", dtos);
		result = JSONObject.fromObject(jsonData);

		return SUCCESS;
	}
	
	// getter setter

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public JSONArray getResultArray() {
		return resultArray;
	}

	public void setResultArray(JSONArray resultArray) {
		this.resultArray = resultArray;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
}
