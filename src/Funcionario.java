public class Funcionario {
    private int codigo;
    private double salarioBruto;
    private double salarioLiquido;
    private double descontosPrevidencia;
    private double descontoPlanoSaude;
    private double descontoInss;
    private double descontoImpostoRenda;
    private double descontoTotal;

    private String txt;

    public Funcionario(int codigo, double salario){
        this.codigo = codigo;
        this.salarioBruto = salario;
    }

    @Override
    public String toString() {
        return "Funcionario - " + "codigo=" + codigo + "\n" +
                "- salarioBruto=" + salarioBruto + "\n" +
                "- salarioLiquido=" + salarioLiquido + "\n" +
                "- descontosPrevidencia=" + descontosPrevidencia + "\n" +
                "- descontoPlanoSaude=" + descontoPlanoSaude + "\n" +
                "- descontoInss=" + descontoInss + "\n" +
                "- descontoImpostoRenda=" + descontoImpostoRenda + "\n" +
                "- descontoTotal=" + descontoTotal + "\n";
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getSalarioBruto() {
        return salarioBruto;
    }

    public void setSalarioBruto(double salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    public double getSalarioLiquido() {
        return salarioLiquido;
    }

    public void setSalarioLiquido(double salarioLiquido) {
        this.salarioLiquido = salarioLiquido;
    }

    public double getDescontosPrevidencia() {
        return descontosPrevidencia;
    }

    public void setDescontosPrevidencia(double descontosPrevidencia) {
        this.descontosPrevidencia = descontosPrevidencia;
    }

    public double getDescontoPlanoSaude() {
        return descontoPlanoSaude;
    }

    public void setDescontoPlanoSaude(double descontoPlanoSaude) {
        this.descontoPlanoSaude = descontoPlanoSaude;
    }

    public double getDescontoInss() {
        return descontoInss;
    }

    public void setDescontoInss(double descontoInss) {
        this.descontoInss = descontoInss;
    }

    public double getDescontoImpostoRenda() {
        return descontoImpostoRenda;
    }

    public void setDescontoImpostoRenda(double descontoImpostoRenda) {
        this.descontoImpostoRenda = descontoImpostoRenda;
    }

    public double getDescontoTotal() {
        return descontoTotal;
    }

    public void setDescontoTotal(double descontoTotal) {
        this.descontoTotal = descontoTotal;
    }
}
