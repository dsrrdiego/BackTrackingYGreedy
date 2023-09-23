import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class BackMetros {
    private Grafo<Integer> grafo;
    private int minimoMetros;
    private ArrayList<Arco<Integer>> solucion;
    private long visitas;
    private long tiempoInicio;
    private long tiempo;
    private ArrayList<Arco<Integer>> tunel;
    private GrupoConexos conectados;

    public BackMetros(Grafo<Integer> g){
        this.grafo=g;
        this.minimoMetros=-1;
        this.visitas=0;
        this.conectados=new GrupoConexos();
        this.tunel=new ArrayList<>();
    }
    
    public ArrayList<Arco<Integer>> caminoMasCorto(){
        this.tiempoInicio=System.currentTimeMillis();
        ArrayList<Arco<Integer>> parcial=new ArrayList<>();

        //armado del conjunto de trabajo
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
        int metrosAcumulados=0;
        visitar(0,parcial, metrosAcumulados); 
        long finT= System.currentTimeMillis();
        this.tiempo=finT-this.tiempoInicio;
        return this.solucion;
    }

    char [] simbolo={'─','\\','/','│'};
    int simboloNumero=0;
    private void visitar(int indice, ArrayList<Arco<Integer>> parcial, int metrosAcumulados){
        this.visitas++;
        if (visitas%10000==0){
            simboloNumero++;
            simboloNumero=simboloNumero%4;
            System.out.print(simbolo[simboloNumero]+" Evaluando: "+String.format("%,15d",visitas)+" tuneles\r");
        }
        if (conectados.size(this.grafo.cantidadVertices())){
            this.minimoMetros=metrosAcumulados;
            this.solucion=new ArrayList<>(parcial);
        }else{
            while (indice<tunel.size()){
                Arco<Integer> arco=this.tunel.get(indice);
                if (!parcial.contains(arco)){
                    Integer metros=arco.getEtiqueta();
                    metrosAcumulados+=metros;
                    if (metrosAcumulados<this.minimoMetros||this.minimoMetros==-1){
                        parcial.add(arco);
                        ArrayList<ArrayList<Integer>> copia=conectados.obtenerCopia();
                        this.conectados.agregarArco(arco);
                        indice++;
                        visitar(indice,parcial, metrosAcumulados);
                        this.conectados.volverA(copia);
                        indice--;
                        parcial.remove(arco);
                    }
                    metrosAcumulados-=metros;
                }
                indice++;
            }
        }
    }
 
    public int getMinimoMetros() {
        return minimoMetros;
    }
    public long getVisitas(){
        return this.visitas;
    }
    public long getTiempo(){
        return this.tiempo;
    }
}
