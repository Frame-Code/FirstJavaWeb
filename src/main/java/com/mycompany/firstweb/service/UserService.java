package com.mycompany.firstweb.service;

import com.mycompany.firstweb.dao.impl.UserDaoImpl;
import com.mycompany.firstweb.dao.interfaces.UserDao;
import com.mycompany.firstweb.dto.ResultDTO;
import com.mycompany.firstweb.dto.UserDTO;
import com.mycompany.firstweb.entities.User;
import com.mycompany.firstweb.service.mappers.ResultMapper;
import com.mycompany.firstweb.service.mappers.UserMapper;
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

    public ResultDTO<UserDTO> create(UserDTO userDTO) {
        ResultDTO<User> result = userDao.create(UserMapper.toEntity(userDTO));
        return ResultMapper.toDTO(result);
        
    }

    public ResultDTO<UserDTO> update(Long id, UserDTO userDTO) {
        ResultDTO<User> result = userDao.findById(id);
        if(!ResultMapper.isSuccessResult(result)) {
            return ResultMapper.toEmptyDTO(result);
        }
        result.getData().setName(userDTO.getName());
        result.getData().setLastNames(userDTO.getLastName());
        result.getData().setPhone(userDTO.getPhone());
        
        ResultDTO<User> resultUpdate = userDao.update(result.getData());
        return ResultMapper.toDTO(resultUpdate);

    }

}
