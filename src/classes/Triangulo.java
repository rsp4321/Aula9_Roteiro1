/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author CAMPUSRP\rodrigo.pinto
 */
public class Triangulo {
    
    private Ponto pa,pb,pc; // Os três vértices

    public Triangulo(Ponto pa, Ponto pb, Ponto pc) {
        
//        this.pa = pa;
//        this.pb = pb;
//        this.pc = pc;

        // Dando três pontos quaisquer, não necessariamente definiremos um triângulo
        // Aplicaremos a condição de existência de triângulos para confirmar
        if (verificarCondicaoExistencia(pa, pb, pc)) {
            
            // Como é possível criar o triângulo, vamos preencher os pontos
            this.pa = pa;
            this.pb = pb;
            this.pc = pc;
        }
    }
    
    /**
     * Método para imprimir os valores dos pontos, a área e os valores dos lados.
     * 
     * @return Uma string contendo todas as informações.
     */
    public String imprimir() {
        String str = "";
        
        // A ideia é imprimir os valores dos pontos, os valores dos lados e a área do mesmo
        
        
        return str;
    }
    
    private boolean verificarCondicaoExistencia(Ponto pa, Ponto pb, Ponto pc) {
        
        // Distância dos lados
        double d1 = pa.distancia(pb); 
        double d2 = pb.distancia(pc);
        double d3 = pc.distancia(pa);
      
        // No caso, vamos fazer a lógica inversa:
        // vamos verificar se foge da condição de existência
        // Nesse caso , retornamos falso direto
        if (!( (Math.abs(d2 - d3) < d1) && (d1 < d2 + d3) )) {  // |b - c| < a < b + c
            return false;
        }
        
        if ( !( (Math.abs(d1 - d3) < d2) && (d2 < d1 + d3))) { // |a - c| < b < a + c
            return false;
        }
        
        if ( !( (Math.abs(d1 - d2) < d3) && (d3 < d1 + d2))) { // |a - b| < c < a + b
            return false;
        }
        
        return true;
        
    }
    
    
    
    
    
}
