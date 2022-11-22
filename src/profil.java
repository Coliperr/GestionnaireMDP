import java.io.Serializable;

public class profil implements Serializable {
    protected String site;
    protected String id;
    protected mdp mdpProfil;

    public profil(String site, String id, mdp mdpProfil) {
        this.site = site;
        this.id = id;
        this.mdpProfil = mdpProfil;
    }

    public void printProfil(){
        System.out.println(this.site + " " + this.id + " " + this.mdpProfil.motDePasse+ " " + this.mdpProfil.force);
    }
}
