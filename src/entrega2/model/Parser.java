package entrega2.model;

public abstract class Parser<T> {
	
    public abstract T parse(String value);

    @SuppressWarnings("unchecked")
	public static <T> Parser<T> getParser() {
       return (Parser<T>) new IntegerParser();
     
    }
}

