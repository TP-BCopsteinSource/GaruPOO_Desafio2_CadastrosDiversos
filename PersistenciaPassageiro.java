import java.util.List;
import java.util.LinkedList;
import java.nio.file.*;
import java.io.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class PersistenciaPassageiro {
    private static final String nArq = "passageiros.dat";

    public static List<Passageiro> carregaPassageiros() {
        List<Passageiro> passageiros = new LinkedList<>();

        String currDir = Paths.get("").toAbsolutePath().toString();
        String caminhoCompleto = currDir + "\\" + nArq;
        Path refArq = Paths.get(caminhoCompleto);

        //cpf,nome,formaPgto,nroCartao,pontuacaoAcumulada,qtdadeAvaliacoes
        try {
            Reader reader = Files.newBufferedReader(refArq);
            // CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            Iterable<CSVRecord> csvRecords = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
            for (CSVRecord csvRecord : csvRecords) {
                String cpf = csvRecord.get("cpf");
                String nome = csvRecord.get("nome");
                FormaPagamento formaPagto = FormaPagamento.valueOf(csvRecord.get("formaPgto"));
                String nroCartao = csvRecord.get("nroCartao");
                int pontuacaoAcumulada = Integer.parseInt(csvRecord.get("pontuacaoAcumulada"));
                int qtdadeAvaliacoes = Integer.parseInt(csvRecord.get("qtdadeAvaliacoes"));
                Passageiro passageiro = new Passageiro(cpf,nome,formaPagto,nroCartao,pontuacaoAcumulada,qtdadeAvaliacoes);
                passageiros.add(passageiro);
            }
        } catch (IOException e) {
            System.err.format("Erro de E/S: %s%n", e);
            return null;
        }
        return passageiros;
    }
}