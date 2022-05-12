package com.example.bytebuddyagent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

public class TimerAgent {

    private static void mode1(Instrumentation instrumentation) {
        new AgentBuilder.Default()
                .type(ElementMatchers.nameStartsWith("BP"))
                .transform((builder, type, classLoader, module) ->
                        builder.method(ElementMatchers.any())
                                .intercept(MethodDelegation.to(TimingInterceptor.class))
                ).installOn(instrumentation);
    }

    private static void mode2() {
        new AgentBuilder.Default()
                .type(ElementMatchers.nameStartsWith("BP"))
                .transform((builder, type, classLoader, module) ->
                        builder.visit(Advice.to(TimerAdvice.class).on(ElementMatchers.any()))
                );
    }

    public static void premain(String arguments,
                               Instrumentation instrumentation) {
        System.out.println(" ******* Agent starts");
        mode1(instrumentation);
        //mode2();
    }
}
