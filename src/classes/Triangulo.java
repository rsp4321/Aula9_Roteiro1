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

    private Ponto pa, pb, pc; // Os três vértices

    public Triangulo(Ponto pa, Ponto pb, Ponto pc) throws Exception {

//        this.pa = pa;
//        this.pb = pb;
//        this.pc = pc;
        // Dando três pontos quaisquer, não necessariamente definiremos um triângulo
        // Aplicaremos a condição de existência de triângulos para confirmar
//        if (verificarCondicaoExistenciaPorLados(pa, pb, pc)) {
        if (verificarCondicaoExistencia(pa, pb, pc)) {

            // Como é possível criar o triângulo, vamos preencher os pontos
            this.pa = pa;
            this.pb = pb;
            this.pc = pc;
        }
        else
            // Não vi outra estratégia se não tratar a falha como uma exceção para impedir a execução do código que instanciou o objeto
            // Sem exceções, o Java retornará o objeto do ponto com os valores padrão
            throw new Exception("Erro! Não é possível criar um triângulo a partir desses pontos.");
    }

    private boolean verificarCondicaoExistenciaPorLados(Ponto pa, Ponto pb, Ponto pc) {

        // Renomeei a função pois só verificar a condição de existência pelos lados não é suficiente
        // Os pontos também não podem ser colineares
        // Distância dos lados
        double d1 = pa.distancia(pb);
        double d2 = pb.distancia(pc);
        double d3 = pc.distancia(pa);

        // No caso, vamos fazer a lógica inversa:
        // vamos verificar se foge da condição de existência
        // Nesse caso , retornamos falso direto
        if (!((Math.abs(d2 - d3) < d1) && (d1 < d2 + d3))) {  // |b - c| < a < b + c
            return false;
        }

        if (!((Math.abs(d1 - d3) < d2) && (d2 < d1 + d3))) { // |a - c| < b < a + c
            return false;
        }

        if (!((Math.abs(d1 - d2) < d3) && (d3 < d1 + d2))) { // |a - b| < c < a + b
            return false;
        }

        return true;

    }

    private double determinante(Ponto pa, Ponto pb, Ponto pc) {

        double paX = pa.getX();
        double paY = pa.getY();
        double pbX = pb.getX();
        double pbY = pb.getY();
        double pcX = pc.getX();
        double pcY = pc.getY();

        // Vamos "calcular" o determinante necessário em rascunho antes
        // | pa.X   pa.Y    1 | pa.X   pa.Y
        // | pb.X   pb.Y    1 | pb.X   pb.Y
        // | pc.X   pc.Y    1 | pc.X   pc.Y
        // Logo:
        // Diagonal principal = (pa.X * pb.Y * 1) + (pa.Y * 1 * pc.X) + (1 * pb.X * pc.Y)
        // Diagonal secundária = (1 * pb.Y * pc.X) + (pa.X * 1 * pc.Y) + (pa.Y * pb.X * 1)
        double diagPrinc = (paX * pbY * 1) + (paY * 1 * pcX) + (1 * pbX * pcY);
        double diagSec = (1 * pbY * pcX) + (paX * 1 * pcY) + (paY * pbX * 1);

        double det = diagPrinc - diagSec;

        return det;
    }

    private boolean isColinear(Ponto pa, Ponto pb, Ponto pc) {

        if (determinante(pa, pb, pc) == 0) {
            return true;
        } else {
            return false;
        }
    }

    public double area(Ponto pa, Ponto pb, Ponto pc) {

        if (isColinear(pa, pb, pc)) {
            return 0.0;
        } else {
            return Math.abs(determinante(pa, pb, pc) / 2.0);
        }

    }

    public String tipo(Ponto pa, Ponto pb, Ponto pc) {
        double d1 = pa.distancia(pb);
        double d2 = pb.distancia(pc);
        double d3 = pc.distancia(pa);

        if (d1 == d2 && d2 == d3) {
            return "Triangulo equilatero";
        }
        if (d1 == d2 || d2 == d3 || d1 == d3) {
            return "Triangulo isoceles";
        } else {
            return "Triangulo escaleno";
        }
    }

    /**
     * Método para imprimir os valores dos pontos, a área e os valores dos
     * lados.
     *
     * @return Uma string contendo todas as informações.
     */
    public String imprimir() {

        String colinear = (isColinear(getPa(), getPb(), getPc()) == true) ? "é Colinear" : "Não é colinear";
        String resultado = "Area: " + area(getPa(), getPb(), getPc())
                + "\nDeterminante: " + determinante(getPa(), getPb(), getPc())
                + "\nDistancia Ponto 1 a 2: " + getPa().distancia(getPb())
                + "\nDistancia Ponto 1 a 3: " + getPa().distancia(getPc())
                + "\nDistancia Ponto 2 a 3: " + getPb().distancia(getPc())
                + "\n" + colinear + "\nTipo: " + tipo(getPa(), getPb(), getPc());

        // A ideia é imprimir os valores dos pontos, os valores dos lados e a área do mesmo
        return resultado;
    }

    /**
     * Método para verificar se é possível criar uim triângulo a partir de tais
     * pontos.
     *
     * @param pa
     * @param pb
     * @param pc
     * @return
     */
    private boolean verificarCondicaoExistencia(Ponto pa, Ponto pb, Ponto pc) {

        // Para poder ser criado o triângulo, duas condições devem ser satisfeitas:
        // 1) As distâncias entre os pontos devem obedecer à condição de existência de triângulos;
        // 2) Os pontos não podem ser colineares;
        //
        return verificarCondicaoExistenciaPorLados(pa, pb, pc) && !isColinear(pa, pb, pc);   // O Netbeans sugeriu condensar a condição no retorno diretamente
    }

    public Ponto getPa() {
        return pa;
    }

    public Ponto getPb() {
        return pb;
    }

    public Ponto getPc() {
        return pc;
    }

}
