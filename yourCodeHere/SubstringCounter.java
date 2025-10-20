// TO DO: add your implementation and JavaDoc

public class SubstringCounter implements Combiner<String, String, Integer> {

    public Integer combine(String operand1, String operand2) {

        if (operand2 == null || operand2.isEmpty()) {
            return 0;
        }
        if (operand1.length() < operand2.length()) {
            return 0;
        }

        int counter = 0;

        for (int i = 0; i <= operand1.length() - operand2.length(); i++) {

            if (operand1.substring(i, i + operand2.length()).equals(operand2)) {
                counter++;
            }


        }
        Integer result = counter;
        // count how many times operand2 occurs in operand1 as a substring
        // return the count
        //
        // O(NM) where N is the length of operand1 and M is the length of operand2
        //
        // Hint:
        //	-- You might want to look through Java's String class methods for some
        //	 useful tools: https://docs.oracle.com/javase/8/docs/api/java/lang/String.html
        //  -- You may for this assignment (naively) assume that these String methods are
        //	 all O(s) where s is the length of the string the method is called upon
        return result;
    }

    // --------------------------------------------------------
    // example testing code... edit this as much as you want!
    // --------------------------------------------------------

    public static void main(String[] args) {
        SubstringCounter sc = new SubstringCounter();
        if (sc.combine("abab", "ab") == 2 && sc.combine("aa", "aab") == 0
                && sc.combine("23232", "232") == 2
                && sc.combine("helloabchelloddefzdfjhello", "hello") == 3) {
            System.out.println("Yay 1");
        }

    }
}