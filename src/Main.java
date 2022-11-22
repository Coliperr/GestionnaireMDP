import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*
        primaryStage.setTitle("SunLock");
        Group root = new Group();
        Pane pane = new Pane(root);
        Scene theScene = new Scene(pane, 600, 400,true);
        primaryStage.getIcons().add(new Image("img/star.jpg"));

        //Définir un text "label" puis une case pour écrire dedans
        Label label1 = new Label("Name:");
        TextField textField = new TextField ();
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField);
        hb.setSpacing(10);
        root.getChildren().addAll(label1,hb);

        //faire apparaitre la fenetre
        primaryStage.setScene(theScene);
        primaryStage.show(); */


        Parent root = FXMLLoader.load(getClass().getResource("ressources/fxml/Main.fxml"));
        primaryStage.setTitle("Locker");
        primaryStage.getIcons().add(new Image("ressources/img/star.jpg"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, IOException, BadPaddingException, InvalidKeyException, InvalidKeySpecException, InvalidAlgorithmParameterException, ClassNotFoundException {
        launch(args);
        compte perso = new compte("perso","oui");
        perso.add_profil(new profil("www.gmail.com","adressemail@gmail.com",new mdp("passeword")));
        System.out.println("************************");
        System.out.println("le compte est: ");
        perso.printCompte();
        perso.encryptCompte();
        System.out.println("Une fois chiffre on a: ");
        perso.printCompte();
        perso.decryptObject();
        System.out.println("Une fois déchifre on a:");
        perso.printCompte();

        System.out.println("************************ Add a new profil ************************");
        perso.add_profil(new profil("https://identites.ensea.fr/cas/login?service=https%3A%2F%2Fintranet.ensea.fr%2F_authenticate%3FrequestedURL%3D%252Ffr%252Findex.html","idDeUser",new mdp("C3c1_N'EstPAS!m0n?mo7/de-paze")));
        System.out.println("le compte est devenu: ");
        perso.printCompte();
        perso.encryptCompte();
        System.out.println("Une fois chiffre on a: ");
        perso.printCompte();
        perso.decryptObject();
        System.out.println("Une fois déchifre on a:");
        perso.printCompte();


        /* test de regex
        String test = "ceciEstunTest";
        String test2 = "Ceciest8untest";
        if (!test.matches(".*[a-zA-Z]*[0-9]*.")){
            System.out.println(" Test Dans if ");
        }
        if (!test2.matches(".*[a-zA-Z]*[0-9]*.") || !test2.matches(".*[a-z]*[A-Z]*[a-z]") || !test2.matches(".*[!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~]]*")){
            System.out.println(" Test2 Dans if ");
        } */


    }
}

