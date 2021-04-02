public class IntegerToRoman {

    String roman[] = {"D", "DM", "M", "CM", "C", "LC", "L", "XL", "X", "IX", "V", "IV", "I"};
    int values[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String romanVal = "";
    int num;

    IntegerToRoman(int num){
        this.num = num;
    }

    public static void main(String[] args) {
        IntegerToRoman integerToRoman = new IntegerToRoman(11);
        integerToRoman.integerToRoman(integerToRoman.num);
        System.out.println("Integer " + integerToRoman.num + "\nRoman Value- " + integerToRoman.romanVal);
    }

    private void integerToRoman(int num) {
        int i = 0;
        while (i < values.length && num <= values[i]) {
            if (num == values[i]){
                romanVal += roman[i];
                return;
            }
            i++;
        }
        romanVal += roman[i];
        if (num % values[i] > 0) {
            integerToRoman(num % values[i]);
        }
    }
}
