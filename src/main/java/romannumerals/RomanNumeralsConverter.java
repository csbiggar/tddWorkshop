package romannumerals;

public class RomanNumeralsConverter {

    private final int arabicNumber;

    public RomanNumeralsConverter(int arabicNumber) {
        this.arabicNumber = arabicNumber;
    }

    public String convert() {
        StringBuilder number = new StringBuilder();
        int remainingValue = arabicNumber;
        for (Numeral numeral : Numeral.decendingListOfNumerals()) {

            if (remainingValue >= numeral.value) {
                int multiplesRequired = remainingValue / numeral.value;
                for (int i = 0; i < multiplesRequired; i++) {
                    number.append(numeral.name());
                }
                remainingValue = remainingValue - multiplesRequired * numeral.value;
            }

            if (numeral.hasSubtractor()) {
                Numeral subtractor = numeral.subtractor().get();
                if (remainingValue >= numeral.subtractiveValueDifference()) {
                    number.append(subtractor.name());
                    number.append(numeral.name());
                    remainingValue = remainingValue - numeral.subtractiveValueDifference();
                }
            }
        }

        return number.toString();

    }


}
