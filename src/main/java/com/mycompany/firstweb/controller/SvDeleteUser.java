package com.mycompany.firstweb.controller;

import com.mycompany.firstweb.dto.ResultDTO;
import com.mycompany.firstweb.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author Artist-Code
 */
@WebServlet(name = "delete", urlPatterns = {"/delete"})
public class SvDeleteUser extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        } catch (NumberFormatException e) {
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
        return "Short description";
    }// </editor-fold>

}
