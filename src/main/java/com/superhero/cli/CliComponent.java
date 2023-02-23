package com.superhero.cli;

import com.superhero.api.SuperHeroApi;
import com.superhero.model.SuperHeroModel;
import com.superhero.repository.SuperHeroRepository;
import com.superhero.service.H2SuperHeroApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
        getAllSuperHeroes();
    }

    private void createNewSuperHero() {
        System.out.println("******************************************** Start Create a Super Hero ***************************************************************************");
        final SuperHeroModel HulkModel = new SuperHeroModel("Hulk");
        final SuperHeroModel IronManModel = new SuperHeroModel("IronMan");
        final SuperHeroModel SuperManModel = new SuperHeroModel("SuperMan");

        final SuperHeroModel hulkResult = superHeroApi.createOrUpdate(HulkModel);
        final SuperHeroModel ironManResult = superHeroApi.createOrUpdate(IronManModel);
        final SuperHeroModel superManResult = superHeroApi.createOrUpdate(SuperManModel);

        System.out.println(hulkResult.toString());
        System.out.println(ironManResult.toString());
        System.out.println(superManResult.toString());

        System.out.println("******************************************** End Create a Super Hero ****************************************************************************");
    }

    private void getAllSuperHeroes() {
        System.out.println("******************************************** Start Get All Super Heroes *************************************************************************");

        final List<SuperHeroModel> listAllSuperHeroes = superHeroApi.getAll();
        System.out.println(listAllSuperHeroes);

        System.out.println("******************************************** End Get All Super Heroes ***************************************************************************");
    }
}
