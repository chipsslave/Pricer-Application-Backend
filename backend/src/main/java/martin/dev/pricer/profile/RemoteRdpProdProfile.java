package martin.dev.pricer.profile;

import lombok.extern.slf4j.Slf4j;
import martin.dev.pricer.data.repository.*;
import martin.dev.pricer.data.service.*;
import martin.dev.pricer.discord.DiscordService;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import javax.security.auth.login.LoginException;

@Profile("remote-rdp-prod")
@Configuration
@Slf4j
public class RemoteRdpProdProfile {

    private final StatusRepository statusRepository;
    private final UrlRepository urlRepository;
    private final ItemRepository itemRepository;
    private final ParserErrorRepository parserErrorRepository;
    private final DealRepository dealRepository;

    @Value("${discord.api.key}")
    private String DiscordApiKey;

    public RemoteRdpProdProfile(StatusRepository statusRepository, UrlRepository urlRepository, ItemRepository itemRepository, ParserErrorRepository parserErrorRepository, DealRepository dealRepository) {
        this.statusRepository = statusRepository;
        this.urlRepository = urlRepository;
        this.itemRepository = itemRepository;
        this.parserErrorRepository = parserErrorRepository;
        this.dealRepository = dealRepository;
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(2);
        return threadPoolTaskScheduler;
    }

    @Bean
    public ItemService getSqlItemService() {
        return new ItemService(itemRepository);
    }

    @Bean
    public StatusService getSqlStatusService() {
        return new StatusService(statusRepository);
    }

    @Bean
    public UrlService getSqlUrlService() {
        return new UrlService(urlRepository);
    }

    @Bean
    public ParserErrorService getParserErrorService() {
        return new ParserErrorService(parserErrorRepository);
    }

    @Bean
    public DealService getDealService() {
        return new DealService(dealRepository);
    }

    @Bean
    public DiscordService discordBot() {
        try {
            JDA jda = JDABuilder.createDefault(DiscordApiKey).build();
            return new DiscordService(jda);
        } catch (LoginException e) {
            e.printStackTrace();
        }
        return null;
    }
}