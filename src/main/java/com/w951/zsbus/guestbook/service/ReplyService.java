package com.w951.zsbus.guestbook.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.w951.util.service.CommonService;
import com.w951.zsbus.guestbook.entity.Reply;

/**
 * 
 * 系统版本：v1.0<br>
 * 开发人员：Ccz<br>
 * 日期：2014-05-18<br>
 * 时间：16:57:07<br>
 * 功能描述：写明作用，调用方式，使用场景，以及特殊情况<br>
 *
 */
@Transactional
public interface ReplyService extends CommonService<Reply> {
	List<Reply> queryPageListByMsgId(String messageId, int page, int rows);
	long getCountByMsgId(String messageId);
	String deleteByMsgId(String messageId);
}