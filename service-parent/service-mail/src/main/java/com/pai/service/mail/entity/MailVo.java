package com.pai.service.mail.entity;

import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;

import com.pai.base.api.constants.Constants;
import com.pai.base.api.constants.MsgType;
import com.pai.base.api.helper.IConfigHelper;
import com.pai.base.api.model.IMsgVo;
import com.pai.base.core.util.string.StringUtils;

@SuppressWarnings("serial")
public class MailVo implements IMsgVo{
	private String serverIp;
	private int port;
	private boolean ssl;
	private String username;
	private String password;
	
	private EmailPerson fromEmailPerson;
	private Set<EmailPerson> tos = new HashSet<EmailPerson>();
	private Set<EmailPerson> cc = new HashSet<EmailPerson>();
	private Set<EmailPerson> bcc = new HashSet<EmailPerson>();
	
	private String subject;
	private String msg;
	private EmailAttachment attachments[];
	private boolean isHtml;
	private String charset = Constants.DEFAULT_ENCODING;

	private long preDelayMS=0L;
	private long postDelayMS=0L;
	private Map<String, Object> paramsMap;
	
	public MailVo(IConfigHelper helper){	
		reloadConfig(helper);
	}
	
	public MailVo(String serverIp,int port,boolean ssl,String username,String password,String fromEmail,String fromName){
		this.serverIp = serverIp;
		this.port = port;
		this.ssl = ssl;
		this.username = username;
		this.password = password;
		if(StringUtils.isNotEmpty(fromEmail)){
			if(StringUtils.isNotEmpty(fromName)){
				fromEmailPerson = new EmailPerson(fromEmail, fromName);		
			}else {
				fromEmailPerson = new EmailPerson(fromEmail);
			}
		}		
	}	
	
	private MailVo reloadConfig(IConfigHelper helper){		
		this.serverIp = helper.getParamValue(Constants.MAIL.SERVERIP);
		this.port = Integer.valueOf(helper.getParamValue(Constants.MAIL.PORT));
		this.ssl = helper.getParamValue(Constants.MAIL.SSL).equals("Y")?true:false;
		this.username = helper.getParamValue(Constants.MAIL.USERNAME);
		this.password = helper.getParamValue(Constants.MAIL.PASSWORD);
		String fromEmail = helper.getParamValue(Constants.MAIL.FROM);
		String fromName = helper.getParamValue(Constants.MAIL.FROM_NAME);
		fromEmailPerson = new EmailPerson(fromEmail, fromName);
		return this;
	}
	
	public MailVo addTo(String email){
		EmailPerson emailPerson = new EmailPerson(email,email);
		tos.add(emailPerson);
		return this;
	}
	
	public MailVo addTo(String email,String name){
		EmailPerson emailPerson = new EmailPerson(email,name);
		tos.add(emailPerson);
		return this;
	}
	
	public MailVo addCC(String email){
		EmailPerson emailPerson = new EmailPerson(email,email);
		tos.add(emailPerson);
		return this;
	}
	
	public MailVo addCC(String email,String name){
		EmailPerson emailPerson = new EmailPerson(email,name);
		tos.add(emailPerson);
		return this;
	}
	
	public MailVo addBcc(String email){
		EmailPerson emailPerson = new EmailPerson(email,email);
		tos.add(emailPerson);
		return this;
	}
	
	public MailVo addBcc(String email,String name){
		EmailPerson emailPerson = new EmailPerson(email,name);
		tos.add(emailPerson);
		return this;
	}	

	public MailVo setContent(String subject,String msg,boolean isHtml) throws EmailException {
		this.subject = subject;
		this.msg = msg;
		this.isHtml = isHtml;	
		return this;
	}
	
	public MailVo setContent(String subject,String msg,boolean isHtml,File[] files) throws EmailException {
		this.subject = subject;
		this.msg = msg;
		this.isHtml = isHtml;		
		this.attachments = convertAttachments(files);
		return this;
	}
	
	public String getMsgType() {
		return MsgType.EMAIL.key();
	}

	public String getServerIp() {
		return serverIp;
	}

	public int getPort() {
		return port;
	}

	public boolean isSsl() {
		return ssl;
	}

	public String getSubject() {
		return subject;
	}
	public String getMsg() {
		return msg;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public EmailAttachment[] getAttachments() {
		return attachments;
	}
	public boolean isHtml() {
		return isHtml;
	}
	public String getCharset() {
		return charset;
	}

	public EmailPerson getFrom() {
		return fromEmailPerson;
	}

	public Set<EmailPerson> getTos() {
		return tos;
	}

	public Set<EmailPerson> getCc() {
		return cc;
	}

	public Set<EmailPerson> getBcc() {
		return bcc;
	}

	public long getPreDelayMS() {
		return preDelayMS;
	}

	public void setPreDelayMS(long preDelayMS) {
		this.preDelayMS = preDelayMS;
	}

	public long getPostDelayMS() {
		return postDelayMS;
	}

	public void setPostDelayMS(long postDelayMS) {
		this.postDelayMS = postDelayMS;
	}

	public Map<String, Object> getParamsMap() {
		return paramsMap;
	}

	public void setParamsMap(Map<String, Object> paramsMap) {
		this.paramsMap = paramsMap;
	}
	
	protected EmailAttachment[] convertAttachments(File files[])
			throws EmailException {
		if(files==null || files.length==0){
			return null;
		}
		try {
			EmailAttachment[] attachments = null;
			if (!ArrayUtils.isEmpty(files)) {
				attachments = new EmailAttachment[files.length];
				for (int i = 0, max = files.length; i < max; i++) {
					EmailAttachment att = new EmailAttachment();
					att.setDisposition(EmailAttachment.ATTACHMENT);
					att.setURL(files[i].toURL());
					att.setName(files[i].getName());
					attachments[i] = att;
				}
			}
			return attachments;
		} catch (Exception e) {
			throw new EmailException(e.getMessage(), e);
		}

	}
}
