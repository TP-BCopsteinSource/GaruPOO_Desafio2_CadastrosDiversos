import java.util.Locale;
import java.util.List;
import java.util.LinkedList;
import java.nio.file.*;
import java.io.*;
import java.nio.charset.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class PersistenciaMotoristas {
    private static final String nArq = "motoristas.dat";

    public static List<Motorista> carregaMotoristas() {
        List<Motorista> motoristas = new LinkedList<>();

        String currDir = Paths.get("").toAbsolutePath().toString();
        String caminhoCompleto = currDir + "/" + nArq;
        Path refArq = Paths.get(caminhoCompleto);

        // cpf,nome,placaVeiculo,formaPgto,pontuacaoAcumulada,qtdadeAvaliacoes
        try {
            Reader reader = Files.newBufferedReader(refArq);
            Iterable<CSVRecord> csvRecords = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
            for (CSVRecord csvRecord : csvRecords) {
                String cpf = csvRecord.get("cpf");
                String nome = csvRecord.get("nome");
                String placaVeiculo = csvRecord.get("placaVeiculo");
                FormaPagamento formaPagto = FormaPagamento.valueOf(csvRecord.get("formaPgto"));
                int pontuacaoAcumulada = Integer.parseInt(csvRecord.get("pontuacaoAcumulada"));
                int qtdadeAvaliacoes = Integer.parseInt(csvRecord.get("qtdadeAvaliacoes"));
                Motorista motorista = new Motorista(cpf,nome,placaVeiculo,formaPagto,pontuacaoAcumulada,qtdadeAvaliacoes);
                motoristas.add(motorista);
            }
        } catch (IOException e) {
            System.err.format("Erro de E/S: %s%n", e);
            return null;
        }
        return motoristas;
    }

    public static boolean persisteMotoristas(List<Motorista> motoristas){
        Path path = Paths.get(nArq); 
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path, Charset.defaultCharset()))){ 
            writer.println("cpf,nome,placaVeiculo,formaPgto,pontuacaoAcumulada,qtdadeAvaliacoes");
            for(Motorista m:motoristas) 
                writer.format(Locale.ENGLISH,
                              "%s,%s,%s,%s,%d,%d\n",
                              m.getCPF(),m.getNome(),m.getPlacaVeiculo(),
                              m.getFormaPgto().toString(),
                              m.getPontuacaoAcumulada(),
                              m.getQtdadeAvaliacoes());
        } 
        catch (IOException x) { 
            System.err.format("Erro de E/S: %s%n", x); 
            return false;
        } 
        return true;
    }
}