package Members;

import Enums.PaymentStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CompetitionSwimmer extends Member {
    private String coach;

    private double breastTime;
    private String breastTimeDate;

    private double crawlTime;
    private String crawlTimeDate;

    private double backCrawlTime;
    private String backCrawlTimeDate;

    private double butterflyTime;
    private String butterflyTimeDate;


    public CompetitionSwimmer(String name, String status, int age, String coach, double breastTime, String breastTimeDate,
                              double crawlTime, String crawlTimeDate, double backCrawlTime, String backCrawlTimeDate,
                              double butterflyTime, String butterflyTimeDate, int fee, PaymentStatus paymentStatus) {
        super(name, status, age, fee, paymentStatus);
        this.coach = coach;
        this.breastTime = breastTime;
        this.breastTimeDate = breastTimeDate;
        this.crawlTime = crawlTime;
        this.crawlTimeDate = crawlTimeDate;
        this.backCrawlTime = backCrawlTime;
        this.backCrawlTimeDate = backCrawlTimeDate;
        this.butterflyTime = butterflyTime;
        this.butterflyTimeDate = butterflyTimeDate;



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

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        setBreastTimeDate(date.format(formatter));
    }

    //CrawlTime
    public double getCrawlTime() {
        return crawlTime;
    }

    public void setCrawlTime(double crawlTime) {
        this.crawlTime = crawlTime;

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:yy");
        setCrawlTimeDate(date.format(formatter));
    }

    //ButterflyTime
    public double getButterflyTime() {
        return butterflyTime;
    }

    public void setButterflyTime(double butterflyTime) {
        this.butterflyTime = butterflyTime;

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:yy");
        setButterflyTimeDate(date.format(formatter));
    }

    //BackCrawlTime
    public double getBackCrawlTime() {
        return backCrawlTime;
    }

    public void setBackCrawlTime(double backCrawlTime) {
        this.backCrawlTime = backCrawlTime;

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:yy");
        setBackCrawlTimeDate(date.format(formatter));
    }

    public String getBreastTimeDate() {
        return breastTimeDate;
    }

    public void setBreastTimeDate(String breastTimeDate) {
        this.breastTimeDate = breastTimeDate;
    }

    public String getCrawlTimeDate() {
        return crawlTimeDate;
    }

    public void setCrawlTimeDate(String crawlTimeDate) {
        this.crawlTimeDate = crawlTimeDate;
    }

    public String getBackCrawlTimeDate() {
        return backCrawlTimeDate;
    }

    public void setBackCrawlTimeDate(String backCrawlTimeDate) {
        this.backCrawlTimeDate = backCrawlTimeDate;
    }

    public String getButterflyTimeDate() {
        return butterflyTimeDate;
    }

    public void setButterflyTimeDate(String butterflyTimeDate) {
        this.butterflyTimeDate = butterflyTimeDate;
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

