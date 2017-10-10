package com.pai.app.web.core.framework.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.filter.OncePerRequestFilter;

public class GZIPFilter extends OncePerRequestFilter {
	private final transient Log log = LogFactory.getLog(GZIPFilter.class);
	private final static int minThreshold = 128;
	protected int compressionThreshold = 0;

	public void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (isGzipSupported(request)) {
			if (logger.isDebugEnabled())
				logger.debug("GZIP supported, compressing response!");
			CompressionServletResponseWrapper wrappedResponse = new CompressionServletResponseWrapper(
					(HttpServletResponse) response);
			wrappedResponse.setCompressionThreshold(compressionThreshold);

			try {
				chain.doFilter(request, wrappedResponse);
				return;
			} finally {
				wrappedResponse.finishResponse();
			}
		}
		chain.doFilter(request, response);
	}		

	@Override
	protected void initFilterBean() throws ServletException {
		String str = super.getFilterConfig().getInitParameter(
				"compressionThreshold");
		if (str != null) {
			compressionThreshold = Integer.parseInt(str);
			if (compressionThreshold != 0
					&& compressionThreshold < minThreshold) {
				if (logger.isDebugEnabled()) {
					logger
							.debug("compressionThreshold should be either 0 - no compression or >= "
									+ minThreshold);
					logger.debug("compressionThreshold set to " + minThreshold);
				}
				compressionThreshold = minThreshold;
			}
		} else {
			if(logger.isDebugEnabled())
				logger.debug("parameter is null, compressionThreshold set to " + minThreshold);
			compressionThreshold = minThreshold;
		}
	}

	/**
	 * Test for GZIP cababilities. Optimized to be used together with
	 * CacheFilter.
	 * 
	 * @param request
	 *            The current user request
	 * @return boolean indicating GZIP support
	 */
	private boolean isGzipSupported(HttpServletRequest request) {
		String s = request.getParameter("gzip");
		if ("false".equals(s)) {
			return false;
		}
		// If not cached, don't zip.
//		if (request.getAttribute("__oscache_filtered__cacheFilter") == null) {
//			return false;
//		}

		// test if browser can accept gzip encoding
		String browserEncodings = request.getHeader("accept-encoding");
		return ((browserEncodings != null) && (browserEncodings.indexOf("gzip") != -1));
	}

}
