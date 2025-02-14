package com.mycompany.firstweb.controller;

import com.mycompany.firstweb.dto.ResultDTO;
import com.mycompany.firstweb.dto.UserDTO;
import com.mycompany.firstweb.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author Artist-Code
 */
@WebServlet(name = "edit", urlPatterns = {"/edit"})
public class SvEdit extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getContentType() == null || !"application/json".equals(req.getContentType())) {
            sendJsonError(resp, HttpServletResponse.SC_BAD_REQUEST, "JSON Expected");
            return;
        }
        
        try {
            JSONObject jsonObject = new JSONObject(new JSONTokener(req.getReader()));
            if(!jsonObject.has("id") || !jsonObject.has("name") || !jsonObject.has("lastName") || !jsonObject.has("phone")) {
                sendJsonError(resp, HttpServletResponse.SC_BAD_REQUEST, "Missing requeried fields");
                
            }
            
            UserDTO userDTO = new UserDTO(
                    jsonObject.getString("name"), 
                    jsonObject.getString("lastName"), 
                    jsonObject.getString("phone")
            );
            ResultDTO<UserDTO> result = userService.update(Long.valueOf(jsonObject.getString("id")), userDTO);
            
            if(result.getData() == null || !result.isSuccess()) {
                sendJsonError(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, result.getErrorMessage());
                return;
            }
            
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(new JSONObject().put("message", "User updated correctly").toString());
            
        } catch(JSONException | NumberFormatException e ){
            sendJsonError(resp, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Long id = Long.valueOf(request.getParameter("id"));
            ResultDTO<UserDTO> result = userService.findById(id);
            if (!result.isSuccess() || result.getData() == null) {
                sendJsonError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, result.getErrorMessage());
                return;
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            JSONObject jsonObject = new JSONObject(result.getData());
            response.getWriter().write(jsonObject.toString());
        } catch (NumberFormatException e) {
            sendJsonError(response, HttpServletResponse.SC_BAD_REQUEST, "Id invalid");
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
