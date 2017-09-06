package bdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class UserQuery extends DBManager<User>{
	public UserQuery() {
        super();
    }

    public User[] select() {
        /*Initialisation Instruction SQL pré-compilée*/
        PreparedStatement st = null;

        /*Initialisation variable de réception du résultat*/
        ResultSet resultat = null;

        /*
        * Initialisation de l'objet créé avec la requéte: un profil Article.
        * Sous forme de tableau pour traitement après.
         */
        User obj[];

        /*variable pour compter ligne*/
        int rowcount;

        try {
            /*Instruction SQL pré-compilée*/
            st = connect.prepareStatement("SELECT * FROM user");

            /* Paramétre(s) pour l'instruction SQL pré-compilée:
            * st(pour la requete).set(type de la variable)(numéro d'arriver de la variable dans la requéte /? correspondant, variable);
             */
            //st.setInt(1, id);

            /* Exécution instruction SQL pré-compilée */
            resultat = st.executeQuery();
            connect.commit();

            /* S'assurer d'aller au bout ... */
            if (!resultat.last()) {
                return null;
            }
            /* ... pour récupérer la dernière ligne ... */
            rowcount = resultat.getRow();
            /*... et revenir au début. */
            resultat.beforeFirst();
            /* Tableau associé comme un article à n lignes. */
            obj = new User[rowcount];
            /* Variable pour parcourir le tableau. */
            int i = 0;
            /*Pour parcourir toute la table*/
            while (resultat.next()) {
                /* nouvel article = toutes les informations recueillies. */
                obj[i] = new User(resultat.getInt("Id"), resultat.getString("Name"), resultat.getString("Password"));
                /*Incrémentation de la variable pour passer à la ligne suivante. */
                i++;
            }
            /* On retourne le compte/objet/client en tableau. */
            return obj;

            /*Exception*/
        } catch (SQLException ex) {
            Logger.getLogger(UserQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        /**/
        return null;
    }

	@Override
	public User take(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(User d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String find(String Nom, String Prenom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(User obj) {
		// TODO Auto-generated method stub
		return false;
	}
}
