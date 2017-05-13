package edu.upc.eetac.dsa.calculadora;

/**
 * Created by Home on 10/05/2017.
 */

public class Operacio {

    private Integer num1;
    private Integer num2;
    private String op;
    private Float res;

    public Operacio (Integer num1, Integer num2, String op, Float res) {
        this.num1 = num1;
        this.num2 = num2;
        this.op = op;
        this.res = res;
    }

    public Integer getNum1() {
        return num1;
    }

    public void setNum1(Integer num1) {
        this.num1 = num1;
    }

    public Integer getNum2() {
        return num2;
    }

    public void setNum2(Integer num2) {
        this.num2 = num2;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public Float getRes() {
        return res;
    }

    public void setRes(Float res) {
        this.res = res;
    }

    @Override
    public String toString(){
        return getNum1() + " " + getOp() + " " + getNum2() + " = " + getRes();
    }
}
