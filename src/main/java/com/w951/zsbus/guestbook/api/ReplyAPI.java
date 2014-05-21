package com.w951.zsbus.guestbook.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.w951.util.action.CommonBaseAction;
import com.w951.util.bean.BeanUtil;
import com.w951.util.date.DateUtil;
import com.w951.zsbus.guestbook.dto.ReplyDTO;
import com.w951.zsbus.guestbook.entity.Reply;
import com.w951.zsbus.guestbook.service.ReplyService;

public class ReplyAPI extends CommonBaseAction {
	private static final long serialVersionUID = -1L;
	private JSONObject result;
	private JSONArray resultArray;
	private Map<String, Object> request;
	private Map<String, Object> session;
	
	@Resource
	private ReplyService replyService;
	
	//parames
	private Reply reply;
	private int page;
	private int rows;
	private String messageId;
	
	// Action
	//查看单条留言的回复
	public String queryByMsgId() throws Exception {
		List<Reply> list = replyService.queryPageListByMsgId(messageId, page, rows);

		ReplyDTO dto = null;
		List<ReplyDTO> dtos = new ArrayList<ReplyDTO>();
		if (list != null && list.size() > 0) {
			for (Reply obj : list) {
				dto = new ReplyDTO();
				BeanUtil.beanToBean(dto, obj);
				dtos.add(dto);
			}
		}

		jsonData.put("total", replyService.getCountByMsgId(messageId));
		jsonData.put("rows", dtos);
		result = JSONObject.fromObject(jsonData);

		return SUCCESS;
	}
	
	//回复单条留言
	public String insertReply() throws Exception {
		reply.setReplyCreatedate(DateUtil.getDateTime());
		String msg = replyService.insert(reply);

		if (msg != null) {
			jsonData.put("message", msg);
		}
		
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

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
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

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
}
