package services.comparators;

import domain.CompetitionSwimmer;

import java.util.Comparator;

public class BackCrawlTimeComparator implements Comparator<CompetitionSwimmer> {

    @Override
    public int compare(CompetitionSwimmer o1, CompetitionSwimmer o2) {
        return Double.compare(o1.getBackCrawlTime(), o2.getBackCrawlTime());
    }
}
