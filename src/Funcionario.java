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
        this.salarioBruto = Math.round(salario*100.0)/100.0;
        this.salarioLiquido = Math.round(salario*100.0)/100.0;
    }

    @Override
    public String toString() {
        return "Funcionario - " + "codigo=" + codigo + "\n" +
                "- salarioBruto = " + salarioBruto + "\n" +
                "- salarioLiquido = " + salarioLiquido + "\n" +
                "- descontosPrevidencia = " + descontosPrevidencia + "\n" +
                "- descontoPlanoSaude = " + descontoPlanoSaude + "\n" +
                "- descontoInss = " + descontoInss + "\n" +
                "- descontoImpostoRenda = " + descontoImpostoRenda + "\n" +
                "- descontoTotal = " + descontoTotal + "\n";
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
        this.salarioBruto = Math.round(salarioBruto*100.0)/100.0;
    }

    public double getSalarioLiquido() {
        return salarioLiquido;
    }

    public void setSalarioLiquido(double salarioLiquido) {
        this.salarioLiquido = Math.round(salarioLiquido*100.0)/100.0;;
    }

    public double getDescontosPrevidencia() {
        return descontosPrevidencia;
    }

    public void setDescontosPrevidencia(double descontosPrevidencia) {
        this.descontosPrevidencia = Math.round(descontosPrevidencia*100.0)/100.0;;
    }

    public double getDescontoPlanoSaude() {
        return descontoPlanoSaude;
    }

    public void setDescontoPlanoSaude(double descontoPlanoSaude) {
        this.descontoPlanoSaude = Math.round(descontoPlanoSaude*100.0)/100.0;;
    }

    public double getDescontoInss() {
        return descontoInss;
    }

    public void setDescontoInss(double descontoInss) {
        this.descontoInss = Math.round(descontoInss*100.0)/100.0;;
    }

    public double getDescontoImpostoRenda() {
        return descontoImpostoRenda;
    }

    public void setDescontoImpostoRenda(double descontoImpostoRenda) {
        this.descontoImpostoRenda = Math.round(descontoImpostoRenda*100.0)/100.0;;
    }

    public double getDescontoTotal() {
        return descontoTotal;
    }

    public void setDescontoTotal(double descontoTotal) {
        this.descontoTotal = Math.round(descontoTotal*100.0)/100.0;;
    }
}
