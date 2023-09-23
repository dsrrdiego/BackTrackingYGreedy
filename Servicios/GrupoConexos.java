import java.util.ArrayList;

public class GrupoConexos {
    
    ArrayList<ArrayList<Integer>> solucion;  // lista de listas de estaciones conctadas entr si

    public GrupoConexos(){
        this.solucion=new ArrayList<>();
    }
    
    public void agregarArco(Arco<Integer> arco){   //agrega una dupla a la solucion
        ArrayList<Integer> tmp=new ArrayList<Integer>();
        tmp.add(arco.getVerticeOrigen());
        tmp.add(arco.getVerticeDestino());
        solucion.add(tmp);
        ordenar();
    } 

    private void ordenar(){  //busca conectividades entre los vertices y los agrupa donde corresponda
        int z=solucion.size();
        if (z>1){
            for (int index = z-1;index>0;index--){
                ArrayList<Integer> ultimo=solucion.get(index);
                for (int i = index-1; i >-1; i--) {
                    ArrayList<Integer> anterior=solucion.get(i);
                    ArrayList<Integer> aInsertar=new ArrayList<>(ultimo);
                    if (contiene(ultimo,anterior, aInsertar)){
                        anterior.addAll(aInsertar);
                        solucion.remove(ultimo);
                    }
                }
            }
        }
    }
    
    //metodo auxiliar para evaluar si una lista contiene vertices
    private boolean contiene(ArrayList<Integer> ultimo, ArrayList<Integer> anterior, ArrayList<Integer> aInsertar){
        boolean insertar=false;

        for (Integer i : ultimo) {
            if (anterior.contains(i)) {
                insertar=true;
                aInsertar.remove(i);
            }
        }
        return insertar;
    }

    //auxiliar para debug
    public void imprimir(){
        for (ArrayList<Integer> list : solucion) {
            System.out.println(list);
        }
    }

    //hace copia de array
    public ArrayList<ArrayList<Integer>> copiar(ArrayList<ArrayList<Integer>> array){
        ArrayList<ArrayList<Integer>> copia=new ArrayList<ArrayList<Integer>>();
        for(ArrayList<Integer> interna: array){
            ArrayList<Integer> tmp=new ArrayList<Integer>(interna);
            copia.add(tmp);
        }
        return copia;
    }

    
    public ArrayList<ArrayList<Integer>> obtenerCopia(){
        return this.copiar(this.solucion);
    }
    
    //restaura la solucion a la lista que se le de
    public void volverA(ArrayList<ArrayList<Integer>> copia){
        this.solucion=this.copiar(copia);
    }

    //usado para evaluar el corte del backtrackin: (si la cantidad de estaciones conectadas es el total).
    public boolean size(int x){
        if (this.solucion.size()==0) return false;
     return (this.solucion.get(0).size()==x);

    }

    //para el greddy, va conectando si no estan previamente conectadas
    public boolean yaEstaConectado(Arco<Integer> arco){
        int origen=arco.getVerticeOrigen();
        int destino=arco.getVerticeDestino();

        for (ArrayList<Integer> conectos : solucion){
            if (conectos.contains(origen)&&conectos.contains(destino)){
                return true;
            }
        }

        return false;
    }
}
