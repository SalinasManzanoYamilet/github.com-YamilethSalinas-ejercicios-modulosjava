public class Empleado {

    private String nombre;
    private double salarioBase;
    private int diasAsistidos;

    public Empleado(String nombre, double salarioBase, int diasAsistidos) {
        this.nombre = nombre;
        this.salarioBase = salarioBase;
        this.diasAsistidos = diasAsistidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public int getDiasAsistidos() {
        return diasAsistidos;
    }

    public void setDiasAsistidos(int diasAsistidos) {
        this.diasAsistidos = diasAsistidos;
    }

    public double calcularSalarioTotal(int diasTotales) {
        double pagoPorDia = salarioBase / diasTotales;
        return pagoPorDia * diasAsistidos;
    }

    public boolean asistenciaCompleta(int diasTotales) {
        return diasAsistidos == diasTotales;
    }

}
