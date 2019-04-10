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
//        if (!(d1 == d3 || d2 == d4)) {//Lados opostos iguais
//            return false;
//        }
//        if (!(d1 == d4 || d2 == d3)) {//Formam Angulo de 90 Graus
//            return false;
//        }
        // Contra-exemplo para o código acima (gera falha):
        // pa = (5,1)
        // pb = (1,4)
        // pc = (6,4)
        // pd = (10,1)
        // No arquivo "plotagem_quadrado_contraexemplo.ggb", são plotadas essas coordenadas e o resultado é um paralelogramo
        // Para definir um quadrado, não basta os lados serem iguais somente. Os ângulos precisam ser iguais à 90°
        // Para tal, será necessário criar um método para calcular o ângulo entre três pontos
        // E calcular os quatro ângulos para verificar: "ABC", "BCD", "CDA" e "DAB"
        double angulo_abc = this.calcularAnguloABC(pa, pb, pc);
        double angulo_bcd = this.calcularAnguloABC(pb, pc, pd);
        double angulo_cda = this.calcularAnguloABC(pc, pd, pa);
        double angulo_dab = this.calcularAnguloABC(pd, pa, pb);

        // Verificando os ângulos
        if ((angulo_abc == angulo_bcd)
                && (angulo_bcd == angulo_cda)
                && (angulo_cda == angulo_dab)
                && (angulo_dab == Math.PI / 2)) {  // O retorno do método é em radianos

            //return true;
            // Verificando os lados
            if ((d1 == d2)
                    && (d2 == d3)
                    && (d3 == d4)) {
                return true;    // Lados iguais e ângulos iguais à 90°. Caso positivo
            } else {
                return false;   // Angulos iguais à 90°  e lados diferentes. Caso negativo
            }
        } else {
            return false;       // Ângulos e lados diferentes. Caso negativo
        }
//        return true;
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

        // Para calcular o ângulo, usaremos o conceito de ângulo entre dois vetores
        // Para tal, obtive a fórmula conforme esse artigo: https://mundoeducacao.bol.uol.com.br/matematica/angulo-entre-dois-vetores.htm
        // no nosso caso, os vetores serão o "AB" e o "BC"
        // Definindo as coordenadas do vetor "AB"
        double x1 = pb.getX() - pa.getX();
        double y1 = pb.getY() - pa.getY();

        // vetor "BC"
        double x2 = pc.getX() - pb.getX();
        double y2 = pc.getY() - pb.getY();

        // O código a seguir não tem tratamento de precisão nos valores e pode falhar por questões disso
        double cosseno = ((x1 * x2) + (y1 * y2))
                / (Math.sqrt(Math.pow(x1, 2) + Math.pow(y1, 2)) // Math.sqrt() retorna a raíz quadrada
                * Math.sqrt(Math.pow(x2, 2) + Math.pow(y2, 2)));    // Math.pow() retorna o quadrado nesse caso

        double angulo;

        if (cosseno >= 0) {
            angulo = Math.acos(cosseno);    // O método Math.acos só retorna valores de cossendo entre 0 e 1 inclusive. Cálculando o módulo para evitar valores negativos
        } else {
            angulo = Math.PI - Math.acos(Math.abs(cosseno));  // Ao calcular o arccos do módulo, ele retornará o suplemento do ângulo a descobrir
        }

//        double angulo = Math.acos(
//                Math.abs( // O método Math.acos só retorna valores de cossendo entre 0 e 1 inclusive. Cálculando o módulo para evitar valores negativos
//                        ((x1 * x2) + (y1 * y2))
//                        / (Math.sqrt(Math.pow(x1, 2) + Math.pow(y1, 2)) // Math.sqrt() retorna a raíz quadrada
//                        * Math.sqrt(Math.pow(x2, 2) + Math.pow(y2, 2))) // Math.pow() retorna o quadrado nesse caso
//                ) // Math.abs()
//        );
        return angulo;
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
