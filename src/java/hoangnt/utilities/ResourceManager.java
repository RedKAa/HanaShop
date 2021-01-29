/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ResourceManager {

    public static Properties getProperties(String path) {
        InputStreamReader in = null;
        Properties properties = new Properties();
        try {
            in = new InputStreamReader(new FileInputStream(path + "WEB-INF\\resourceNaming.properties"));
            properties.load(in);
        } catch (IOException ex) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex1) {
                }
            }
        }
        return properties;
    }
    
    
}
