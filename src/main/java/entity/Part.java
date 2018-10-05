package entity;

public class Part {

    private Long id;
    private Long warsztatId;
    private String producent;
    private String model;
    private Long categoryID;
    private int pólka;
    private int miejsce;
    private int rząd;
    private String DOT;
    private String bieznik;

    public Part(){

    }

    public Part(Long id,Long categoryID, Long warsztatId, String producent, String model , int pólka, int miejsce, int rząd, String DOT, String bieznik) {
        this.id = id;
        this.producent = producent;
        this.model = model;
        this.categoryID = categoryID;
        this.pólka = pólka;
        this.miejsce = miejsce;
        this.rząd = rząd;
        this.DOT = DOT;
        this.bieznik = bieznik;
        this.warsztatId = warsztatId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProducent() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public int getPólka() {
        return pólka;
    }

    public void setPólka(int pólka) {
        this.pólka = pólka;
    }

    public int getMiejsce() {
        return miejsce;
    }

    public void setMiejsce(int miejsce) {
        this.miejsce = miejsce;
    }

    public int getRząd() {
        return rząd;
    }

    public void setRząd(int rząd) {
        this.rząd = rząd;
    }

    public String getDOT() {
        return DOT;
    }

    public void setDOT(String DOT) {
        this.DOT = DOT;
    }

    public String getBieznik() {
        return bieznik;
    }

    public void setBieznik(String bieznik) {
        this.bieznik = bieznik;
    }
}
