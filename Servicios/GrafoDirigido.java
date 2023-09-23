
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Complejidades generales:
 * Por lo que he estado viendo, las complejidades de las operaciones contain, put, 
 * suelen ser constantes,
 * sin embago dependiendo del equilibrio de la estructura y otras variantes puede ser 
 * de O(n), para casos excepcionales.
 * Por lo cual, los considero en este analisis, O(1) para el peor de los casos. 
 */

public class GrafoDirigido<T> implements Grafo<T> {

	private Map<Integer,ArrayList<Arco<T>>> vertice;
	private int cantidadDeArcos;

	public GrafoDirigido(){
		this.vertice =new HashMap<Integer,ArrayList<Arco<T>>>() ;
		this.cantidadDeArcos=0;
	}

	/** 
	 * * Complejidad:  O(1)
	 * Este método recorre primero el hashMap para evitar "blanquearle" la lista de arcos si es que existe,
	 * lo que en el pero de los casos puede ser de complejidad O(1).
	 * y Luego Agrega la Clave al Hashmap, lo que podria significar (en el peor de los casos) otro O(1),
     * */
	@Override
	public void agregarVertice(int verticeId) {
		if (!vertice.containsKey(verticeId)){
            ArrayList<Arco<T>> lista=new ArrayList<>();
            vertice.put(verticeId, lista);
        }else{
            // System.out.println("ya existe");
        }
	}


	/**
	 * Compejidad: O(n)
	 * n arcos
	 */
	@Override
	public void borrarVertice(int verticeId) {
		Iterator<Arco<T>> arcos = this.obtenerArcos();
		while (arcos.hasNext()){
			Arco<T> arco = arcos.next();
			Integer o=arco.getVerticeOrigen();
			Integer d=arco.getVerticeDestino();
			if (o==verticeId || d==verticeId){
				this.borrarArco(o,d);
			}
		}
        vertice.remove(verticeId);
	}


	/**
	 * Complejidad O(1);
	 * Verificar si existe: O(1),
	 * ContainsKey: O(1),
	 * get del hasmap O(1),
	 * add del arrayList O(1)
	 */
	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		if (!this.existeArco(verticeId1, verticeId2)){
            Arco<T> tmp= new Arco<T>(verticeId1, verticeId2, etiqueta);
            if (vertice.containsKey(verticeId1)&&vertice.containsKey(verticeId2)){
                vertice.get(verticeId1).add(tmp);
				this.cantidadDeArcos++;
            }
        }
	}

	/**
	 * Complejidad O(1),
	 * todos los metodos implementados aqui son de complejidad O(1)
	 */
	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		if (vertice.containsKey(verticeId1)){
            Arco<T> tmp=new Arco<T>(verticeId1, verticeId2, null);
            vertice.get(verticeId1).remove(tmp);
			this.cantidadDeArcos--;
        }
	}

	/**
	 * Complejidad: O(1),
	 * 
	 */
	@Override
	public boolean contieneVertice(int verticeId) {
        return (vertice.containsKey(verticeId));
	}


	/**
	 * Complejidad: O(1);
	 * metodos Contains/Key de un map y de un ArrayList:O(1)
	 * 
	 */
	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
	   if (vertice.containsKey(verticeId1)){
            ArrayList<Arco<T>> tmp=vertice.get(verticeId1);
            Arco<T> arco=new Arco<T>(verticeId1, verticeId2, null);
            return tmp.contains(arco);
        }else{
            return false;
        }
	}

	/**
	 * Complejidad: O(n); Siendo n la cantidad de items del array de arcos que forman el value del mapa.
	 * containsKey: O(1);
	 * Aqui, la complejidad de indexOf es O(n) ,
	 * ambos get son de O(1)
	 * 
	 */
	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		if (vertice.containsKey(verticeId1)){
            Arco<T> arco= new Arco<T>(verticeId1, verticeId2, null);
            int i=vertice.get(verticeId1).indexOf(arco);
            return vertice.get(verticeId1).get(i);
        }else{
            return null;
        }
	}

	/**
	 * Complejidad O(1);
	 * el metodo size() de un arryList se corresponde con una constante hasta en el peor de los casos
	 */
	@Override
	public int cantidadVertices() {
        return vertice.size();
	}


	/**
	 * complejidad: O(n)
	 * el recorrer cada vertice implica O(n) donde n es el total de claves del mapa, (total de vertices)
	 * luego, obtener propiamente el value para consultar el size, son constantes
	 */
	@Override
	public int cantidadArcos() {
        return this.cantidadDeArcos;
	}

	/**
	 * Complejidad: O(n);
	 * Hacer una copia de los vertices implica recorrerlo en su totalidad: O(n);
	 */
	@Override
	public Iterator<Integer> obtenerVertices() {
		ArrayList<Integer> verts= new ArrayList<>(vertice.keySet());
        return verts.iterator();
	}


	/**
	 * Complejidad: O(n);
	 * Obtener el value <ArrayList<Arco<T>> de tal vertice: O(1);
	 * Recorrerclo: O(n) : n es la cantidad de elementos de dicho array
	 * getVerticeDestino y add son constantes
	 */
	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		ArrayList<Arco<T>> tmp= vertice.get(verticeId);
        ArrayList<Integer> resp=new ArrayList<>();

		for (Arco<T> arco : tmp){
            resp.add(arco.getVerticeDestino());
        }
        return resp.iterator();
	}

	/**
	 * Complejidad: O(n)    ((   O(3n) ))
	 * Hacer una colleccion con todos los arcos que existen en el mapa implica O(a) a es 
	 * la cantidad total de arcos.
	 * recorrer tal coleccion implica O(n) donde n es el size de la ésta ( igual a a),
	 * Agregarlos a una lista, implica O(t) donde t es la cantidad de elementos a agregar 
	 * ( a = n = t) 
	 * 
	 */
	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		ArrayList<Arco<T>> tmp=new ArrayList<>();
        Collection<ArrayList<Arco<T>>> values = vertice.values();
		for (ArrayList<Arco<T>> arcos : values){
            tmp.addAll(arcos);
        }
        return tmp.iterator();
	}

	/**
	 * Complejidad: O(1)
	 */
	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		if (vertice.containsKey((verticeId))) return vertice.get(verticeId).iterator();
		return Collections.emptyIterator();
	}

}
