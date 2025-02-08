
package com.mycompany.firstweb.dao.interfaces;

import java.util.List;

/**
 *
 * @author Artist-Code
 * @param <T> The generic class 
 */
public interface GenericDao<T> {
    T getById(Long id);
    List<T> getAll();
    void deleteById(Long id);
    void create(T object);
    void update(T object);
}
