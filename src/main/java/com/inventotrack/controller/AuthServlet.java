package com.inventotrack.controller;

import com.inventotrack.dto.AuthRequest;
import com.inventotrack.dto.AuthResponse;
import com.inventotrack.factory.ServiceFactory;
import com.inventotrack.service.AuthService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/auth/*")
public class AuthServlet extends BaseServlet {

    private final AuthService authService =
            ServiceFactory.getAuthService();

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws IOException {

        try {

            String path = req.getPathInfo();

            if ("/login".equals(path)) {

                AuthRequest request =
                        readJson(req, AuthRequest.class);

                AuthResponse response =
                        authService.login(
                                request,
                                req.getSession(true));

                writeSuccess(resp,
                        "Login Successful",
                        response);

            }
            else if("/logout".equals(path)){

                authService.logout(req.getSession(false));

                writeSuccess(resp,
                        "Logout Successful",
                        null);

            }
            else{

                resp.sendError(404);

            }

        }catch (Exception e){

            handleException(resp,e);

        }

    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {
        System.out.println("PathInfo = " + req.getPathInfo());
        System.out.println("RequestURI = " + req.getRequestURI());

        try{

            if("/me".equals(req.getPathInfo())){

                AuthResponse response =
                        authService.getCurrentUser(
                                req.getSession(false));

                writeSuccess(resp,
                        "Current User",
                        response);

            }else{

                resp.sendError(404);

            }

        }catch (Exception e){

            handleException(resp,e);

        }

    }

}