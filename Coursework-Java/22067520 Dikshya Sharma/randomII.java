
/**
 * Write a description of class randomII here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
class randomII extends random{
    public void animal() {
        System.out.println("HEloooooooo");
    }
}

class Main{
    public static void main(String[] args){
        randomII myrandom = new randomII();
        myrandom.animal();
        myrandom.sleep();
    }
}
