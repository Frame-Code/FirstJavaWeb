package com.mycompany.firstweb.service.mappers;

import com.mycompany.firstweb.dto.ResultDTO;
import com.mycompany.firstweb.dto.UserDTO;
import com.mycompany.firstweb.entities.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Artist-Code
 */
public class ResultMapper {

    public static <T> boolean isSuccessResult(ResultDTO<T> result) {
        if (result.getData() == null || !result.isSuccess()) {
            return false;
        } else if (result.isSuccess() && result.getData() != null) {
            return true;
        }
        return false;
    }

    public static ResultDTO<UserDTO> toDTO(ResultDTO<User> result) {
        if (!isSuccessResult(result)) {
            return toEmptyDTO(result);
        }

        return new ResultDTO<>(UserMapper.toDTO(result.getData()),
                result.isSuccess(),
                result.getErrorMessage());
    }

    public static ResultDTO<List<UserDTO>> toDTOList(ResultDTO<List<User>> resultList) {
        if (!isSuccessResult(resultList)) {
            return toEmptyDTOList(resultList);
        }
        List<UserDTO> usersDTO = new ArrayList<>();

        if (resultList.getData().isEmpty()) {
            return new ResultDTO<>(usersDTO, resultList.isSuccess(), resultList.getErrorMessage());
        }

        for (User user : resultList.getData()) {
            UserDTO userDTO = UserMapper.toDTO(user);
            usersDTO.add(userDTO);
        }
        
        return new ResultDTO<>(usersDTO, resultList.isSuccess(), resultList.getErrorMessage());

    }

    public static ResultDTO<UserDTO> toEmptyDTO(ResultDTO<User> result) {
        return new ResultDTO<>(null, result.isSuccess(), result.getErrorMessage());
    }

    public static ResultDTO<List<UserDTO>> toEmptyDTOList(ResultDTO<List<User>> resultList) {
        return new ResultDTO<>(null, resultList.isSuccess(), resultList.getErrorMessage());
    }
}
