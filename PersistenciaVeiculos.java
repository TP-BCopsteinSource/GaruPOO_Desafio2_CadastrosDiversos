import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import java.io.PrintWriter;
import java.nio.charset.Charset;

public class PersistenciaVeiculos {
    private static final String nArq = "veiculos.dat";

    public static List<Veiculo> carregaVeiculos() {
        List<Veiculo> veiculos = new LinkedList<>();

        String currDir = Paths.get("").toAbsolutePath().toString();
        String caminhoCompleto = currDir+"\\"+nArq;
        Path refArq = Paths.get(caminhoCompleto);

        try {
            Reader reader = Files.newBufferedReader(refArq);
            //CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            Iterable<CSVRecord> csvRecords = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);            
            for (CSVRecord csvRecord : csvRecords) {
                String placa = csvRecord.get("placa");
                String marca = csvRecord.get("marca");
                String cor = csvRecord.get("cor");
                String catAux = csvRecord.get("categoria");
                Veiculo.CategoriaVeiculo categoria = Veiculo.CategoriaVeiculo.valueOf(catAux);
                Veiculo veiculo = new Veiculo(placa,marca,cor,categoria);
                veiculos.add(veiculo);
            }
        }catch(IOException e){
            System.err.format("Erro de E/S: %s%n", e); 
            return null;
        }
        return veiculos;
    }

    public static boolean persisteVeiculos(List<Veiculo> veiculos){
        Path path = Paths.get(nArq); 
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path, Charset.defaultCharset()))){ 
            for(Veiculo v:veiculos) 
                writer.format(Locale.ENGLISH,
                		      "%s;%s;%s;%s;",
                              v.getPlaca(),v.getMarca(),
                              v.getCor(),v.getCategoria().toString()); 
        } 
        catch (IOException x) { 
            System.err.format("Erro de E/S: %s%n", x); 
            return false;
        } 
        return true;
    }
}