package Models;

import Members.CompetitionSwimmer;

import java.util.Comparator;

public class BreastTimeComparator implements Comparator<CompetitionSwimmer> {

    @Override
    public int compare(CompetitionSwimmer o1, CompetitionSwimmer o2) {
        return Double.compare(o1.getBreastTime(), o2.getBreastTime());
    }
}
