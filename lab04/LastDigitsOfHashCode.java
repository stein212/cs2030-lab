public class LastDigitsOfHashCode implements Transformer<Object,Integer> {
    private final int numDigits;

    public LastDigitsOfHashCode(int numDigits) {
        this.numDigits = numDigits;
    }

    @Override
    public Integer transform(Object o) {
        String hashCodeStr = String.valueOf(o.hashCode());
        if (hashCodeStr.length() < numDigits) {
            return Integer.parseInt(hashCodeStr);
        }

        return Integer.parseInt(hashCodeStr.substring(hashCodeStr.length() - numDigits));
    }
}
