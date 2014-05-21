package com.w951.zsbus.guestbook.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "zsbus_guestbook_message")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Message implements java.io.Serializable {
	private static final long serialVersionUID = -4694958295553838992L;
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
	private List<Reply> replies = new ArrayList<Reply>();

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "message_id", unique = true, nullable = false, length = 32)
	public String getMessageId() {
		return this.messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	@Column(name = "message_title", length = 50)
	public String getMessageTitle() {
		return this.messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	@Column(name = "message_content", length = 500)
	public String getMessageContent() {
		return this.messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	@Column(name = "message_niki", length = 20)
	public String getMessageNiki() {
		return this.messageNiki;
	}

	public void setMessageNiki(String messageNiki) {
		this.messageNiki = messageNiki;
	}

	@Column(name = "message_email", length = 50)
	public String getMessageEmail() {
		return this.messageEmail;
	}

	public void setMessageEmail(String messageEmail) {
		this.messageEmail = messageEmail;
	}

	@Column(name = "message_company", length = 50)
	public String getMessageCompany() {
		return this.messageCompany;
	}

	public void setMessageCompany(String messageCompany) {
		this.messageCompany = messageCompany;
	}

	@Column(name = "message_address", length = 100)
	public String getMessageAddress() {
		return this.messageAddress;
	}

	public void setMessageAddress(String messageAddress) {
		this.messageAddress = messageAddress;
	}

	@Column(name = "message_phone", length = 20)
	public String getMessagePhone() {
		return this.messagePhone;
	}

	public void setMessagePhone(String messagePhone) {
		this.messagePhone = messagePhone;
	}

	@Column(name = "message_fax", length = 20)
	public String getMessageFax() {
		return this.messageFax;
	}

	public void setMessageFax(String messageFax) {
		this.messageFax = messageFax;
	}

	@Column(name = "message_createdate", length = 19)
	public String getMessageCreatedate() {
		return this.messageCreatedate;
	}

	public void setMessageCreatedate(String messageCreatedate) {
		this.messageCreatedate = messageCreatedate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "message")
	public List<Reply> getReplies() {
		return this.replies;
	}

	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}

}