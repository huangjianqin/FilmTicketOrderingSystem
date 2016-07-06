package org.filmticketorderingsystem.generator.factory;

import org.filmticketorderingsystem.generator.OrderNumGenerator;
import org.filmticketorderingsystem.generator.OrderVerifiCodeGenerator;

/**
 * Created by 健勤 on 2016/5/12.
 */
public class GeneratorFactory {
    public static OrderNumGenerator getOrderNumGenerator(){
        return OrderNumGenerator.getGenerator();
    }

    public static OrderVerifiCodeGenerator getVerifiCodeGenerator(){
        return OrderVerifiCodeGenerator.getGenerator();
    }
}
