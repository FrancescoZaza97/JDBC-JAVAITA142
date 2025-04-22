package bean;

public class Quotidiani {
    private int id;
    private String nome;
    private double prezzo;
    private int aggio;
    private int cricevute;
    private int cvendute;

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
        this.prezzo = prezzo;
    }

    public int getAggio() {
        return aggio;
    }

    public void setAggio(int aggio) {
        this.aggio = aggio;
    }

    public int getCricevute() {
        return cricevute;
    }

    public void setCricevute(int cricevute) {
        this.cricevute = cricevute;
    }

    public int getCvendute() {
        return cvendute;
    }

    public void setCvendute(int cvendute) {
        this.cvendute = cvendute;
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

