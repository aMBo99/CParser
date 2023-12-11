public class Int extends AbstractRowValue{
    int value;

    public Int() {
    }

    public Int(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void printValue() {
        System.out.println(value);
    }
}
