package com.github.budwing.java;

import com.beust.jcommander.internal.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

/**
 * In Java 10, local var has been enhanced with support for allowing var to be used when declaring the formal parameters of implicitly typed lambda expressions.
 */
@Slf4j
public class LambdaVarExamples {
    /**
     * One benefit of uniformity is that modifiers, notably annotations, can be applied to local variables and lambda formals without losing brevity
     */
    @Test
    public void exampleForLambdaVar() {
        StringLengthPlus noVar = (s,p)->s.length()+p;
        StringLengthPlus withVar = (@Nullable var s, var p)->s.length()+p;
        // mixed style is illegal
        // StringLengthPlus lengthPlus3 = (var s, int p)->s.length()+p+3;
        log.info("no var: {}",noVar.getPlusLen("test", 100));
        log.info("with var: {}",withVar.getPlusLen("test", 100));
    }
}
