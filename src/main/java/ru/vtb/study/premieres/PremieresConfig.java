package ru.vtb.study.premieres;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.vtb.study.premieres.interfaces.IPremiere;
import ru.vtb.study.premieres.model.Premiere;
import ru.vtb.study.premieres.interfaces.IPremiereService;
import ru.vtb.study.premieres.service.PremiereService;

@Configuration
public class PremieresConfig {

    @Bean
    public IPremiereService iPremiereService() {
        return new PremiereService();
    }

    @Bean(name = "premiere")
    @Scope("prototype")
    public IPremiere premierePrototype(String name, String description, int ageCategory, int availableSeats) {
        return new Premiere(name, description, ageCategory, availableSeats);
    }
}
