package com.mycompany.firstweb.service;

import com.mycompany.firstweb.dao.impl.UserDaoImpl;
import com.mycompany.firstweb.dao.interfaces.UserDao;
import com.mycompany.firstweb.dto.ResultDTO;
import com.mycompany.firstweb.dto.UserDTO;
import com.mycompany.firstweb.entities.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Artist-Code
 */
public class UserService {

    private final UserDao userDao;

    public UserService() {
        this.userDao = new UserDaoImpl();
    }

    private <T> boolean isSuccessResult(ResultDTO<T> result) {
        if (result.getData() == null || !result.isSuccess()) {
            return false;
        } else if (result.isSuccess() && result.getData() != null) {
            return true;
        }
        return false;
    }

    public ResultDTO<UserDTO> findById(Long id) {
        ResultDTO<User> result = userDao.findById(id);
        if (!isSuccessResult(result)) {
            return new ResultDTO<>(null, result.isSuccess(), result.getErrorMessage());
        }
        UserDTO userDTO = new UserDTO(result.getData().getName(), result.getData().getLastNames(), result.getData().getPhone());
        return new ResultDTO<>(userDTO, result.isSuccess(), result.getErrorMessage());

    }

    public ResultDTO<List<UserDTO>> findAll() {
        ResultDTO<List<User>> result = userDao.findAll();

        if (!isSuccessResult(result)) {
            return new ResultDTO<>(null, result.isSuccess(), result.getErrorMessage());
        }

        List<UserDTO> usersDTO = new ArrayList<>();

        if (result.getData().isEmpty()) {
            return new ResultDTO<>(usersDTO, result.isSuccess(), result.getErrorMessage());
        }

        for (User user : result.getData()) {
            UserDTO userDTO = new UserDTO(user.getName(), user.getLastNames(), user.getPhone());
            usersDTO.add(userDTO);
        }
        return new ResultDTO<>(usersDTO, result.isSuccess(), result.getErrorMessage());
    }

    public ResultDTO<String> deleteById(Long id) {
        return userDao.deleteById(id);
    }

    public ResultDTO<UserDTO> create(String name, String lastName, String phone) {
        ResultDTO<User> result = userDao.create(new User(name, lastName, phone));
        if (!isSuccessResult(result)) {
            return new ResultDTO<>(null, result.isSuccess(), result.getErrorMessage());
        }
        UserDTO userDTO = new UserDTO(result.getData().getName(), result.getData().getLastNames(), result.getData().getPhone());
        return new ResultDTO<>(userDTO, result.isSuccess(), result.getErrorMessage());
    }

    public ResultDTO<UserDTO> update(Long id, String name, String lastName, String phone) {
        ResultDTO<User> result = userDao.findById(id);
        if (!isSuccessResult(result)) {
            return new ResultDTO<>(null, result.isSuccess(), result.getErrorMessage());
        }

        result.getData().setName(name);
        result.getData().setLastNames(lastName);
        result.getData().setPhone(phone);
        ResultDTO<User> resultUpdate = userDao.update(result.getData());
        
        if (!isSuccessResult(resultUpdate)) {
            return new ResultDTO<>(null, result.isSuccess(), result.getErrorMessage());
        }
        UserDTO userDTO = new UserDTO(resultUpdate.getData().getName(), resultUpdate.getData().getLastNames(), resultUpdate.getData().getPhone());
        return new ResultDTO<>(userDTO, result.isSuccess(), result.getErrorMessage());

    }

}
