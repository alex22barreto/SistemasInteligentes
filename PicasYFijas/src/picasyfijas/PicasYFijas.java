/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picasyfijas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author alexb
 */


public class PicasYFijas {
    /**
     * @param args the command line arguments
     */
    static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
    
    static AgentePicaFija agente = new AgentePicaFija();
    static int buscar =1;
    
    public static void main(String[] args) throws IOException {
        
        String [] basePicas = {"0123",
                               "1234",
                               "2345",
                               "3456",
                               "4567",
                               "5678",
                               "6789",
                               "7890",
                               "8901",
                               "9012"
                                };
        int indiceMayor=0;
        agente.setPicas(0);
        agente.setFijas(0);
        int auxPicas=0;
        int auxFijas=0;
        String [] input;
        
        char [] numeroAux;
        
        for (int i = 0; i < basePicas.length; i++) {
            System.out.println("Ingrese el numero de picas segido del numero de fijas, separandolos por una ',' para el siguiente nuemo: "+basePicas[i]);            
            input = entrada.readLine().split(",");
            auxPicas=Integer.parseInt(input[0]);
            auxFijas=Integer.parseInt(input[1]);
            
            if (auxPicas >0 || auxFijas >0) {
                auxPicas = auxPicas + auxFijas;
                if (auxPicas > agente.getPicas()) {
                    indiceMayor = i;
                    agente.setFijas(auxFijas);
                    agente.setPicas(auxPicas-agente.getFijas());
                }
            }
        }
        
        agente.setNumero(basePicas[indiceMayor]);
        numeroAux = agente.getNumero().toCharArray();
        System.out.println("nuero: "+agente.getNumero());
        
        int n = 4;                  //Tipos para escoger
        int r = numeroAux.length;   //Elementos elegidos
        Perm2(numeroAux, "", n, r);
     
        numeroAux = agente.getNumero().toCharArray();
        n = 0;
        int i=0;
        do {
            if (n>9) {
                n=0;
                i++;
            }
            numeroAux[i]= Integer.toString(n).charAt(0);
            System.out.println("Ingrese el numero de picas segido del numero de fijas, separandolos por una ',' para el siguiente nuemo: "+ String.valueOf(numeroAux));            
            
            try {
                input = entrada.readLine().split(",");
                auxPicas=Integer.parseInt(input[0]);
                auxFijas=Integer.parseInt(input[1]);

                if (auxPicas >0 || auxFijas >0) {
                    auxPicas = auxPicas + auxFijas;
                    if (auxFijas<4) {
                        if (auxPicas > (agente.getPicas()+agente.getFijas())) {
                        agente.setNumero(String.valueOf(numeroAux));
                        agente.setFijas(auxFijas);
                        agente.setPicas(auxPicas-auxFijas);
                        if(agente.getPicas()==agente.getFijas()){
                            n=10;
                        }else{
                            buscar = 1;
                            Perm2(numeroAux, "", 4, 4);
                        }
                        
                    }
                    if (auxPicas < (agente.getPicas()+agente.getFijas())) {
                        n=10;
                        numeroAux = agente.getNumero().toCharArray();
                    }
                    }else{
                        agente.setNumero(String.valueOf(numeroAux));
                        agente.setFijas(auxFijas);
                        agente.setPicas(auxPicas-auxFijas);
                        System.out.println("su numero es  "+ agente.getNumero());
                        break;
                    }
                    
                    
                    
                    
                }
            } catch (IOException ioe) {
                System.out.println("ERROR: " + ioe.getMessage());;
            } catch (NumberFormatException nfe) {
                System.out.println("ERROR: " + nfe.getMessage() + ". No es numérico.");;
            }
            n++;
        } while (true);
        
    }
    
    private static String Perm2(char[] elem, String act, int n, int r) throws IOException {
        if (n == 0 && buscar>0) {
//            System.out.println(act);
            String numAux = act.replaceAll(", ", "");
            System.out.println("Ingrese el numero de picas segido del numero de fijas, separandolos por una ',' para el siguiente nuemo: "+numAux);            
            String [] input = entrada.readLine().split(",");
            int auxPicas=Integer.parseInt(input[0]);
            int auxFijas=Integer.parseInt(input[1]);
            
            if (auxPicas >0 || auxFijas >0) {
                auxPicas = auxPicas + auxFijas;
                if (auxPicas > agente.getPicas()) {
                    agente.setNumero(numAux);
                    agente.setFijas(auxFijas);
                    agente.setPicas(auxPicas-agente.getFijas());
                    if (agente.getFijas() == agente.getPicas()) {
                        buscar = -1;
                    }
                }
            }
        } else {
            for (int i = 0; i < r; i++) {
                if (!act.contains(Character.toString(elem[i]))) { // Controla que no haya repeticiones
                    Perm2(elem, act + elem[i] + ", ", n - 1, r);
                }
            }
        }
        return null;
    }
    
}
