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
public class MessageDTO implements Serializable {
	private static final long serialVersionUID = -1L;

    private String messageId;
    private String messageTitle;
    private String messageContent;
    private String messageNiki;
    private String messageEmail;
    private String messageCompany;
    private String messageAddress;
    private String messagePhone;
    private String messageFax;
    private String messageCreatedate;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
    
    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }
    
    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
    
    public String getMessageNiki() {
        return messageNiki;
    }

    public void setMessageNiki(String messageNiki) {
        this.messageNiki = messageNiki;
    }
    
    public String getMessageEmail() {
        return messageEmail;
    }

    public void setMessageEmail(String messageEmail) {
        this.messageEmail = messageEmail;
    }
    
    public String getMessageCompany() {
        return messageCompany;
    }

    public void setMessageCompany(String messageCompany) {
        this.messageCompany = messageCompany;
    }
    
    public String getMessageAddress() {
        return messageAddress;
    }

    public void setMessageAddress(String messageAddress) {
        this.messageAddress = messageAddress;
    }
    
    public String getMessagePhone() {
        return messagePhone;
    }

    public void setMessagePhone(String messagePhone) {
        this.messagePhone = messagePhone;
    }
    
    public String getMessageFax() {
        return messageFax;
    }

    public void setMessageFax(String messageFax) {
        this.messageFax = messageFax;
    }
    
    public String getMessageCreatedate() {
        return messageCreatedate;
    }

    public void setMessageCreatedate(String messageCreatedate) {
        this.messageCreatedate = messageCreatedate;
    }
    

}