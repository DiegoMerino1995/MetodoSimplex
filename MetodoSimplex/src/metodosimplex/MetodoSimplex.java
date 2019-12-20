package metodosimplex;
import java.util.ArrayList;
public class MetodoSimplex {
    public static void main(String[] args) {
        String funcion = "max";
        ArrayList<Double> ec1 = new ArrayList<Double>();
        ec1.add(2.0);
        ec1.add(3.0);
        ec1.add(3.0);
        ArrayList<Double> resultado1 = new ArrayList<Double>();
        resultado1.add(0.0);
        
        ArrayList<Double> ec2 = new ArrayList<Double>();
        ec2.add(3.0);
        ec2.add(2.0);
        ec2.add(0.0);
        ArrayList<String> simbolo2 = new ArrayList<String>();
        simbolo2.add("<=");
        ArrayList<Double> resultado2 = new ArrayList<Double>();
        resultado2.add(60.0);
        
        ArrayList<Double> ec3 = new ArrayList<Double>();
        ec3.add(-1.0);
        ec3.add(1.0);
        ec3.add(4.0);
        ArrayList<String> simbolo3 = new ArrayList<String>();
        simbolo3.add("<=");
        ArrayList<Double> resultado3 = new ArrayList<Double>();
        resultado3.add(10.0);
        
        ArrayList<Double> ec4 = new ArrayList<Double>();
        ec4.add(2.0);
        ec4.add(-2.0);
        ec4.add(5.0);
        ArrayList<String> simbolo4 = new ArrayList<String>();
        simbolo4.add("<=");
        ArrayList<Double> resultado4 = new ArrayList<Double>();
        resultado4.add(50.0);
        
        ArrayList<ArrayList> matriz = new ArrayList<ArrayList>();
        matriz.add(ec1);
        matriz.add(ec2);
        matriz.add(ec3);
        matriz.add(ec4);
        
        ArrayList<ArrayList> matrizSimbolo = new ArrayList<ArrayList>();
        matrizSimbolo.add(simbolo2);
        matrizSimbolo.add(simbolo3);
        matrizSimbolo.add(simbolo4);
        
        ArrayList<ArrayList> matrizResultado = new ArrayList<ArrayList>();
        matrizResultado.add(resultado1);
        matrizResultado.add(resultado2);
        matrizResultado.add(resultado3);
        matrizResultado.add(resultado4); 
        int contEst=0;
        for (int i = 0; i < matrizSimbolo.size(); i++) {
            ArrayList<String> temp = new ArrayList<String>();
            temp = matrizSimbolo.get(i);
            ArrayList<Double> temp1 = new ArrayList<Double>();
            temp1 = matriz.get(i+1); 
            if (temp.get(0)==">=") {
                for (int j = 0; j < contEst; j++) {
                    matriz.get(i+1).add(0.0);
                }
                matriz.get(i+1).add(-1.0); 
                contEst ++;
            }else{
                for (int j = 0; j < contEst; j++) {
                    matriz.get(i+1).add(0.0);
                }
                matriz.get(i+1).add(1.0);
                contEst ++;
            }             
        }
        int a =(matriz.size())-1;
        ArrayList temp = matriz.get(a);
        int tamTemp = (temp.size()-1);
        for (int i = 0; i <= a; i++) {
            
            while (true) {            
                if ((((matriz.get(i)).size())-1) == tamTemp) {
                    break;
                }
                (matriz.get(i)).add(0.0);
            }
        }
        for (int i = 0; i < matriz.size(); i++) {
            ArrayList b = matrizResultado.get(i);
            (matriz.get(i)).add(b.get(0)); 
        }                
        if (funcion == "max") {
            ArrayList <Double> df = (matriz.get(0));
            for (int i = 0; i < df.size(); i++) {
                if (df.get(i)!= 0) {
                    df.set(i, df.get(i)*(-1));    
                }
            }
        }
        System.out.println(matriz);
        boolean condW =true;

        while(condW){
            ArrayList <Double> cond = matriz.get(0);
            for (int i = 0; i < cond.size(); i++) {
                if (cond.get(i)>=0) {
                    condW = false;
                }else{
                     condW = true;
                     break;
                }
        }
        ArrayList <Double> tmp = matriz.get(0);
        int  pivotePosition = 0  ;
        Double pivoteNumero = 999.0;
        for (int i = 0; i < tmp.size()-1; i++) {
            if (tmp.get(i)< pivoteNumero) {
               pivoteNumero = tmp.get(i);
            }            
        }
        for (int i = 0; i < tmp.size(); i++) {
            if ( pivoteNumero == tmp.get(i)) {
                pivotePosition = i;
                break;
            }
        }
        System.out.println(pivotePosition);
        ArrayList <Double> resultado = new ArrayList<Double>()   ;

        for (int i = 1; i < matriz.size(); i++) {
            ArrayList <Double> prueba = matriz.get(i);
             if (prueba.get(pivotePosition)  <= 0) {
                resultado.add(-1.0);
             }else{
             resultado.add(prueba.get(prueba.size()-1)/prueba.get(pivotePosition));      
             }
        }
        
        double menor = 999;
        int tuplaPosition = 0;
        for (int i = 0; i < resultado.size(); i++) {
            if (resultado.get(i) >= 1) {
                if (resultado.get(i)< menor) {
                    menor = resultado.get(i);
                    tuplaPosition=i+1;//el indice de la razon menor
                }
            }
        }
        System.out.println(tuplaPosition);
        System.out.println(matriz.get(tuplaPosition).get(pivotePosition));
    
         ArrayList<Double> tupla = new ArrayList<Double>();
        tupla = matriz.get(tuplaPosition);
        double div = tupla.get(pivotePosition);
        if (div != 1) {
            for (int i = 0; i < tupla.size(); i++) {
                tupla.set(i, tupla.get(i) / div);
            }
        }
        ArrayList<Double> tmp1 = new ArrayList<Double>();
            for (int i = 0; i < matriz.size(); i++) {
                if (tupla != matriz.get(i)) {
                    tmp1 = matriz.get(i);
                    double multiplicador = tmp1.get(pivotePosition) * -1;
                    for (int j = 0; j < tupla.size(); j++) {
                        if (tupla.get(j) != 0) {
                            tmp1.set(j, (tmp1.get(j)) + (tupla.get(j) * multiplicador));
                        } else {
                            tupla.set(j, 0.0);
                        }
                    }
                }
            }
        System.out.println(matriz);
        
    }
    }  
    
}
