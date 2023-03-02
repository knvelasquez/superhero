package com.superhero.cli;

import com.superhero.api.SuperHeroApi;
import com.superhero.model.SuperHeroModel;
import com.superhero.repository.SuperHeroRepository;
import com.superhero.service.H2SuperHeroApi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CliComponent {
    private static final Logger logger = LogManager.getLogger(CliComponent.class);
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
        logger.info("******************************************** Start Create a Super Hero ***************************************************************************");
        final SuperHeroModel HulkModel = new SuperHeroModel("Hulk");
        final SuperHeroModel IronManModel = new SuperHeroModel("IronMan");
        final SuperHeroModel SuperManModel = new SuperHeroModel("SuperMan");

        final SuperHeroModel hulkResult = superHeroApi.createOrUpdate(HulkModel);
        final SuperHeroModel ironManResult = superHeroApi.createOrUpdate(IronManModel);
        final SuperHeroModel superManResult = superHeroApi.createOrUpdate(SuperManModel);

        logger.info(hulkResult.toString());
        logger.info(ironManResult.toString());
        logger.info(superManResult.toString());

        logger.info("******************************************** End Create a Super Hero ****************************************************************************");
    }

    private void getAllSuperHeroes() {
        logger.info("******************************************** Start Get All Super Heroes *************************************************************************");

        final List<SuperHeroModel> listAllSuperHeroes = superHeroApi.getAll();
        logger.info(listAllSuperHeroes);

        logger.info("******************************************** End Get All Super Heroes ***************************************************************************");
    }

    private void getUniqueById() {
        logger.info("******************************************** Start Get Unique Super Hero by id ******************************************************************");

        final SuperHeroModel hulkResult = superHeroApi.getByUniqueId(1L);
        logger.info(hulkResult);

        final SuperHeroModel IronManResult = superHeroApi.getByUniqueId(2L);
        logger.info(IronManResult);

        final SuperHeroModel SuperManResult = superHeroApi.getByUniqueId(3L);
        logger.info(SuperManResult);

        final SuperHeroModel NotFound = superHeroApi.getByUniqueId(450L);
        logger.info(NotFound);

        logger.info("******************************************** End Get Unique Super Hero by id *********************************************************************");
    }

    private void getAllByContainingName() {
        logger.info("******************************************** Start Get All Super Heroes By Contain Name **********************************************************");
        final List<SuperHeroModel> listResult = superHeroApi.getAllByContainingName("man");
        logger.info(listResult);

        final List<SuperHeroModel> listResult2 = superHeroApi.getAllByContainingName("lk");
        logger.info(listResult2);

        logger.info("******************************************** End Get All Super Heroes By Contain Name ************************************************************");
    }

    private void updateSuperHero() {
        logger.info("******************************************** Start Update a Super Hero ***************************************************************************");

        final SuperHeroModel hulkResult = superHeroApi.getByUniqueId(1L);
        final SuperHeroModel updatedHulk = new SuperHeroModel(hulkResult.getId(), "updatedHulkName");
        final SuperHeroModel updatedHulkResult = superHeroApi.createOrUpdate(updatedHulk);
        logger.info(updatedHulkResult);

        final SuperHeroModel ironManResul = superHeroApi.getByUniqueId(2L);
        final SuperHeroModel updatedIronMan = new SuperHeroModel(ironManResul.getId(), "updatedIronMan2Name");
        final SuperHeroModel updatedIronManResult = superHeroApi.createOrUpdate(updatedIronMan);
        logger.info(updatedIronManResult);

        logger.info("******************************************** End Update a Super Hero *****************************************************************************");
    }

    private void deleteSuperHero() {
        logger.info("******************************************** Start Delete a Super Hero ***************************************************************************");

        superHeroApi.delete(1L);
        superHeroApi.delete(2L);
        superHeroApi.delete(3L);
        superHeroApi.delete(1560L);
        logger.info(superHeroApi.getAll());

        logger.info("******************************************** End Delete a Super Hero *****************************************************************************");
    }
}
