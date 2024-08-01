
/**
 * Write a description of class triallII here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
class triallII implements trial
{
    public void animal() {
        System.out.println("Heloooooooo");
    }
    
    public void sleep() {
        System.out.println("Hiiii");
    }
}

class Main{
    public static void main(String[] args) {
        triallII mytrial = new triallII();
        mytrial.animal();
        mytrial.sleep();
    }
}
