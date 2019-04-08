/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Arma X
 */
public class Quadrado {
    private Ponto pa,pb,pc,pd; // Os quatro vértices

    public Quadrado(Ponto pa, Ponto pb, Ponto pc, Ponto pd) {
        
        if (verificarCondicaoExistencia(pa, pb, pc, pd)) {
            
            // Como é possível criar o quadrado, vamos preencher os pontos
            this.pa = pa;
            this.pb = pb;
            this.pc = pc;
            this.pd = pd;
        }
    }
    
    /**
     * Método para imprimir os valores dos pontos, a área e os valores dos lados.
     * 
     * @return Uma string contendo todas as informações.
     */
    public String imprimir() {
        String str = "";
        
          
        return str;
    }
    
    private boolean verificarCondicaoExistencia(Ponto pa, Ponto pb, Ponto pc, Ponto pd) {
        
        // Distância dos lados
        double d1 = pa.distancia(pb); 
        double d2 = pb.distancia(pc);
        double d3 = pc.distancia(pd);
        double d4 = pd.distancia(pa);
      
        // No caso, vamos fazer a lógica inversa:
        // vamos verificar se foge da condição de existência
        // Nesse caso , retornamos falso direto
        if (!( d1 == d2 )) {  //
            return false;
        }
                
        return true;
        
    }
    
}
