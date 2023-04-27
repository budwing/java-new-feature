package com.github.budwing.java10;

import org.apache.commons.lang3.StringUtils;

public class AutomaticMod {
    public static void main(String[] args) {
        if (StringUtils.isAllEmpty(args)) {
            System.out.println("no args");
        } else {
            System.out.println("The argument value is " + StringUtils.joinWith(" and ", args));
        }
    }
}
