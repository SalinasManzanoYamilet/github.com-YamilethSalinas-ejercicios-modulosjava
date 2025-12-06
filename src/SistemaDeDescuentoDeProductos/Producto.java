public class Producto {
    private String nombre;
    private double precioOriginal;
    private double porcentajeDescuento;

    public Producto(String nombre, double precioOriginal, double porcentajeDescuento) {
        this.nombre = nombre;
        this.precioOriginal = precioOriginal;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioOriginal() {
        return precioOriginal;
    }

    public void setPrecioOriginal(double precioOriginal) {
        this.precioOriginal = precioOriginal;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    // Métodos de comportamiento
    public double calcularMontoDescuento() {
        return precioOriginal * (porcentajeDescuento / 100);
    }

    public double calcularPrecioFinal() {
        return precioOriginal - calcularMontoDescuento();
    }
}
