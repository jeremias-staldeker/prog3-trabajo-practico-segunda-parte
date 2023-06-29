package entrega2.model;

public class IntegerParser extends Parser<Integer> {

	@Override
    public Integer parse(String value) {
        return Integer.parseInt(value);
    }
}
