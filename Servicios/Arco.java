



/*
 * La clase arco representa un arco del grafo. Contiene un vertice origen, un vertice destino y una etiqueta.
 * Nota: Para poder exponer los arcos fuera del grafo y que nadie los modifique se hizo esta clase inmutable
 * (Inmutable: una vez creado el arco no es posible cambiarle los valores).
 */
public class Arco<T> implements Comparable{

	private int verticeOrigen;
	private int verticeDestino;
	private T etiqueta;

	public Arco(int verticeOrigen, int verticeDestino, T etiqueta) {
		this.verticeOrigen = verticeOrigen;
		this.verticeDestino = verticeDestino;
		this.etiqueta = etiqueta;
	}
	
	public int getVerticeOrigen() {
		return verticeOrigen;
	}
	
	public int getVerticeDestino() {
		return verticeDestino;
	}

	public T getEtiqueta() {
		return etiqueta;
	}
	public String toString(){
		return "\n│Desde: "+String.format("%2d",this.verticeOrigen) + " Hasta: "+ String.format("%2d",this.verticeDestino) + " │ "+ this.etiqueta+" Metros│";
	}
	public boolean equals(Object x){
		Arco<T> xx=(Arco<T>) x;
		return (xx.getVerticeOrigen()==this.verticeOrigen && xx.getVerticeDestino() == this.verticeDestino);
	}

	@Override
	public int compareTo(Object a) {
		// TODO Auto-generated method stub
		// throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
		Arco<Integer> arco=(Arco<Integer>) a;
		int et=arco.getEtiqueta();
		return ((int)this.etiqueta-et);
	}

}
