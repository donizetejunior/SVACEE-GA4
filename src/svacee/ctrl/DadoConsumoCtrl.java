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
import svacee.model.DadoConsumo;

public class DadoConsumoCtrl {

    private File arquivoCSV;
    private List<DadoConsumo> tabelaDadosCSV = new ArrayList<>();

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

}
