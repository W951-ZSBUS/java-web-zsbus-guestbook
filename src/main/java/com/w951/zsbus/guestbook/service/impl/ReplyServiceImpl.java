package com.w951.zsbus.guestbook.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.w951.zsbus.guestbook.entity.Reply;
import com.w951.zsbus.guestbook.service.ReplyService;
import com.w951.orm.hibernate.HibernateDao;
import com.w951.util.bean.BeanUtil;

/**
 * 
 * 系统版本：v1.0<br>
 * 开发人员：Ccz<br>
 * 日期：2014-05-18<br>
 * 时间：16:57:07<br>
 * 功能描述：写明作用，调用方式，使用场景，以及特殊情况<br>
 *
 */
@Component
public class ReplyServiceImpl implements ReplyService {
	@Resource
	private HibernateDao hibernateDao;
	
	private static final String QUERY_REPLY_BY_MSGID = "FROM Reply t WHERE t.message.messageId = :messageId ORDER BY t.replyCreatedate DESC";
	private static final String COUNT_REPLY_BY_MSGID = "SELECT COUNT(*) FROM Reply t WHERE t.message.messageId = :messageId";
	private static final String DELETE_REPLY_BY_MSGID = "DELETE FROM Reply t WHERE t.message.messageId = :messageId";
	
	@Transactional(propagation = Propagation.REQUIRED)
	public String delete(Reply entity) {
		entity = get(entity.getReplyId());
		hibernateDao.delete(entity);
		return null;
	}

	public Reply get(String id) {
		return hibernateDao.get(new Reply(), id);
	}

	public long getCount() {
		return hibernateDao.getCount(new Reply());
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public String insert(Reply entity) {
		hibernateDao.insert(entity);
		return null;
	}

	public List<Reply> queryList(String... order) {
		if (order != null) {
			return hibernateDao.queryList(new Reply(), order);
		} else {
			return hibernateDao.queryList(new Reply());
		}
	}

	public List<Reply> queryPageList(int pageIndex, int pageSize,
			String... order) {
		if (order != null) {
			return hibernateDao.queryPageList(new Reply(), pageIndex,
					pageSize, order);
		} else {
			return hibernateDao.queryPageList(new Reply(), pageIndex,
					pageSize);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public String update(Reply entity) {
		Reply obj = get(entity.getReplyId());
		BeanUtil.beanToBean(entity, obj);
		hibernateDao.update(entity);
		return null;
	}
	
	/*----------自定义接口----------*/
	public List<Reply> queryPageListByMsgId(String messageId, int pageIndex, int pageSize) {
		return hibernateDao.queryPageListByHql(QUERY_REPLY_BY_MSGID,pageIndex,pageSize,
				new String[][] { new String[] { "messageId", messageId } }); 
	}
	
	public long getCountByMsgId(String messageId) {
		return hibernateDao.getCountByHql(COUNT_REPLY_BY_MSGID, new String[][] { new String[] { "messageId", messageId } });
	}
	
	public String deleteByMsgId(String messageId) {
		hibernateDao.excuteHQL(DELETE_REPLY_BY_MSGID, new String[][] { new String[] { "messageId", messageId } });
		return null;
	}


}