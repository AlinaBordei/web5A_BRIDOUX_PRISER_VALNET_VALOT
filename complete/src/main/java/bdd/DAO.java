/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Axel
 */
public class DAO {

    private static Connection connect;

    private DAO() {
    }

    /*Instantiation de la connexion à la base de données*/
    private static void initConnexion(String driver, String url, String login, String password) throws SQLException {
        try {
            /*Appel de la fonction de connection et affichage de messages relatifs à l'état de connection*/
            Class.forName(driver);
            connect = DriverManager.getConnection(url, login, password);
            connect.setAutoCommit(false);
            
            /*Affichage*/
            System.out.println("Connexion reussie");
            
        /*Exception*/
        } catch (ClassNotFoundException e) {
            
            /*Affichage d'erreur pour l'utilisateur*/
            System.out.println("erreur chargement pilote JDBC \n");
            
            /*Sortie*/
            System.exit(0);
            
        /*Exception*/
        } catch (SQLException e) {
            
            /*Affichage d'erreur pour l'utilisateur*/
            System.out.println("erreur connexion base de données \n" + e.getMessage());
            
            /*Sortie*/
            System.exit(0);
        }
    }
/*Connexion à une base de donnée en sql avec par exemple le driver, le type, l'url, le port, l'utilisateur et son mot de passe*/
    public static Connection getConnect() throws SQLException {
        if (!(connect instanceof Connection)) {
            initConnexion("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/bddweb","root","");
        }
        return connect;
    }

}
