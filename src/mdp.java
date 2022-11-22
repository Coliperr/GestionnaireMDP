import java.io.Serializable;
import java.util.regex.Pattern;

public class mdp implements Serializable {
    protected String motDePasse;
    protected int force;
    public mdp(String motDePasse) {
        this.motDePasse = motDePasse;
        this.force = force_mdp(motDePasse);
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public int getForce() {
        return force;
    }

    public int force_mdp(String motDePasse){
        int force = 0;
        int len = motDePasse.length();
        switch(len){
            case 0,1,2,3,4,5: return force;
            case 6,7: force = 1; break;
            case 8,9: force = 2; break;
            default: force = 4;
        }
        /*if (!motDePasse.matches(".*[a-zA-Z]*[0-9]*.") || !motDePasse.matches(".*[a-z]*[A-Z]*[a-z]") || !motDePasse.matches(".*[!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~]]*")){
            return force;
        }*/

        int i = 0;
        int lowercase = 0,uppercase = 0,digit = 0,special = 0;
        for (i=0; i<motDePasse.length();i++){
            if (Character.isUpperCase(motDePasse.charAt(i))){
                uppercase++;
            }
            else if (Character.isLowerCase(motDePasse.charAt(i))) {
                lowercase++;
            }
            else if (Character.isDigit(motDePasse.charAt(i))) {
                digit++;
            }
            else{
                special++;
            }
        }
        if (uppercase==0||lowercase==0||digit==0||special==0||uppercase==len||lowercase==len||digit==len){
            return force;
        }
        else{
            return force+1;
        }
    }
}
