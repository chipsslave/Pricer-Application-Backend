package martin.dev.pricer.scraper.scrapers;

import martin.dev.pricer.scraper.*;

import java.util.Map;

public class WatchShopScraper extends Scraper {

    public WatchShopScraper(String name, Parser parser, ScraperState startingScraperState, Map<State, ScraperState> availableScraperStates) {
        super(name, parser, startingScraperState, availableScraperStates);
    }

    @Override
    public void nextPageUrl() {
        String[] x = this.getCurrentPageUrl().split("page=");
        int pageNumber = ScraperTools.parseIntegerFromString(x[1]) + 1;
        this.setCurrentPageNumber(pageNumber);
        this.setCurrentPageUrl(x[0] + "page=" + pageNumber);
    }
}
