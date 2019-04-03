/* @ Arma X */
package classes;

public class Ponto {

    private double x;
    private double y;

    public Ponto() {
        this.x = 0.0;
        this.y = 0.0;
    }

    public Ponto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Ponto(Ponto outro) {
        this.x = outro.getX();
        this.y = outro.getY();
    }

    public String imprimirStr() {
        String texto = "";
        texto = "(" + this.x + ", " + this.y + ")";
        return texto;
    }

    public double distancia(Ponto outro) {
        double dist = 0;
        double dx = this.x - outro.getX();
        double dy = this.y - outro.getY();

        dist = Math.sqrt(dx * dx + dy * dy);
        return dist;
    }

    public double determinante(Ponto pb, Ponto pc) {
        
        double diagPrinc = this.x * pb.getY() * 1 + this.y * 1 * pc.getX() + 1 * pb.getX() * pc.getY();
        double diagSec = 1 * pb.getY() * pc.getX() + this.x * 1 * pc.getY() + this.y * pb.getX() * 1;
        double det = diagPrinc - diagSec;
        return det;
    }

    public boolean isColinear(Ponto pb, Ponto pc) {
        if (this.determinante(pb, pc) == 0) {
            return true;
        } else {
            return false;
        }
    }

    public double area(Ponto pb, Ponto pc) {
        if (this.isColinear(pb, pc)) {
            return 0.0;
        } else {
            return Math.abs(this.determinante(pb, pc) / 2.0);
        }

    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
