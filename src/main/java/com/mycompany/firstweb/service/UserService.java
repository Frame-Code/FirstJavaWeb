package com.mycompany.firstweb.service;

import com.mycompany.firstweb.dao.impl.UserDaoImpl;
import com.mycompany.firstweb.dao.interfaces.UserDao;
import com.mycompany.firstweb.dto.ResultDTO;
import com.mycompany.firstweb.dto.UserDTO;
import com.mycompany.firstweb.entities.User;
import com.mycompany.firstweb.service.mappers.ResultMapper;
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

    public ResultDTO<UserDTO> findById(Long id) {
        ResultDTO<User> result = userDao.findById(id);
        return ResultMapper.toDTO(result);

    }

    public ResultDTO<List<UserDTO>> findAll() {
        ResultDTO<List<User>> result = userDao.findAll();
        return ResultMapper.toDTOList(result);
    }

    public ResultDTO<String> deleteById(Long id) {
        return userDao.deleteById(id);
    }

    public ResultDTO<UserDTO> create(String name, String lastName, String phone) {
        ResultDTO<User> result = userDao.create(new User(name, lastName, phone));
        return ResultMapper.toDTO(result);
        
    }

    public ResultDTO<UserDTO> update(Long id, String name, String lastName, String phone) {
        ResultDTO<User> result = userDao.findById(id);
        result.getData().setName(name);
        result.getData().setLastNames(lastName);
        result.getData().setPhone(phone);
        
        ResultDTO<User> resultUpdate = userDao.update(result.getData());
        return ResultMapper.toDTO(resultUpdate);

    }

}
