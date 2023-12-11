import java.util.Arrays;

public class IntArr extends AbstractRowValue {
    int[] value;

    public IntArr() {
    }

    public IntArr(int[] value) {
        this.value = value;
    }

    public int[] getValue() {
        return value;
    }

    public void setValue(int val, int idx) {
        this.value[idx] = val;
    }

    public void printValue() {
        System.out.println(Arrays.toString(value));
    }
}
