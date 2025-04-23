package bean;

import java.util.Scanner;

public class Quotidiani {
    private int id;
    private String nome;
    private double prezzo;
    private int aggio;
    private int cricevute;
    private int cvendute;
    Scanner inputUtente = new Scanner(System.in);



    // Costruttore vuoto
    public Quotidiani() {}

    // Costruttore completo
    public Quotidiani(int id, String nome, double prezzo, int aggio, int cricevute, int cvendute) {
        this.id = id;
        this.nome = nome;
        this.prezzo = prezzo;
        this.aggio = aggio;
        this.cricevute = cricevute;
        this.cvendute = cvendute;
    }

    // Getter e Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        
        if (this.prezzo > 0) {
            this.prezzo = prezzo;
        }else{
            do{    
                System.out.println("il dato inserito non puo essere negativo.. re inserisci il valore");
                this.prezzo = inputUtente.nextDouble();
            }while(this.prezzo <= 0);
        }
    }

    public int getAggio() {
        return aggio;
    }

    public void setAggio(int aggio) {

        if (this.aggio > 0) {
            this.aggio = aggio;
        }else{
            do{    
                System.out.println("il dato inserito non puo essere negativo.. re inserisci il valore");
                this.aggio = inputUtente.nextInt();
            }while(this.aggio <= 0);
        }
    }

    public int getCricevute() {
        return cricevute;
    }

    public void setCricevute(int cricevute) {

        if (this.cricevute > 0) {
            this.cricevute = cricevute;
        }else{
            do{    
                System.out.println("il dato inserito non puo essere negativo.. re inserisci il valore");
                this.cricevute = inputUtente.nextInt();
            }while(this.cricevute <= 0);
        }
    }

    public int getCvendute() {
        return cvendute;
    }

    public void setCvendute(int cvendute) {
        if (this.cvendute > 0) {
            this.cvendute = cvendute;
        }else{
            do{    
                System.out.println("il dato inserito non puo essere negativo.. re inserisci il valore");
                this.cvendute = inputUtente.nextInt();
            }while(this.cvendute <= 0);
        }
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            ", prezzo='" + getPrezzo() + "'" +
            ", aggio='" + getAggio() + "'" +
            ", cricevute='" + getCricevute() + "'" +
            ", cvendute='" + getCvendute() + "'" +
            "}";
    }


}

