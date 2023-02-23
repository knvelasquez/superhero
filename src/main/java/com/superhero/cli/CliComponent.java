package com.superhero.cli;

import com.superhero.api.SuperHeroApi;
import com.superhero.model.SuperHeroModel;
import com.superhero.repository.SuperHeroRepository;
import com.superhero.service.H2SuperHeroApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CliComponent {
    private final SuperHeroApi superHeroApi;

    @Autowired
    public CliComponent(SuperHeroRepository superHeroRepository) {
        this.superHeroApi = new H2SuperHeroApi(superHeroRepository);
        init();
    }

    private void init() {
        createNewSuperHero();
    }

    private void createNewSuperHero(){
        System.out.println("******************************************** Start Create a Super Hero ***************************************************************************");
        final SuperHeroModel HulkModel = new SuperHeroModel("Hulk");
        final SuperHeroModel IronManModel = new SuperHeroModel("IronMan");
        final SuperHeroModel SuperManModel = new SuperHeroModel("SuperMan");

        SuperHeroModel hulkResult = superHeroApi.createOrUpdate(HulkModel);
        SuperHeroModel ironManResult = superHeroApi.createOrUpdate(IronManModel);
        SuperHeroModel superManResult = superHeroApi.createOrUpdate(SuperManModel);

        System.out.println(hulkResult.toString());
        System.out.println(ironManResult.toString());
        System.out.println(superManResult.toString());

        System.out.println("******************************************** End Create a Super Hero ****************************************************************************");
    }
}
