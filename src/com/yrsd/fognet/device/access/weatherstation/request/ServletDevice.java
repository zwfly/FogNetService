package com.yrsd.fognet.device.access.weatherstation.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 2017/6/17.
 */
@WebServlet(name = "ServletDevice", urlPatterns = "/yurunsd/weatherstation/device/items")
public class ServletDevice extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        System.out.println("ServletDevice doPost");




        response.getWriter().println("123qweqweqwe");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ServletDevice doget");



        response.getWriter().println("123qweqweqwe");
    }
}
