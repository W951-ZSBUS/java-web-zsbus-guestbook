package com.w951.zsbus.guestbook.dto;

import java.io.Serializable;

/**
 * 
 * 系统版本：v1.0<br>
 * 开发人员：Ccz<br>
 * 日期：2014-05-18<br>
 * 时间：16:57:06<br>
 * 功能描述：写明作用，调用方式，使用场景，以及特殊情况<br>
 *
 */
public class ReplyDTO implements Serializable {
	private static final long serialVersionUID = -1L;

    private String replyId;
    private String messageId;
    private String replyContent;
    private String replyCreatename;
    private String replyCreatedate;

    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }
    
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
    
    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }
    
    public String getReplyCreatename() {
        return replyCreatename;
    }

    public void setReplyCreatename(String replyCreatename) {
        this.replyCreatename = replyCreatename;
    }
    
    public String getReplyCreatedate() {
        return replyCreatedate;
    }

    public void setReplyCreatedate(String replyCreatedate) {
        this.replyCreatedate = replyCreatedate;
    }
    

}