package com.mycompany.firstweb.controller;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        // ResultDTO<List<UserDTO>> resultDTO = userService.findAll();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        List<UserDTO> usersDTO = userService.findAll().getData();
        try {
            JSONArray jsonArray = new JSONArray(usersDTO);
            response.getWriter().write(jsonArray.toString());
            
        } catch(JSONException e) {
            response.sendError(HttpServletResponse.SC_CONFLICT, e.getMessage());
        }

        /*
        //Adding an attribute to the new session
        HttpSession session = request.getSession();
        session.setAttribute("ListUsers", usersDTO);

        //Redirecting to  jsp showUsers
        response.sendRedirect("showUsers.jsp");*/
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

        //Verifing if the request have a json format
        String contentType = request.getContentType();
        if (contentType == null || !contentType.equals("application/json")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "JSON format unrecognized");
            return;
        }

        //Read the body of the request
        BufferedReader reader = request.getReader();
        StringBuilder jsonString = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonString.append(line);
        }

        if (jsonString.toString().trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "The body of the request is empty");
            return;
        }

        try {
            //Converting the JSON to a object and save it
            JSONObject jsonObject = new JSONObject(jsonString.toString());
            UserDTO userDTO = new UserDTO(jsonObject.getString("name"),
                    jsonObject.getString("lastName"),
                    jsonObject.getString("phone"));
            ResultDTO<UserDTO> result = userService.create(userDTO);

            //Verifing if the save was success
            if (!result.isSuccess() || result.getData() == null) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, result.getErrorMessage());
                return;
            }

            //Creating the response with JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_CREATED);

            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("message", "User created correclty");

            response.getWriter().write(jsonResponse.toString());

            response.sendRedirect("index.html");
        } catch (JSONException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON");
        }
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
