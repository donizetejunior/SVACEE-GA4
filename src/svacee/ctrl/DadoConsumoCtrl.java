// Beatriz, Donizete e Luciano
package svacee.ctrl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import svacee.model.DadoConsumo;

public class DadoConsumoCtrl {

    private File arquivoCSV;
    private int testeArq;
    
    private List<DadoConsumo> tabelaDadosCSV = new ArrayList<>();
    private List<String> pontoColetaLista = new ArrayList<>();
    private List<DadoConsumo> graficoLista = new ArrayList<>();

    public void obterDadosCSV(File arq) throws FileNotFoundException, IOException {

        setArquivoCSV(arq);

        String linha = "";
        String[] linhas;
        String[] coluna;

        BufferedReader br = null;
        FileReader fr = new FileReader(arq);
        
        br = new BufferedReader(fr);

        DadoConsumo dc;

        while ((linha = br.readLine()) != null) {
            linhas = linha.split(";");

            for (String v : linhas) {
                coluna = v.split(",");

                dc = new DadoConsumo();

                dc.setDataHora(Timestamp.valueOf(coluna[0]));
                dc.setIdColeta(coluna[1]);
                dc.setValorKwH(Double.parseDouble(coluna[2]));

                getTabelaDadosCSV().add(dc);
            }
        }
        setTesteArq(1);
    }
    
    public void preencherPontoColeta() {
        for (DadoConsumo sdc : getTabelaDadosCSV()) {
            if (!pontoColetaLista.contains(sdc.getIdColeta())) {
                getPontoColetaLista().add(sdc.getIdColeta());
            }
        }
    }

    public void preencherGrafico(String item) {
        for (DadoConsumo sdc : getTabelaDadosCSV()) {
            if (sdc.getIdColeta().equalsIgnoreCase(item)) {
                sdc.getDataHora();
                sdc.getValorKwH();
                getGraficoLista().add(sdc);
            }
        }
    }

    public File getArquivoCSV() {
        return arquivoCSV;
    }

    public void setArquivoCSV(File arquivoCSV) {
        this.arquivoCSV = arquivoCSV;
    }

    public List<DadoConsumo> getTabelaDadosCSV() {
        return tabelaDadosCSV;
    }

    public void setTabelaDadosCSV(List<DadoConsumo> tabelaDadosCSV) {
        this.tabelaDadosCSV = tabelaDadosCSV;
    }

    public int getTesteArq() {
        return testeArq;
    }

    public void setTesteArq(int testeArq) {
        this.testeArq = testeArq;
    }

    public List<String> getPontoColetaLista() {
        return pontoColetaLista;
    }

    public void setPontoColetaLista(List<String> pontoColetaLista) {
        this.pontoColetaLista = pontoColetaLista;
    }

    public List<DadoConsumo> getGraficoLista() {
        return graficoLista;
    }

    public void setGraficoLista(List<DadoConsumo> graficoLista) {
        this.graficoLista = graficoLista;
    }
}
