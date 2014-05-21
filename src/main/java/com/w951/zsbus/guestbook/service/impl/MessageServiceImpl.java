package com.w951.zsbus.guestbook.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.w951.zsbus.guestbook.entity.Message;
import com.w951.zsbus.guestbook.service.MessageService;
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
public class MessageServiceImpl implements MessageService {
	@Resource
	private HibernateDao hibernateDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public String delete(Message entity) {
		entity = get(entity.getMessageId());
		hibernateDao.delete(entity);
		return null;
	}

	public Message get(String id) {
		return hibernateDao.get(new Message(), id);
	}

	public long getCount() {
		return hibernateDao.getCount(new Message());
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public String insert(Message entity) {
		hibernateDao.insert(entity);
		return null;
	}

	public List<Message> queryList(String... order) {
		if (order != null) {
			return hibernateDao.queryList(new Message(), order);
		} else {
			return hibernateDao.queryList(new Message());
		}
	}

	public List<Message> queryPageList(int pageIndex, int pageSize,
			String... order) {
		if (order != null) {
			return hibernateDao.queryPageList(new Message(), pageIndex,
					pageSize, order);
		} else {
			return hibernateDao.queryPageList(new Message(), pageIndex,
					pageSize);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public String update(Message entity) {
		Message obj = get(entity.getMessageId());
		BeanUtil.beanToBean(entity, obj);
		hibernateDao.update(entity);
		return null;
	}

}