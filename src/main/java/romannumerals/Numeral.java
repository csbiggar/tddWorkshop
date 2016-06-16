package romannumerals;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum Numeral {
    I(1, true),
    V(5, false),
    X(10, true),
    L(50, false),
    C(100, true),
    D(500, false);

    public final int value;
    private final boolean isSubtrativeForLargerNumber;


    public Optional<Numeral> subtractor() {
        Optional<Numeral> subtractor = Optional.empty();
        for (Numeral n : Arrays.asList(Numeral.values())) {
            if (n == this) {
                break;
            }
            if (n.isSubtrativeForLargerNumber()) {
                subtractor = Optional.of(n);
            }
        }
        return subtractor;
    }

    public static List<Numeral> decendingListOfNumerals() {
        List<Numeral> numerals = Arrays.asList(Numeral.values());
        Collections.reverse(numerals);
        return numerals;
    }


    public boolean hasSubtractor() {
        return subtractor().isPresent();
    }

    int subtractiveValueDifference() {
        if (hasSubtractor()) {
            return this.value - this.subtractor().get().value;
        }
        return 0;
    }
}
