/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.service;

import hoangnt.model.Product;
import java.util.List;

/**
 *
 * @author user
 */
public interface ProductService {
    
    //ADMIN ONLY
    boolean insert(Product product, String userID);//done

    boolean edit(Product product, String userID);//done

    boolean delete(String productID, String userID);//done

     //ALL ROLE
    Product get(String id) ;//done

    List<Product> getAll(int beginIndex, int endIndex, boolean isAdmin);//done

    int countAll(boolean isAdmin);

    List<Product> seachByCategory(String cid, int beginIndex, int endIndex, boolean isAdmin) ;//done

    int countSearchByCategory(String cid, boolean isAdmin);//done

    List<Product> seachByName(String productName, int beginIndex, int endIndex, boolean isAdmin);//done

    int countSearchByName(String productName, boolean isAdmin) ;//done

    List<Product> seachByPrice(long min, long max, int beginIndex, int endIndex, boolean isAdmin);//done

    int countSearchByPrice(long min, long max, boolean isAdmin);//done

    Product getBestSellerProduct();//done

}
