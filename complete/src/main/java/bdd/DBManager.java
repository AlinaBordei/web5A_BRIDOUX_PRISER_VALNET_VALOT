/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdd;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Axel
 */
public abstract class DBManager<T> {

    protected Connection connect = null;

    public DBManager() {

        /* Obtention de la connexion via le DAO qui paramètre les donnèes de connexion*/
        try {
            this.connect = DAO.getConnect();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public abstract T take(int id);

    public abstract boolean create(T d);
    
    public abstract String find(String Nom, String Prenom);
    
    public abstract boolean update(T obj);
    
}
