import java.util.Comparator;

/**
 * This class contains the logic how the array with the dominos should be sorted.
 */
public class DominoComparator implements Comparator<Dominostone> {

    /**
     * Compares two objects and sorts them in the given array.
     * @param o1 object one
     * @param o2 object two
     * @return -1 if o1 is smaller. 1 if o2 is smaller. 0 if they are the same.
     */
    @Override
    public int compare(Dominostone o1, Dominostone o2) {
        if(o1.getLeftField() < o2.getLeftField())
            return -1;
        else if(o1.getLeftField() > o2.getLeftField())
            return 1;
        else {
            if (o1.getRightField() < o2.getRightField())
                return -1;
            else if (o1.getRightField() > o2.getRightField())
                return 1;
            else
                return 0;
        }
    }
}
