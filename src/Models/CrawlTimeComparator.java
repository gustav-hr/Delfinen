package Models;

import Members.CompetitionSwimmer;

import java.util.Comparator;

public class CrawlTimeComparator implements Comparator<CompetitionSwimmer> {

    @Override
    public int compare (CompetitionSwimmer o1, CompetitionSwimmer o2){
        return Double.compare(o1.getCrawlTime(), o2.getCrawlTime());
    }
}
