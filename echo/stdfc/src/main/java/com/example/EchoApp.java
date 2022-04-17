package com.example;

import com.aliyun.fc.runtime.Context;
import com.aliyun.fc.runtime.FunctionInitializer;
import com.aliyun.fc.runtime.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EchoApp implements HttpRequestHandler, FunctionInitializer {

    @Override
    public void initialize(Context context) {
        context.getLogger().info("init echo application");
    }

    @Override
    public void handleRequest(HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse,
                              Context context) throws IOException {
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write("Hello from stdfc!");
        writer.flush();
    }

}
