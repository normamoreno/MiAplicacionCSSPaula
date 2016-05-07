package mx.edu.utng.css;

/**
 * Clase que hara las casillas en X y Y
 */
public class Casilla {
    public int x,y,ancho;
    public int contenido=0;
    public boolean destapado=false;
    public void fijarxy(int x,int y, int ancho) {
        this.x=x;
        this.y=y;
        this.ancho=ancho;
    }

    /**
     * Método para acomodar las casillas
     * @param xx
     * @param yy
     * @return
     */
    public boolean dentro(int xx,int yy) {
        if (xx>=this.x && xx<=this.x+ancho && yy>=this.y && yy<=this.y+ancho)
            return true;
        else
            return false;
    }
}
