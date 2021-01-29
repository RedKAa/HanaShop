/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.service;
import hoangnt.model.Category;
import java.util.List;
/**
 *
 * @author user
 */
public interface CategoryService {
    void insert(Category category);

    void edit(Category category);

    void delete(String id);

    Category get(String id);

    List<Category> getAll();
}
