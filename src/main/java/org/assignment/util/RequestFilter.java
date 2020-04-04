package org.assignment.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@ControllerAdvice
public class RequestFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		try {
			log.info("Request for api: " + httpRequest.getRequestURI());

			String mdcData = httpRequest.getHeader("microtime");

			if (mdcData == null || mdcData.isEmpty()) {
				mdcData = "" + System.currentTimeMillis();
			}
			MDC.put("mdcData", mdcData); // Referenced from logging configuration.
			chain.doFilter(request, response);

		} catch (Exception e) {

			String errorMsg = "Exception in api : " + httpRequest.getRequestURI();
			log.error(errorMsg, e);
			throw e;
		} finally {
			MDC.clear();
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}