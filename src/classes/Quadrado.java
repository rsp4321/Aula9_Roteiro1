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

    private Ponto pa, pb, pc, pd; // Os quatro vértices

    public Quadrado(Ponto pa, Ponto pb, Ponto pc, Ponto pd) throws Exception {
        if (verificarCondicaoExistencia(pa, pb, pc, pd)) {
            // Como é possível criar o quadrado, vamos preencher os pontos
            this.pa = pa;
            this.pb = pb;
            this.pc = pc;
            this.pd = pd;
        } else {
            throw new Exception("Erro! Não é possível criar um quadrado a partir desses pontos.");
        }
    }

    private boolean verificarCondicaoExistencia(Ponto pa, Ponto pb, Ponto pc, Ponto pd) {

        // Distância dos lados
        double d1 = pa.distancia(pb);
        double d2 = pb.distancia(pc);
        double d3 = pc.distancia(pd);
        double d4 = pd.distancia(pa);

        //
        // No caso, vamos fazer a lógica inversa:
        // vamos verificar se foge da condição de existência
        // Nesse caso , retornamos falso direto
        if (!(d1 == d3 || d2 == d4)) {//Lados opostos iguais
            return false;
        }
        if (!(d1 == d4 || d2 == d3)) {//Formam Angulo de 90 Graus
            return false;
        }
        
        // Contra-exemplo para o código acima (gera falha):
        // pa = (5,1)
        // pb = (1,4)
        // pc = (6,4)
        // pd = (10,1)
        
        // No arquivo "plotagem_quadrado_contraexemplo.ggb", são plotadas essas coordenadas e o resultado é um paralelogramo
        
        // Para definir um quadrado, não basta os lados serem iguais somente. Os ângulos precisam ser iguais à 90°
        // Para tal, será necessário criar um método para calcular o ângulo entre três pontos
        
        return true;
    }

    public String area(Ponto pa, Ponto pb, Ponto pc, Ponto pd) {
        double d1 = pa.distancia(pb);
        double d4 = pd.distancia(pa);
        return "Area: " + d1 * d4;
    }

//    public String perimetro(Ponto pa, Ponto pb, Ponto pc, Ponto pd) {
    public double calcularPerimetro() {

        // No caso, como o quadrado já está formado, só precisamos multiplicar um dos lados por 4
        double d1 = pa.distancia(pb);

//        double d2 = pb.distancia(pc);
//        double d3 = pc.distancia(pd);
//        double d4 = pd.distancia(pa);
//
//        return "Perimetro: " + d1 + d2 + d3 + d4;
        return d1 * 4;
    }
    
    private double calcularAnguloABC(Ponto pa, Ponto pb, Ponto pc) {
        
        double da = pa.distancia(pb);
        double db = pb.distancia(pc);
        
        return 0;
    }

    // Quadrados, a princípio, não possuem tipos definidos. Eles já são casos particulares de quadriláteros
    public String tipo(Ponto pa, Ponto pb, Ponto pc, Ponto pd) {
        String tipo = (verificarCondicaoExistencia(getPa(), getPb(), getPc(), getPd()) == true) ? "é Quadrado Perfeito" : "é Retangulo ou Losango";
        return tipo;
    }

    public String imprimir() {
        String resultado = "" + area(getPa(), getPb(), getPc(), getPd())
//                + "\n" + perimetro(getPa(), getPb(), getPc(), getPd())
                + "\nPerímetro: " + calcularPerimetro()
                + "\nDistancia Ponto 1 a 2: " + getPa().distancia(getPb())
                + "\nDistancia Ponto 1 a 4: " + getPa().distancia(getPd())
                + "\nDistancia Ponto 3 a 2: " + getPc().distancia(getPb())
                + "\nDistancia Ponto 3 a 4: " + getPc().distancia(getPd())
                + "\nTipo: " + tipo(getPa(), getPb(), getPc(), getPd());

        // A ideia é imprimir os valores dos pontos, os valores dos lados e a área do mesmo
        return resultado;
    }

    /**
     * @return the pa
     */
    public Ponto getPa() {
        return pa;
    }

    /**
     * @return the pb
     */
    public Ponto getPb() {
        return pb;
    }

    /**
     * @return the pc
     */
    public Ponto getPc() {
        return pc;
    }

    /**
     * @return the pd
     */
    public Ponto getPd() {
        return pd;
    }

}
