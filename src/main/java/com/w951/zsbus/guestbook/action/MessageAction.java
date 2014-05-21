package com.w951.zsbus.guestbook.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.w951.util.action.CommonAction;
import com.w951.util.bean.BeanUtil;
import com.w951.util.date.DateUtil;
import com.w951.util.dto.User;
import com.w951.zsbus.guestbook.dto.MessageDTO;
import com.w951.zsbus.guestbook.dto.ReplyDTO;
import com.w951.zsbus.guestbook.entity.Message;
import com.w951.zsbus.guestbook.entity.Reply;
import com.w951.zsbus.guestbook.service.MessageService;
import com.w951.zsbus.guestbook.service.ReplyService;

public class MessageAction extends CommonAction {
	private static final long serialVersionUID = -1L;
	private JSONObject result;
	private JSONArray resultArray;
	private Map<String, Object> request;
	private Map<String, Object> session;

	@Resource
	private MessageService messageService;
	@Resource
	private ReplyService replyService;
	
	// 参数

	private Message message;
	private Reply reply;
	private int page;
	private int rows;
	
	// Action
	
	@Override
	public String insert() throws Exception {
		message.setMessageCreatedate(DateUtil.getDateTime());
		String msg = messageService.insert(message);

		if (msg != null) {
			jsonData.put("message", msg);
		}
		
		result = JSONObject.fromObject(jsonData);

		return SUCCESS;
	}

	@Override
	public String delete() throws Exception {
		String msg = messageService.delete(message);

		if (msg != null) {
			jsonData.put("message", msg);
		}
		
		result = JSONObject.fromObject(jsonData);

		return SUCCESS;
	}

	@Override
	public String update() throws Exception {
		String msg = messageService.update(message);

		if (msg != null) {
			jsonData.put("message", msg);
		}
		
		result = JSONObject.fromObject(jsonData);

		return SUCCESS;
	}

	@Override
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
	
	//查看单条留言的回复
	public String queryByMsgId() throws Exception {
		List<Reply> list = replyService.queryPageListByMsgId(reply.getMessage().getMessageId(), page, rows);

		ReplyDTO dto = null;
		List<ReplyDTO> dtos = new ArrayList<ReplyDTO>();
		if (list != null && list.size() > 0) {
			for (Reply obj : list) {
				dto = new ReplyDTO();
				BeanUtil.beanToBean(dto, obj);
				dtos.add(dto);
			}
		}

		jsonData.put("total", replyService.getCountByMsgId(reply.getMessage().getMessageId()));
		jsonData.put("rows", dtos);
		result = JSONObject.fromObject(jsonData);

		return SUCCESS;
	}
	
	//回复单条留言
	public String insertReply() throws Exception {
		User admin = (User) session.get("admin");
		reply.setReplyCreatename(admin.getUserName());
		reply.setReplyCreatedate(DateUtil.getDateTime());
		String msg = replyService.insert(reply);

		if (msg != null) {
			jsonData.put("message", msg);
		}
		
		result = JSONObject.fromObject(jsonData);

		return SUCCESS;
	}
	
	//删除单条回复
	public String deleteReply() throws Exception {
		String msg = replyService.delete(reply);

		if (msg != null) {
			jsonData.put("message", msg);
		}
		
		result = JSONObject.fromObject(jsonData);

		return SUCCESS;
	}
	
	//删除单条留言的全部回复
	public String deleteReplyByMsgId() throws Exception {
		String msg = replyService.deleteByMsgId(reply.getMessage().getMessageId());

		if (msg != null) {
			jsonData.put("message", msg);
		}
		
		result = JSONObject.fromObject(jsonData);

		return SUCCESS;
	}

	// getter setter

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

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
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

	public JSONArray getResultArray() {
		return resultArray;
	}

	public void setResultArray(JSONArray resultArray) {
		this.resultArray = resultArray;
	}

}
