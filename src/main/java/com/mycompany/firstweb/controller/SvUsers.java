package com.mycompany.firstweb.controller;

import com.mycompany.firstweb.dto.ResultDTO;
import com.mycompany.firstweb.dto.UserDTO;
import com.mycompany.firstweb.service.UserService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author Artist-Code
 */
@WebServlet(name = "usersServlet", urlPatterns = {"/users"})
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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ResultDTO<List<UserDTO>> resultDTO = userService.findAll();
        if (resultDTO.getData() == null || !resultDTO.isSuccess()) {
            sendJsonError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, resultDTO.getErrorMessage());
            return;
        }
        try {
            JSONArray jsonArray = new JSONArray(resultDTO.getData());
            response.getWriter().write(jsonArray.toString());

        } catch (JSONException e) {
            sendJsonError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
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
            sendJsonError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Expected JSON request");
            return;
        }

        try {
            //Converting the JSON to a object and save it
            JSONObject jsonObject = new JSONObject(new JSONTokener(request.getReader()));
            if (!jsonObject.has("name") || !jsonObject.has("lastName") || !jsonObject.has("phone")) {
                sendJsonError(response, HttpServletResponse.SC_BAD_REQUEST, "Missing required fields");
                return;
            }

            UserDTO userDTO = new UserDTO(
                    jsonObject.getString("name"),
                    jsonObject.getString("lastName"),
                    jsonObject.getString("phone"));
            ResultDTO<UserDTO> result = userService.create(userDTO);

            //Verifing if the save was success
            if (!result.isSuccess() || result.getData() == null) {
                sendJsonError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, result.getErrorMessage());
                return;
            }

            //Creating the response with JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_CREATED);

            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("message", "User created correclty");

            response.getWriter().write(jsonResponse.toString());
        } catch (JSONException e) {
            sendJsonError(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON format");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long idUser = Long.valueOf(request.getParameter("id"));
            ResultDTO<String> result = userService.deleteById(idUser);

            if (result.getData() == null || !result.isSuccess()) {
                sendJsonError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, result.getErrorMessage());
                return;
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(new JSONObject().put("message", "User deleted succesfully").toString());
        } catch(NumberFormatException e) {
            sendJsonError(response, HttpServletResponse.SC_BAD_REQUEST, "ID invalid");
        }

    }

    private void sendJsonError(HttpServletResponse response, int statusCode, String message) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(statusCode);
        response.getWriter().write(new JSONObject().put("Error", message).toString());
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "User API Servlet";
    }// </editor-fold>

}
