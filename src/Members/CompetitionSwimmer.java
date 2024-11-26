package Members;

public class CompetitionSwimmer extends AllMembers {
    private String coach;
    private double breastTime;
    private double crawlTime;
    private double backCrawlTime;
    private double butterflyTime;

    public CompetitionSwimmer(String name, String status, int age, String coach, double breastTime, double crawlTime, double backCrawlTime, double butterflyTime, int fee) {
        super(name, status, age, fee);
        this.coach = coach;
        this.breastTime = breastTime;
        this.crawlTime = crawlTime;
        this.backCrawlTime = backCrawlTime;
        this.butterflyTime = butterflyTime;

    }


    //getter & setter specifics for competitionSwimmer

    //coach
    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    //BreastTime
    public double getBreastTime() {
        return breastTime;
    }

    public void setBreastTime(double breastTime) {
        this.breastTime = breastTime;
    }

    //CrawlTime
    public double getCrawlTime() {
        return crawlTime;
    }

    public void setCrawlTime(double crawlTime) {
        this.crawlTime = crawlTime;
    }

    //ButterflyTime
    public double getButterflyTime() {
        return butterflyTime;
    }

    public void setButterflyTime(double butterflyTime) {
        this.butterflyTime = butterflyTime;
    }

    //BackCrawlTime
    public double getBackCrawlTime() {
        return backCrawlTime;
    }

    public void setBackCrawlTime(double backCrawlTime) {
        this.backCrawlTime = backCrawlTime;
    }

    @Override
    public String toString() {

        return super.toString() +
                "\nCoach: " + coach +
                "\nBreaststroke time: " + breastTime +
                "\nCrawl time: " + crawlTime +
                "\nBack crawl time: " + backCrawlTime +
                "\nButterfly time: " + butterflyTime +
                "\n_____________________________________________\n";
    }


}

