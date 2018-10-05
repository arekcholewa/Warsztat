package entity;

public class Auto {
    private Long id;
    private Long warsztatId;
    private String marka;
    private String model;
    private String NrRej;

    public Auto(){

    }

    public Auto(Long id, Long warsztatId, String marka, String model, String nrRej) {
        this.id = id;
        this.warsztatId = warsztatId;
        this.marka = marka;
        this.model = model;
        NrRej = nrRej;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWarsztatId() {
        return warsztatId;
    }

    public void setWarsztatId(Long warsztatId) {
        this.warsztatId = warsztatId;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNrRej() {
        return NrRej;
    }

    public void setNrRej(String nrRej) {
        NrRej = nrRej;
    }
}
