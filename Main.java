import java.math.BigInteger;

public class Main {

	/* seleccionar dataset aqui */
	public static final int dataSet=1;
	/*                          */

	public static final String MI_PATH="datasets/dataset"+dataSet+".txt";

	public static void main(String[] args) {

		
		CSVReader reader = new CSVReader(MI_PATH);
		reader.read();
		
		Grafo<Integer> subte= reader.obtenerGrafo();
		
		System.out.print("\033[H\033[2J");
        System.out.flush();
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println("                  Trabajo Especial Programción 3 : BackTracking / Greedy");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────");
		
		/*BackTracking */
		
		BackMetros bm=new BackMetros(subte);
		BigInteger cantidadDeCombinacionesPosibles=calcular(subte.cantidadArcos());
		String cantidadDeCombinacionesPosiblesString =String.format("%,d", cantidadDeCombinacionesPosibles);
		
		System.out.println("\nTécnica utilizada: BackTRacking:");
		System.out.println("Cantidad de estaciones a conectar: "+subte.cantidadVertices());
		System.out.println("Cantidad de túneles a evaluar: "+subte.cantidadArcos());
		System.out.println("Combinaciones maximas de combinaciones posibles: "+ cantidadDeCombinacionesPosiblesString+"\n");

		System.out.println("\n\nCamino Óptiomo: "+bm.caminoMasCorto());
		int metrosBack=bm.getMinimoMetros();
		String tunelesBack=String.format("%,10d",bm.getVisitas());
		System.out.println("\nLa cantidad mínima de metros a construir es de: "+metrosBack+".");
		System.out.println("Se tuvieron que evaluar: "+bm.getVisitas()+" de "+ cantidadDeCombinacionesPosibles +" tuneles para encontrar el circuito mas corto.");
		String tiempoBack=String.format("%,3.3f",(float) bm.getTiempo()/1000);
		System.out.println("Se tardaron: "+tiempoBack+" Segundos.");
		

		

		// /*Greedy */
		System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────");
		GreedyMetros gm=new GreedyMetros(subte);
		System.out.println("\nTécnica utilizada: Greedy");
		System.out.println("Cantidad de túneles a evaluar: "+subte.cantidadArcos());
		System.out.println("Camino a recorrer: "+gm.caminoMasCorto());
		int metrosGreedy=gm.getMetrosFinales();
		int tunelesGreedy=gm.getVisitas();
		System.out.println("La cantidad mínima de metros a construir es de: "+metrosGreedy+".");
		System.out.println("Se tuvieron que evaluar: "+tunelesGreedy+" de " + subte.cantidadArcos()+" tuneles para encontrar un camino corto.");
		String tiempoGreedy=String.format("%,3.3f",(float) gm.getTiempo()/1000);
		System.out.println("Se tardaron: "+tiempoGreedy+" Segundos.");
		
		/*Tabla informativa */

		String cantidadDeTuneles=String.format("%4d",subte.cantidadArcos());

		String metrosBackString = String.format("%18d", metrosBack);
		String tunelesBackString = String.format("%31s", tunelesBack);
		tiempoBack=String.format("%10s", tiempoBack);
		
		String metrosGreedyString = String.format("%18d", metrosGreedy);
		String tunelesGreedyString = String.format("%31s", tunelesGreedy);
		String cantidadString= String.format("%4d", subte.cantidadVertices());
		tiempoGreedy=String.format("%10s", tiempoGreedy);
		
		System.out.println("\n┌─────────────────────────────────────────────────────────────────────────────────┐");
		System.out.println("│ Dataset: "+dataSet+"                      Cantidad Total de Estaciones Del Recorrido:"+cantidadString+" │");
		System.out.println("│                                        Cantidad Total de tuneles a evaluar:"+cantidadDeTuneles +" │");
		System.out.println("├────────────────┬───────────────────┬────────────────────────────────┬───────────┤");
		System.out.println("│ Algoritmo:     │   Mínimo metros   │Métrica(Combinaciones evaluadas)│ Tiempo(s) │");
		System.out.println("├────────────────┼───────────────────┼────────────────────────────────┼───────────┤");
		System.out.println("│ BackTracking   │"+metrosBackString+" │"+   tunelesBackString+" │"+tiempoBack      +" │");
		System.out.println("│ Greedy         │"+metrosGreedyString+" │"+ tunelesGreedyString+" │"+tiempoGreedy+" │");
		System.out.println("└────────────────┴───────────────────┴────────────────────────────────┴───────────┘\n");
	}

	public static BigInteger factorial(int n) {
        BigInteger resultado = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            resultado = resultado.multiply(BigInteger.valueOf(i));
        }
        return resultado;
    }

	public static BigInteger calcular(int n){
		BigInteger suma=BigInteger.ZERO;
		for (int i=0;i<=n;i++){
			BigInteger a = factorial(n);
			BigInteger b= factorial(i);
			BigInteger c= factorial(n-i);
			BigInteger por=b.multiply(c);
			BigInteger div= a.divide(por);
			suma=suma.add(div);
		}
		return suma;
	}
}
