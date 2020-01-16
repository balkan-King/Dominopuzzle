import java.util.Comparator;

public class DominoComparator implements Comparator<Dominostone> {

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
