// Beatriz, Donizete e Luciano
package svacee.model;


import java.sql.Timestamp;

public class DadoConsumo {

    private Timestamp dataHora;
    private String idColeta;
    private double valorKwH;
    
    public DadoConsumo(){
        
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public String getIdColeta() {
        return idColeta;
    }

    public void setIdColeta(String idColeta) {
        this.idColeta = idColeta;
    }

    public double getValorKwH() {
        return valorKwH;
    }

    public void setValorKwH(double valorKwH) {
        this.valorKwH = valorKwH;
    }
}
