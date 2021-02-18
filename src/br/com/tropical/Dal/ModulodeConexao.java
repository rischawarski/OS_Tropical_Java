
package br.com.tropical.Dal;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 *
 * @author RSC Sistemas   (Richardson schawaraski cruz )
 */
public class ModulodeConexao {
    
      public static Properties getProp() throws IOException {
        //return null;
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("C:\\Tropical Sistema\\src/dbconf.ini");
        props.load(file);
        return props;

    }

    //~metodo responsavel por estabelecer a conexao com o banco
    public static Connection conector() throws IOException {
        java.sql.Connection conexao = null;
        Properties prop = getProp();
        String driver = prop.getProperty("DRIVER");
        String url = prop.getProperty("URL");
        String user = prop.getProperty("USER");
        String senha = prop.getProperty("SENHA");

      
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, senha);
            return conexao;
        } catch (Exception e) {

            //JOptionPane.showMessageDialog(null,"Banco de dados inacesivel");
            return null;
        }
    }
    
}
