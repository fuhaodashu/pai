package com.pai.service.mail;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import com.pai.service.mail.entity.EmailPerson;
import com.pai.service.mail.entity.MailVo;

public class MailUtil {
	protected static Log logger = LogFactory.getLog(MailUtil.class);
	protected static String encoding = "UTF-8";
	protected static boolean debug = true; // debug=true, 会print传送信息

	public static String send(MailVo mailVo)
			throws EmailException {

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~
		// create Email by type
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~
		boolean hasAttachments = false;
		Email email = null;
		if (mailVo.isHtml()) {
			email = new HtmlEmail();
		}else if (!ArrayUtils.isEmpty(mailVo.getAttachments())) {
			email = new MultiPartEmail();
			hasAttachments = true;		
		} else {
			email = new SimpleEmail();
		}
		email.setCharset(encoding);
		email.setHostName(mailVo.getServerIp());
		email.setSSL(mailVo.isSsl());
		email.setCharset(mailVo.getCharset());
		email.setSmtpPort(mailVo.getPort());
		if (mailVo.isSsl()) {
			email.setSslSmtpPort(mailVo.getPort() + "");
		}
		email.setFrom(mailVo.getFrom().getEmail(), mailVo.getFrom().getName(), encoding);
		email.setSubject(mailVo.getSubject());
		email.setDebug(debug);

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~
		// set message
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~
		if (mailVo.isHtml()) {
			((HtmlEmail) email).setHtmlMsg(mailVo.getMsg());
			email.setContent(mailVo.getMsg(), Email.TEXT_HTML);
		}else if (hasAttachments) {
			((MultiPartEmail) email).setMsg(mailVo.getMsg());
		}else {
			email.setMsg(mailVo.getMsg());
		}

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~
		// add TO, CC, BCC
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~
		if (mailVo.getTos()!=null && mailVo.getTos().size()>0) {
			for (EmailPerson iEmailPerson:mailVo.getTos()) {
				email.addTo(iEmailPerson.getEmail(),iEmailPerson.getName(), encoding);
			}
		}
		if (mailVo.getCc()!=null && mailVo.getCc().size()>0) {
			for (EmailPerson iEmailPerson:mailVo.getCc()) {
				email.addCc(iEmailPerson.getEmail(),iEmailPerson.getName(), encoding);
			}
		}
		if (mailVo.getBcc()!=null && mailVo.getBcc().size()>0) {
			for (EmailPerson iEmailPerson:mailVo.getBcc()) {
				email.addBcc(iEmailPerson.getEmail(),iEmailPerson.getName(), encoding);
			}
		}

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~
		// add 'attachments'
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~
		if (!ArrayUtils.isEmpty(mailVo.getAttachments())) {
			MultiPartEmail mail = (MultiPartEmail) email;
			for (int i = 0, max = mailVo.getAttachments().length; i < max; i++) {
				mail.attach(mailVo.getAttachments()[i]);
			}
		}

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~
		// authentication
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~
		if (!StringUtils.isEmpty(mailVo.getUsername()) && !StringUtils.isEmpty(mailVo.getPassword())) {
			email.setAuthentication(mailVo.getUsername(), mailVo.getPassword());
		}
		email.setSocketConnectionTimeout(2000);
		email.setSocketTimeout(5000);
		String ret = email.send();
		return ret;
	}	

	public static boolean getDebugMode() {
		return debug;
	}

	public static void setDebugMode(boolean flg) {
		debug = flg;
	}
}
