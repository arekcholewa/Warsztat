package entity;

public class Warsztat {

    private Long id;
    private String ulica;
    private String miejscowosc;

    public Warsztat(){

    }

    public Warsztat(Long id, String ulica, String miejscowosc) {
        this.id = id;
        this.ulica = ulica;
        this.miejscowosc = miejscowosc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }
}
