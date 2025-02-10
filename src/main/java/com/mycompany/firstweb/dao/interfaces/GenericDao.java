
package com.mycompany.firstweb.dao.interfaces;

import com.mycompany.firstweb.dto.ResultDTO;
import java.util.List;

/**
 *
 * @author Artist-Code
 * @param <T> The generic class 
 */
public interface GenericDao<T> {
    ResultDTO<T> findById(Long id);
    ResultDTO<List<T>> findAll();
    ResultDTO<String> deleteById(Long id);
    ResultDTO<T> create(T object);
    ResultDTO<T> update(T object);
}
