package com.jas.web.utils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class ResponseUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseUtil.class);

    public static final int RETURN_STATUS_SUCCESS = 0;
    public static final int RETURN_STATUS_FAILED = 1;

    public static JSONObject constructResponse(int status, String msg, Object data) {
        JSONObject jo = new JSONObject();
        jo.put("status", status);
        jo.put("msg", msg);
        jo.put("data", data);
        return jo;
    }

    public static void responseOutWithJson(HttpServletResponse response, String json) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(json);
        } catch (Exception e) {
            LOGGER.error("responseOutWithJson : 网络异常, json=" + json, e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
