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
    static int buscar = 1;

    public static void main(String[] args) throws IOException {

        String[] basePicas = {"0123",
            "4567"
        };
        String digitos = "0123456789";
        int indiceMayor = 0;
        agente.setPicas(0);
        agente.setFijas(0);
        int auxPicas = 0;
        int auxFijas = 0;
        int picasTotal = 0;
        String lectura = "";
        String[] input;

        char[] numeroAux;
        while (true) {

            lectura = entrada.readLine();
            if ("S".equals(lectura)) {
                for (int i = 0; i < basePicas.length; i++) {
                    if (buscar > 0) {
                        System.out.println("Ingrese el numero de picas segido del numero de fijas, separandolos por una ',' para el siguiente nuemo: " + basePicas[i]);
                        input = entrada.readLine().split(",");
                        auxPicas = Integer.parseInt(input[0]);
                        auxFijas = Integer.parseInt(input[1]);
                        auxPicas = auxPicas + auxFijas;

                        if (auxPicas > 0 || auxFijas > 0) {
                            if (auxPicas > agente.getPicas()) {
                                indiceMayor = i;
                                agente.setFijas(auxFijas);
                                agente.setPicas(auxPicas - agente.getFijas());
                            }
                            picasTotal = picasTotal + auxPicas;
                            if (auxFijas == 4) {
                                System.out.println("su numero es  " + basePicas[indiceMayor]);
                                buscar = -1;
                            }
                        }
                    }
                }
                agente.setNumero(basePicas[indiceMayor]);

                if (buscar > 0) {

//                        System.out.println("buscar: "+picasTotal);
                    numeroAux = agente.getNumero().toCharArray();
                    int n = 4;                  //Tipos para escoger
                    int r = numeroAux.length;   //Elementos elegidos

                    if (picasTotal == 4) {
                        if ((agente.getPicas() + agente.getFijas()) == 4) {
                            Perm2(numeroAux, "", n, r);
                            System.out.println("su numero es  " + agente.getNumero());
                            buscar = -1;
                        } else {
                            digitos = digitos.replaceAll(agente.getNumero(), "");
                            digitos = digitos.replaceAll("89", "");
                            Perm2(numeroAux, "", n, r);
                            buscar = 1;
                        }

                    } else if (picasTotal == 2) {
                        if ((agente.getPicas() + agente.getFijas()) == 2) {
                            Perm2(numeroAux, "", n, r);
                            buscar = 1;
                            digitos = "89";
                        } else {
                            digitos = digitos.replaceAll(agente.getNumero(), "");
                            Perm2(numeroAux, "", n, r);
                            buscar = 1;
                        }
                    } else {
                        digitos = digitos.replaceAll(agente.getNumero(), "");
                        Perm2(numeroAux, "", n, r);
                        buscar = 1;
                    }

//            System.out.println("nuero: "+agente.getNumero());
//            System.out.println("buscar: "+agente.getNumero());
                    numeroAux = agente.getNumero().toCharArray();
                    //
                    n = 0;
                    int i = 0;
                    if (buscar > 0) {
                        do {
                            if (n > digitos.length()) {
                                n = 0;
                                i++;
                            }
                            numeroAux = agente.getNumero().toCharArray();
                            numeroAux[i] = digitos.charAt(n);
                            System.out.println("Ingrese el numero de picas segido del numero de fijas, separandolos por una ',' para el siguiente nuemo: " + String.valueOf(numeroAux));

                            try {
                                input = entrada.readLine().split(",");
                                auxPicas = Integer.parseInt(input[0]);
                                auxFijas = Integer.parseInt(input[1]);
                                auxPicas = auxPicas + auxFijas;

                                if (auxPicas > 0 || auxFijas > 0) {
                                    if (auxFijas < 4) {
                                        if (auxPicas > (agente.getFijas())) {
                                            agente.setNumero(String.valueOf(numeroAux));
                                            agente.setFijas(auxFijas);
                                            agente.setPicas(auxPicas - auxFijas);
                                            if (auxPicas == auxFijas) {
                                                digitos = digitos.replace(digitos.substring(0, (n + 1)), "");
                                                n = 10;
                                            } else {
                                                buscar = 1;
                                                Perm2(numeroAux, "", 4, 4);
                                                digitos = digitos.replace(digitos.substring(0, (n + 1)), "");
                                                n = 10;
                                                i--;
                                                buscar = 1;
                                            }

                                        }
                                        if (auxPicas < (agente.getPicas() + agente.getFijas())) {
                                            n = 10;
                                            numeroAux = agente.getNumero().toCharArray();
                                        }
                                    } else {
                                        agente.setNumero(String.valueOf(numeroAux));
                                        agente.setFijas(auxFijas);
                                        agente.setPicas(auxPicas - auxFijas);
                                        System.out.println("su numero es  " + agente.getNumero());
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
                }
            }
        }
    }

    private static String Perm2(char[] elem, String act, int n, int r) throws IOException {
        if (n == 0 && buscar > 0) {
//            System.out.println(act);
            String numAux = act.replaceAll(", ", "");
            System.out.println("Ingrese el numero de picas segido del numero de fijas, separandolos por una ',' para el siguiente nuemo: " + numAux);
            String[] input = entrada.readLine().split(",");
            int auxPicas = Integer.parseInt(input[0]);
            int auxFijas = Integer.parseInt(input[1]);
            auxPicas = auxPicas + auxFijas;
            if (auxPicas > 0 || auxFijas > 0) {
                if (auxPicas >= (agente.getPicas() + agente.getFijas())) {
                    agente.setNumero(numAux);
                    agente.setFijas(auxFijas);
                    agente.setPicas(auxPicas - agente.getFijas());
                    if (auxPicas == auxFijas) {
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
