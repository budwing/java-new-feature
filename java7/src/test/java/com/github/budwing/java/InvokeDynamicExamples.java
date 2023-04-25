package com.github.budwing.java;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

@Slf4j
public class InvokeDynamicExamples {
    @Test
    public void stringReplaceInvokeDynamic() {
        Object receiver = "java new feature";
        try {
            MethodHandles.Lookup l = MethodHandles.lookup();
            MethodType mt = MethodType.methodType(String.class, char.class, char.class);
            MethodHandle mh = l.findVirtual(receiver.getClass(), "replace", mt);

            String ret;
            try {
                ret = (String) mh.invoke(receiver, 'a', 'b');
                log.info(ret);
            } catch (Throwable e) {
                log.error("exception occurs while invoking method:", e);
            }
        } catch (Exception e) {
            log.error("exception occurs while looking up method:", e);
        }
    }

    @Test
    public void constCallSiteInvokeDynamic() throws Throwable {
        MethodType type = MethodType.methodType(String.class, int.class, int.class);
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle handle = lookup.findVirtual(String.class, "substring", type);
        ConstantCallSite callSite = new ConstantCallSite(handle);
        MethodHandle invoker = callSite.dynamicInvoker();
        String result = (String) invoker.invoke("Java dynamic invoke", 0, 3);
        log.info(result);
    }
}
