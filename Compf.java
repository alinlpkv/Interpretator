public class Compf extends Stack {
    protected final static int SYM_LEFT = 0, SYM_RIGHT = 1, SYM_OPER = 2, SYM_OTHER = 3, SYM_TWICE_LEFT=4;
    protected static char lastchar ='(';
    private int symType(char c) {
        switch (c) {
            case '(': return SYM_LEFT;
            case ')':case ']':return SYM_RIGHT;
            case '+':case '-':case '*':case '/':return SYM_OPER;
            case '[': {
                return SYM_TWICE_LEFT;
            }
            default:
                return symOther(c);
        }
    }

    private void processSymbol(char c) {
        switch (symType(c)) {
            case SYM_LEFT:
                push(c);
                break;
            case SYM_RIGHT:
                processSuspendedSymbols(c);
                pop();
                break;
            case SYM_OPER:
                processSuspendedSymbols(c);
                push(c);
                break;
            case SYM_TWICE_LEFT:
                twiceNumber();
                processSuspendedSymbols('*');
                push('*');
                push ('(');
                break;

            case SYM_OTHER:
                if (symType(lastchar) == SYM_OTHER) {
                    nextTen(c);
                }
                else nextOther(c);
                break;
        }
        lastchar=c;
    }

    private void processSuspendedSymbols(char c) {
        while (precedes(top(), c)) nextOper(pop());
    }

    private int priority(char c) {
        return c == '+' ||c == '-'?1 :2;
    }

    private boolean precedes(char a, char b) {
        if (symType(a) == SYM_LEFT) return false;
        if (symType(b) == SYM_RIGHT) return true;
        return priority(a) >= priority(b);
    }

    protected int symOther(char c) {
        if (c < 'a' ||c > 'z'){
            System.out.println("Недопустимый символ: " + c);
            System.exit(0);
        }
        return SYM_OTHER;
    }

    protected void nextOper(char c) {
        System.out.print("" + c + " ");
    }

    protected void nextOther(char c) {
        nextOper(c);
    }

    protected void nextTen(char c) {
        nextOper(c);
    }

    protected void twiceNumber() {
       System.out.println("[]");
    }

    public void compile(char[] str) {
        processSymbol( '(');

        for (int i = 0; i < str.length; i++){
           processSymbol(str[i]);
         // System.out.println(str[i]);
        }

        processSymbol(')');
        System.out.print("\n");
    }


}
