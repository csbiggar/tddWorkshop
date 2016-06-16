package romannumerals

import spock.lang.Specification
import spock.lang.Unroll

import static romannumerals.Numeral.*


/**
 * Created by Carolyn on 29/03/2016.
 */
class RomanNumeralsConverterSpec extends Specification {

    def "0 returns empty string"() {
        expect:
        new RomanNumeralsConverter(0).convert() == ""
    }

    @Unroll
    def "#arabicNumber returns #romanNumber"() {
        expect:
        new RomanNumeralsConverter(arabicNumber).convert() == romanNumber

        where:
        arabicNumber | romanNumber
        1            | "I"
        2            | "II"
        3            | "III"
        4            | "IV"
        5            | "V"
        6            | "VI"
        7            | "VII"

        9            | "IX"
        10           | "X"
        11           | "XI"

        14           | "XIV"
        15           | "XV"
        16           | "XVI"

        19           | "XIX"
        20           | "XX"
        21           | "XXI"

        24           | "XXIV"
        25           | "XXV"
        26           | "XXVI"

        29           | "XXIX"
        30           | "XXX"

        40           | "XL"
        41           | "XLI"
        42           | "XLII"
        43           | "XLIII"
        44           | "XLIV"
        45           | "XLV"
        46           | "XLVI"
        47           | "XLVII"
        48           | "XLVIII"
        49           | "XLIX"

        50           | "L"
        51           | "LI"
        52           | "LII"
        53           | "LIII"
        54           | "LIV"
        55           | "LV"
        56           | "LVI"
        57           | "LVII"
        58           | "LVIII"
        59           | "LIX"

        60           | "LX"
        61           | "LXI"
        89           | "LXXXIX"

        90           | "XC"
        91           | "XCI"
        99           | "XCIX"

        100          | "C"
        101          | "CI"
        142          | "CXLII"
        200          | "CC"
        378          | "CCCLXXVIII"

        //Just checking now ....
        500          | "D"
        753          | "DCCLIII"

    }


    @Unroll
    def "allowed subtractor for #numeral is #result"() {
        expect:
        numeral.subtractor() == result;

        where:
        numeral | result
        I       | Optional.empty()
        V       | Optional.of(I)
        X       | Optional.of(I)
        L       | Optional.of(X)
        C       | Optional.of(X)
        D       | Optional.of(C)
    }

    def "descending list of numeral values"() {
        expect:
        Numeral.decendingListOfNumerals() == [D, C, L, X, V, I]

    }


}
