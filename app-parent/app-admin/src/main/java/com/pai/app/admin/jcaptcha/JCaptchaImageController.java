package com.pai.app.admin.jcaptcha;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.octo.captcha.service.image.ImageCaptchaService;

@Controller
@RequestMapping("")
public class JCaptchaImageController {
	@Resource(name = "imageCaptchaService")
	private ImageCaptchaService imageCaptchaService;

	@RequestMapping(value = "/generateImage")
	public void ImageCaptcha(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String captchaId = request.getSession().getId();
		// call the ImageCaptchaService getChallenge method
		BufferedImage challenge = imageCaptchaService.getImageChallengeForID(
				captchaId, request.getLocale());

		// flush it in the response
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		ServletOutputStream responseOutputStream = response.getOutputStream();

		// 将图像输出到Servlet输出流中。
		ImageIO.write(challenge, "jpeg", responseOutputStream);
		responseOutputStream.flush();
		responseOutputStream.close();
	}
}
