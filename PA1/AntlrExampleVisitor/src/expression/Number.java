package expression;

public class Number extends Expression {
    int num;

    public Number(int num) {
        this.num = num;
    }

    @SuppressWarnings("removal")
    @Override
    public String toString() {
        return new Integer(num).toString();
    }
}