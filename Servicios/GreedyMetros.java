import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class GreedyMetros {
    private Grafo<Integer> grafo;
    private ArrayList<Arco<Integer>> parcial;
    private int visitas;
    private int metrosFinales;
    private long tiempoInicio;
    private long tiempo;
    private GrupoConexos recorrido;

    
    public GreedyMetros(Grafo<Integer> g){
        this.grafo=g;
        this.parcial=new ArrayList<>();
        this.visitas=0;
        this.metrosFinales=0;
        this.recorrido=new GrupoConexos();
        
    }

    public ArrayList<Arco<Integer>> caminoMasCorto(){
        this.tiempoInicio=System.currentTimeMillis();
        ArrayList<Arco<Integer>> tunel=new ArrayList<>();
        Iterator<Arco<Integer>> arcos = this.grafo.obtenerArcos();
        while (arcos.hasNext()){
            Arco<Integer> arco=arcos.next();
            Integer origen=arco.getVerticeOrigen();
            Integer destino=arco.getVerticeDestino();
            Arco<Integer> tmp=new Arco<Integer>(destino, origen, 0);
            if (!tunel.contains(tmp)) 
                tunel.add(arco);
        }
        Collections.sort(tunel);
        visitar (tunel);
        return this.parcial;
    }
    
    private void visitar(ArrayList<Arco<Integer>> tunel){
        while (!tunel.isEmpty()&&!this.recorrido.size(this.grafo.cantidadVertices())){
            this.visitas++;
            Arco<Integer> arco=tunel.get(0);
            tunel.remove(0);
            if (!recorrido.yaEstaConectado(arco)){
                parcial.add(arco);
                this.metrosFinales+=arco.getEtiqueta();
                this.recorrido.agregarArco(arco);
            }
        }
        long finT=System.currentTimeMillis();
        this.tiempo=finT-this.tiempoInicio;
    }

    public int getVisitas() {
        return visitas;
    }
    public int getMetrosFinales() {
        return this.metrosFinales;
    }
    public long getTiempo() {
        return tiempo;
    }
    
}
