package com.example.bytebuddyagent;

import net.bytebuddy.asm.Advice;

class TimerAdvice {

    @Advice.OnMethodEnter
    static void onEnter(@Advice.Local("initTimeMaker") long initTimeMaker) {
        initTimeMaker = System.currentTimeMillis();
    }

    @Advice.OnMethodExit(onThrowable = Throwable.class)
    //static void onExit(@Advice.Origin String method, @Advice.Enter long start) {
    static void onExit(@Advice.Origin String method, @Advice.Local("initTimeMaker") long initTimeMaker) {
        //System.out.println(" ******* " + method + " took " + (System.currentTimeMillis() - start));
        System.out.println(" ******* " + method + " took " + (System.currentTimeMillis() - initTimeMaker));
    }
}
