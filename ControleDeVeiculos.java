import java.util.List;
// Verificar como usar esta biblioteca para melhorar o getveiculo
// Para o exemplo de dependencias externas
//import static com.google.common.collect.MoreCollectors.onlyElement;
import java.util.stream.Collectors;

public class ControleDeVeiculos {
    private List<Veiculo> veiculos;

    public ControleDeVeiculos(){ // Refatorar: injeção de dependência
        veiculos = PersistenciaVeiculos.carregaVeiculos();
    }

    public Veiculo getVeiculo(String placa){
        /*
        Para o exemplo com dependencias externas
        return veiculos
                .stream()
                .filter(v->v.getPlaca().equals(placa))
                .collect(onlyElement());
        */
        List<Veiculo> veiculo = veiculos
                                .stream()
                                .filter(v->v.getPlaca().equals(placa))
                                .collect(Collectors.toList());
        if (veiculo.size() > 1){
            return null;
        }else{
            return veiculo.get(0);
        }
    }
}