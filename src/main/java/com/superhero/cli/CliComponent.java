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
        getUniqueById();
        getAllByContainingName();
        updateSuperHero();
        deleteSuperHero();
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

    private void getUniqueById() {
        System.out.println("******************************************** Start Get Unique Super Hero by id ******************************************************************");

        final SuperHeroModel hulkResult = superHeroApi.getByUniqueId(1L);
        System.out.println(hulkResult);

        final SuperHeroModel IronManResult = superHeroApi.getByUniqueId(2L);
        System.out.println(IronManResult);

        final SuperHeroModel SuperManResult = superHeroApi.getByUniqueId(3L);
        System.out.println(SuperManResult);

        final SuperHeroModel NotFound = superHeroApi.getByUniqueId(450L);
        System.out.println(NotFound);

        System.out.println("******************************************** End Get Unique Super Hero by id *********************************************************************");
    }

    private void getAllByContainingName() {
        System.out.println("******************************************** Start Get All Super Heroes By Contain Name **********************************************************");
        final List<SuperHeroModel> listResult = superHeroApi.getAllByContainingName("man");
        System.out.println(listResult);

        final List<SuperHeroModel> listResult2 = superHeroApi.getAllByContainingName("lk");
        System.out.println(listResult2);

        System.out.println("******************************************** End Get All Super Heroes By Contain Name ************************************************************");
    }

    private void updateSuperHero() {
        System.out.println("******************************************** Start Update a Super Hero ***************************************************************************");

        final SuperHeroModel hulkResult = superHeroApi.getByUniqueId(1L);
        final SuperHeroModel updatedHulk = new SuperHeroModel(hulkResult.getId(), "updatedHulkName");
        final SuperHeroModel updatedHulkResult = superHeroApi.createOrUpdate(updatedHulk);
        System.out.println(updatedHulkResult);

        final SuperHeroModel ironManResul = superHeroApi.getByUniqueId(2L);
        final SuperHeroModel updatedIronMan = new SuperHeroModel(ironManResul.getId(), "updatedIronMan2Name");
        final SuperHeroModel updatedIronManResult = superHeroApi.createOrUpdate(updatedIronMan);
        System.out.println(updatedIronManResult);

        System.out.println("******************************************** End Update a Super Hero *****************************************************************************");
    }

    private void deleteSuperHero() {
        System.out.println("******************************************** Start Delete a Super Hero ***************************************************************************");

        superHeroApi.delete(1L);
        superHeroApi.delete(2L);
        superHeroApi.delete(3L);
        superHeroApi.delete(1560L);
        System.out.println(superHeroApi.getAll());

        System.out.println("******************************************** End Delete a Super Hero *****************************************************************************");
    }
}
