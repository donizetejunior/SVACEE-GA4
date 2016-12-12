package svacee.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import svacee.ctrl.DadoConsumoCtrl;
import svacee.model.DadoConsumo;

/**
 * @author Beatriz, Donizete e Luciano
 */
public class TelaOperacional extends javax.swing.JFrame {

    DadoConsumoCtrl dcc = new DadoConsumoCtrl();
    String itemSelecionado;
    private Object sdcc;
    
    
    public TelaOperacional() {
        initComponents();
        jBArqCSV.setToolTipText("Obter dados de arquivo CSV");
        jBDadosTabular.setToolTipText("Visualizar Dados Tabular");
        jBDadosGrafico.setToolTipText("Visualizar Dados em Gráfico");
        jBAjuda.setToolTipText("Obter Ajuda Sobre o Sistema");
        jBSair.setToolTipText("Finalizar Programa");
    }

    @SuppressWarnings("unchecked")

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalSvacee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalSvacee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalSvacee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalSvacee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipalSvacee().setVisible(true);
            }
        });
    }
    
    
    public void buscarArquivoCSV() {

        try {
            dcc.getTabelaDadosCSV().clear();
            JFileChooser jfc = new JFileChooser();
            int retorno = jfc.showOpenDialog(null);
            if (retorno == JFileChooser.APPROVE_OPTION) {
                File arq = jfc.getSelectedFile();

                dcc.obterDadosCSV(arq);
                dcc.preencherPontoColeta();
                ocuparTabela();
                ocuparComboBoxEquipamento();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    public void ocuparTabela() {
        DefaultTableModel model = (DefaultTableModel) jTabelaDados.getModel();
        model.getDataVector().removeAllElements();

        for (DadoConsumo sdc : dcc.getTabelaDadosCSV()) {
            model.addRow(new Object[]{sdc.getDataHora(), sdc.getIdColeta(), sdc.getValorKwH()});
        }
    }

    public void ocuparComboBoxEquipamento() {
        jcbPontoColeta.removeAllItems();

        Iterator i = dcc.getPontoColetaLista().iterator();
        while (i.hasNext()) {
            jcbPontoColeta.addItem((String) i.next());
        }
    }

    public void plotarGrafico() {
        if (dcc.getTesteArq()== 1) {
            if (jtpAbas.getTabCount() == 3) {
                jtpAbas.remove(2);
            }

            JPanel grafico = createChartPanel();
            jtpAbas.add(grafico, "Gráfico de Consumo");
            jtpAbas.setSelectedComponent(grafico);
        } else {
            JOptionPane.showMessageDialog(this, "Erros Foram Encontrados Durante o processo de plotagem!"
                    + "\nPor Favor Tente Novamente!",
                    "ERRO!", JOptionPane.ERROR_MESSAGE);
        }

    }

    private JPanel createChartPanel() {
        
        String chartTitle = "Gráfico de Consumo: " + itemSelecionado;
        String xAxisLabel = "Hora (Hora,minuto)";
        String yAxisLabel = "Valor KwH (Quilowatt-hora)";

        XYDataset dataset = createDataset();

        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset);

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        plot.setRenderer(renderer);

        return new ChartPanel(chart);
    }

    private XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries linha = new XYSeries(itemSelecionado);

        SimpleDateFormat formataHora = new SimpleDateFormat("HH.mm");

        for (DadoConsumo dc : dcc.getGraficoLista()) {
            linha.add(Double.parseDouble(formataHora.format(dc.getDataHora())), dc.getValorKwH());
        }
        dataset.addSeries(linha);

        return dataset;
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jBArqCSV = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jBDadosTabular = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jBDadosGrafico = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jBAjuda = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jBSair = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jtpAbas = new javax.swing.JTabbedPane();
        jpDadosTabular = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabelaDados = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jpDadosGrafico = new javax.swing.JPanel();
        jbVisualizarGrafico = new javax.swing.JButton();
        jlInfo = new javax.swing.JLabel();
        jcbPontoColeta = new javax.swing.JComboBox<String>();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setMaximumSize(new java.awt.Dimension(1000, 1000));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 600));

        jToolBar1.setRollover(true);

        jBArqCSV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/svacee/gui/imgens/arquivo.gif"))); // NOI18N
        jBArqCSV.setFocusable(false);
        jBArqCSV.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBArqCSV.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBArqCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBArqCSVActionPerformed(evt);
            }
        });
        jToolBar1.add(jBArqCSV);
        jToolBar1.add(jSeparator1);

        jBDadosTabular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/svacee/gui/imgens/table.png"))); // NOI18N
        jBDadosTabular.setFocusable(false);
        jBDadosTabular.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBDadosTabular.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBDadosTabular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDadosTabularActionPerformed(evt);
            }
        });
        jToolBar1.add(jBDadosTabular);
        jToolBar1.add(jSeparator2);

        jBDadosGrafico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/svacee/gui/imgens/chart.jpg"))); // NOI18N
        jBDadosGrafico.setFocusable(false);
        jBDadosGrafico.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBDadosGrafico.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBDadosGrafico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDadosGraficoActionPerformed(evt);
            }
        });
        jToolBar1.add(jBDadosGrafico);
        jToolBar1.add(jSeparator3);

        jBAjuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/svacee/gui/imgens/ajuda.png"))); // NOI18N
        jBAjuda.setFocusable(false);
        jBAjuda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBAjuda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBAjuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAjudaActionPerformed(evt);
            }
        });
        jToolBar1.add(jBAjuda);
        jToolBar1.add(jSeparator4);

        jBSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/svacee/gui/imgens/sair.png"))); // NOI18N
        jBSair.setFocusable(false);
        jBSair.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBSair.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSairActionPerformed(evt);
            }
        });
        jToolBar1.add(jBSair);

        jtpAbas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTabelaDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data e Hora", "Equipamento Coleta", "Valor KwH"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTabelaDados);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Seja Bem Vindo a Analise de Dados no Modo Gráfico");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Para que seja possivel a plotagem de dados em uma tabela, é necessário que tenha selecionado um arquivo CSV.");

        javax.swing.GroupLayout jpDadosTabularLayout = new javax.swing.GroupLayout(jpDadosTabular);
        jpDadosTabular.setLayout(jpDadosTabularLayout);
        jpDadosTabularLayout.setHorizontalGroup(
            jpDadosTabularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDadosTabularLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpDadosTabularLayout.setVerticalGroup(
            jpDadosTabularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDadosTabularLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jtpAbas.addTab("Tabela", jpDadosTabular);

        jbVisualizarGrafico.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jbVisualizarGrafico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/svacee/gui/imgens/chart.jpg"))); // NOI18N
        jbVisualizarGrafico.setText("Plotar Gráfico");
        jbVisualizarGrafico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbVisualizarGraficoActionPerformed(evt);
            }
        });

        jlInfo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jlInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlInfo.setText("Por Favor antes de plotar um gráfico, primeiro selecione um Equipamento");

        jcbPontoColeta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione..." }));
        jcbPontoColeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbPontoColetaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Seja Bem Vindo a Analise de Dados no Modo Gráfico");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Para que seja possivel a plotagem de dados em um gráfico, é necessário que tenha selecionado um arquivo CSV.");

        javax.swing.GroupLayout jpDadosGraficoLayout = new javax.swing.GroupLayout(jpDadosGrafico);
        jpDadosGrafico.setLayout(jpDadosGraficoLayout);
        jpDadosGraficoLayout.setHorizontalGroup(
            jpDadosGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
            .addComponent(jlInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpDadosGraficoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpDadosGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpDadosGraficoLayout.createSequentialGroup()
                        .addComponent(jcbPontoColeta, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(183, 183, 183))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpDadosGraficoLayout.createSequentialGroup()
                        .addComponent(jbVisualizarGrafico)
                        .addGap(209, 209, 209))))
        );
        jpDadosGraficoLayout.setVerticalGroup(
            jpDadosGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDadosGraficoLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addComponent(jlInfo)
                .addGap(26, 26, 26)
                .addComponent(jcbPontoColeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95)
                .addComponent(jbVisualizarGrafico)
                .addGap(107, 107, 107))
        );

        jtpAbas.addTab("Visualizar Gráfico", jpDadosGrafico);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jtpAbas, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtpAbas))
        );

        jMenu1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenu1.setText("ARQUIVO");

        jMenuItem2.setText("Obter Dados de CSV");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("SAIR");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenu2.setText("VIZUALIZAR");

        jMenuItem4.setText("Dados Tabular");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Gráfico de Consumo");
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        jMenu3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenu3.setText("AJUDA");

        jMenuItem6.setText("SOBRE Pollus Móveis");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuItem7.setText("Sobre SVACEE");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem8.setText("Sobre Desenvolvedores");
        jMenu3.add(jMenuItem8);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        buscarArquivoCSV();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        jtpAbas.setSelectedComponent(jpDadosTabular);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        SobrePollusMoveis obj = new SobrePollusMoveis();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jBSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSairActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jBSairActionPerformed

    private void jBDadosTabularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDadosTabularActionPerformed
        jtpAbas.setSelectedComponent(jpDadosTabular);
    }//GEN-LAST:event_jBDadosTabularActionPerformed

    private void jBArqCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBArqCSVActionPerformed
        buscarArquivoCSV();
    }//GEN-LAST:event_jBArqCSVActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        SobreSVACEE obj = new SobreSVACEE();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jBDadosGraficoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDadosGraficoActionPerformed
        // TODO add your handling code here:
        jtpAbas.setSelectedComponent(jpDadosGrafico);
    }//GEN-LAST:event_jBDadosGraficoActionPerformed

    private void jBAjudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAjudaActionPerformed
        // TODO add your handling code here:
        SobresSvacee obj = new SobresSvacee();
        obj.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBAjudaActionPerformed

    private void jcbPontoColetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbPontoColetaActionPerformed
        dcc.getGraficoLista().clear();
        String item = (String) jcbPontoColeta.getSelectedItem();
        dcc.preencherGrafico(item);
        itemSelecionado = item;
    }//GEN-LAST:event_jcbPontoColetaActionPerformed

    
    
    private void jbVisualizarGraficoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVisualizarGraficoActionPerformed
        plotarGrafico();
    }//GEN-LAST:event_jbVisualizarGraficoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAjuda;
    private javax.swing.JButton jBArqCSV;
    private javax.swing.JButton jBDadosGrafico;
    private javax.swing.JButton jBDadosTabular;
    private javax.swing.JButton jBSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTable jTabelaDados;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton jbVisualizarGrafico;
    private javax.swing.JComboBox<String> jcbPontoColeta;
    private javax.swing.JLabel jlInfo;
    private javax.swing.JPanel jpDadosGrafico;
    private javax.swing.JPanel jpDadosTabular;
    private javax.swing.JTabbedPane jtpAbas;
    // End of variables declaration//GEN-END:variables
}
