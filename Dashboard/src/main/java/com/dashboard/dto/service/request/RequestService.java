package com.dashboard.dto.service.request;

import javax.servlet.http.HttpServletRequest;

public interface RequestService {

    String getClientIp(HttpServletRequest request);

}