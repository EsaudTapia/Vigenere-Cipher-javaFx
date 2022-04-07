package cipher;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeString.indexOf;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class InterfazController implements Initializable {

    @FXML
    private Button btnCerrar;
    @FXML
    private Button btnCodificar;
    @FXML
    private Button btnDecodificar;
    @FXML
    private TextField txtClave;
    @FXML
    private TextArea txtMensaje;
    
    
    double xOffset = 0;
    double yOffset = 0;
    Stage primaryStage;
  
    private ObservableList<String> acciones;
    String letra[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
        "s", "t", "u", "v", "w", "x", "y", "z"};
    private String letras[][] = new String[letra.length][letra.length];


    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        int c = 0;
        for (int i = 0; i < letra.length; i++) {
            c = i;
            for (int j = 0; j < letra.length; j++) {
                this.letras[i][j] = letra[c];
                c++;
                if (c == letra.length) {
                    c = 0;
                }
            }
        }

    }

    @FXML
    private void comprobarparaCodificar(ActionEvent event) {
        String clave, mensaje, resultado = "";
        clave = this.txtClave.getText();
        mensaje = this.txtMensaje.getText();

        if (!clave.equals("") && !mensaje.equals("")) {
            this.txtMensaje.setText(this.codificar(clave, mensaje, resultado));

        } else {
            JOptionPane.showMessageDialog(null, "Exiten campos vacios, no se puede codificar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void comprobarparaDecodificar(ActionEvent event) {
        String clave, mensaje, resultado = "";
        clave = this.txtClave.getText();
        mensaje = this.txtMensaje.getText();

        if (!clave.equals("") && !mensaje.equals("")) {
            this.txtMensaje.setText(this.decodificar(clave, mensaje, resultado));

        } else {
            JOptionPane.showMessageDialog(null, "Exiten campos vacios, no se puede decodificar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String codificar(String clave, String mensaje, String resultado) {
        clave = clave.replaceAll("\\s+", "");
        clave= clave.toLowerCase();
        mensaje = mensaje.replaceAll("\n", " ");
        mensaje= mensaje.toLowerCase();
            
        String[] palabras = mensaje.split(" ");
        int c = 0;
        for (int f = 0; f < palabras.length; f++) {

            for (int i = 0; i < palabras[f].length(); i++) {
                resultado += this.letras[Arrays.asList(letra).indexOf(clave.substring(c, c + 1))][Arrays.asList(letra).indexOf(palabras[f].substring(i, i + 1))];
                c++;
                if (c == clave.length()) {
                    c = 0;
                }
            }
            resultado += " ";
        }
        return resultado;
    }

    /*
    
     */
    private String decodificar(String clave, String mensaje, String resultado) {

        clave = clave.replaceAll("\\s+", "");
        clave= clave.toLowerCase();
        mensaje = mensaje.replaceAll("\n", " ");
        mensaje= mensaje.toLowerCase();
        String[] palabras = mensaje.split(" ");

        int c = 0;
        for (int f = 0; f < palabras.length; f++) {

            for (int i = 0; i < palabras[f].length(); i++) {

                String[] aux = this.letras[Arrays.asList(letra).indexOf(clave.substring(c, c + 1))];
                resultado += this.letra[Arrays.asList(aux).indexOf(palabras[f].substring(i, i + 1))];
                c++;
                if (c == clave.length()) {
                    c = 0;
                }
            }
            resultado += " ";
        }
        return resultado;
    }


    @FXML
    private void cerrarLog(ActionEvent event) {
        Stage ventana = (Stage) btnCerrar.getScene().getWindow();
        ventana.close();
    }

    @FXML
    private void MoverVentana(MouseEvent event) {
        primaryStage.setX(event.getScreenX() + xOffset);
        primaryStage.setY(event.getScreenY() + yOffset);
    }

    @FXML
    private void ObtenerXY(MouseEvent event) {
        primaryStage = (Stage) this.btnCerrar.getScene().getWindow();
        xOffset = primaryStage.getX() - event.getScreenX();
        yOffset = primaryStage.getY() - event.getScreenY();
    }
    



}
