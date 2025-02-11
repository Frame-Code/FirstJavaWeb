package com.mycompany.firstweb.controller;

import com.mycompany.firstweb.dto.ResultDTO;
import com.mycompany.firstweb.dto.UserDTO;
import com.mycompany.firstweb.service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Source;

/**
 *
 * @author Artist-Code
 */
@WebServlet(name = "SvUsers", urlPatterns = {"/SvUsers"})
public class SvUsers extends HttpServlet {

    private final UserService userService = new UserService();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvUsers</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvUsers at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        //processRequest(request, response);
        ResultDTO<List<UserDTO>> resultDTO = userService.findAll();
        /*if (resultDTO.getData() == null || !resultDTO.isSuccess()) {
            request.setAttribute("alertMessage", resultDTO.getErrorMessage());
            request.getRequestDispatcher("showUsers.jsp").forward(request, response);
        } else if (resultDTO.getData().isEmpty()) {
            request.setAttribute("alertMessage", "Users not founded");
            request.getRequestDispatcher("showUsers.jsp").forward(request, response);
        }*/
        
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
        //processRequest(request, response);
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        //UserDTO userDTO = new UserDTO(name, lastName, phone);
        //userService.create(userDTO);
        System.out.println(request.getRequestURI());
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
