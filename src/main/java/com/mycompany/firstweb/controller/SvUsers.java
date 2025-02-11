package com.mycompany.firstweb.controller;

import com.google.gson.Gson;
import com.mycompany.firstweb.dto.ResultDTO;
import com.mycompany.firstweb.dto.UserDTO;
import com.mycompany.firstweb.service.UserService;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Artist-Code
 */
@WebServlet(name = "SvUsers", urlPatterns = {"/SvUsers"})
public class SvUsers extends HttpServlet {

    private final UserService userService = new UserService();

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //ResultDTO<List<UserDTO>> resultDTO = userService.findAll();

        List<UserDTO> usersDTO = userService.findAll().getData();

        //Adding an attribute to the new session
        HttpSession session = request.getSession();
        session.setAttribute("ListUsers", usersDTO);

        //Redirecting to  jsp showUsers
        response.sendRedirect("showUsers.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet- specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        UserDTO userDTO = gson.fromJson(reader, UserDTO.class);
        System.out.println(userDTO);
        //userService.create(userDTO);
        response.sendRedirect("index.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
