import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Random;

public class compte implements Serializable {
    private final String id;
    private final String key;
    private final String protocole;
    private ArrayList<profil> profils = new ArrayList<>();
    private final SecretKey secretKey;
    private final Cipher cipher;
    private final IvParameterSpec iv;
    private SealedObject sealedObject;

    public compte(String id, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException {
        this.id = id;
        this.key = key;
        this.protocole = "AES";
        this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        KeyGenerator generator = KeyGenerator.getInstance(this.protocole);
        generator.init(128);

        //Création d'une clé secrète par un mot de passe
        //salt est une valeur aléatoire qui sert à la création de la Secret Key à partir d'un mot de passe. Ici on prend une chaine de taille 7.
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        String salt = new String(array, Charset.forName("UTF-8"));

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(this.key.toCharArray(), salt.getBytes(), 65536, 256);
        this.secretKey = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");

        //Création de l'Initialization Vector (IV)
        byte[] ivByte = new byte[16];
        new SecureRandom().nextBytes(ivByte);
        this.iv = new IvParameterSpec(ivByte);
    }

    public void add_profil(profil p){
        this.profils.add(p);
    }

    public String getId() {
        return id;
    }

    public String getProtocole() {
        return protocole;
    }

    public void encryptCompte() throws InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, IOException {
        this.cipher.init(Cipher.ENCRYPT_MODE,this.secretKey,this.iv);
        this.sealedObject = new SealedObject(this.profils,this.cipher);
        this.profils = null;
    }

    public void decryptObject() throws InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, IOException, BadPaddingException, ClassNotFoundException {
        this.cipher.init(Cipher.DECRYPT_MODE,this.secretKey,this.iv);
        this.profils = (ArrayList<profil>) sealedObject.getObject(this.cipher);
        this.sealedObject = null;
    }
    public void printCompte(){
        if (this.profils==null){
            System.out.println("Il n'y a pas de profils enregistrés");
        }
        else {
            for (profil profil : this.profils) {
                profil.printProfil();
            }
        }
    }

}
