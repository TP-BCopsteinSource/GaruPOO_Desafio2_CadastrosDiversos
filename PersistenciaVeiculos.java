import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;



public class PersistenciaVeiculos {
    private static final String nArq = "Veiculos.dat";

    public static List<Veiculo> carregaVeiculos() {
        List<Veiculo> veiculos = new LinkedList<>();

        String currDir = Paths.get("").toAbsolutePath().toString();
        String caminhoCompleto = currDir+"\\"+nArq;
        Path refArq = Paths.get(caminhoCompleto);

        try (Scanner sc = new Scanner(Files.newBufferedReader(refArq, Charset.defaultCharset()))){ 
            sc.useDelimiter("[;\n]"); // separadores: ; e nova linha 
            String placa;
            String marca;
            String cor;
            Veiculo.CategoriaVeiculo categoria;
            while (sc.hasNext()){ 
                placa = sc.next(); 
                marca = sc.next();
                cor = sc.next();
                categoria = Veiculo.CategoriaVeiculo.valueOf(sc.next());
                Veiculo veiculo = new Veiculo(placa,marca,cor,categoria);
                veiculos.add(veiculo);
            }
         }catch (IOException x){ 
             System.err.format("Erro de E/S: %s%n", x);
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