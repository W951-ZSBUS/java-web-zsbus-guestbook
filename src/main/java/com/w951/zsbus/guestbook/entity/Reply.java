package com.w951.zsbus.guestbook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "zsbus_guestbook_reply")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Reply implements java.io.Serializable {
	private static final long serialVersionUID = -809269410176998371L;
	private String replyId;
	private Message message;
	private String replyContent;
	private String replyCreatename;
	private String replyCreatedate;

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "reply_id", unique = true, nullable = false, length = 32)
	public String getReplyId() {
		return this.replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "message_id")
	public Message getMessage() {
		return this.message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	@Column(name = "reply_content", length = 500)
	public String getReplyContent() {
		return this.replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	@Column(name = "reply_createname", length = 20)
	public String getReplyCreatename() {
		return this.replyCreatename;
	}

	public void setReplyCreatename(String replyCreatename) {
		this.replyCreatename = replyCreatename;
	}

	@Column(name = "reply_createdate", length = 19)
	public String getReplyCreatedate() {
		return this.replyCreatedate;
	}

	public void setReplyCreatedate(String replyCreatedate) {
		this.replyCreatedate = replyCreatedate;
	}

}