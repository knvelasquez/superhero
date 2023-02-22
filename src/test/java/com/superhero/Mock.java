package com.superhero;

import com.superhero.model.SuperHeroModel;

import java.util.ArrayList;
import java.util.List;

public class Mock {

    static final SuperHeroModel superMan = new SuperHeroModel(1L, "SuperMan");
    static final SuperHeroModel batMan = new SuperHeroModel(2L, "BatMan");
    static final SuperHeroModel spiderMan = new SuperHeroModel(3L, "SpiderMan");
    static final SuperHeroModel deadpool = new SuperHeroModel(4L, "DeadPool");
    static final SuperHeroModel ironMan = new SuperHeroModel(5L, "IronMan");


    static List<SuperHeroModel> AllSuperHeroes() {
        List<SuperHeroModel> listAllSuperHeroes = new ArrayList<>();
        listAllSuperHeroes.add(superMan);
        listAllSuperHeroes.add(batMan);
        listAllSuperHeroes.add(spiderMan);
        listAllSuperHeroes.add(deadpool);
        listAllSuperHeroes.add(ironMan);

        return listAllSuperHeroes;
    }

    static List<SuperHeroModel> listSuperHeroesContainManInName() {
        List<SuperHeroModel> listSuperHeroesResult = new ArrayList<>();

        listSuperHeroesResult.add(superMan);
        listSuperHeroesResult.add(batMan);
        listSuperHeroesResult.add(spiderMan);
        listSuperHeroesResult.add(ironMan);

        return listSuperHeroesResult;
    }
}
