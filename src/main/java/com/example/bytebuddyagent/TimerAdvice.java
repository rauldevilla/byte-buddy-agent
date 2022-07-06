package com.example.bytebuddyagent;

import net.bytebuddy.asm.Advice;

class TimerAdvice {

    @Advice.OnMethodEnter(suppress = Throwable.class)
    static long onEnter(
                        //,@Advice.FieldValue(value = "name", readOnly = false) String nameField
        ){

//        if(ary != null) {
//            for(int i =0 ; i < ary.length ; i++){
//                System.out.println("Argument: " + i + " is " + ary[i]);
//            }
//        }

//        System.out.println("Origin :" + origin);
//        System.out.println("Detailed Origin :" + detailedOrigin);

        //nameField = "Jack";
        return System.nanoTime();

    }

    @Advice.OnMethodExit(suppress = Throwable.class, onThrowable = Throwable.class)
    static void onExit(
                    @Advice.Enter long time,
                    @Advice.Origin String origin,
                    @Advice.This Object thisObject,
                    @Advice.Origin("#m") String detailedOrigin,
                    @Advice.Thrown Throwable exception) {
//        System.out.println("Inside exit method . . .");
        System.out.println(
                "<><><><><><><> " +
                        " Method " + origin +
                        ", This: " + thisObject.getClass().getName() +
                        ", Detailed origin: " + detailedOrigin + " " +
                        ", Throwable: " + exception + " " +
                        " -> Execution Time: " + (System.nanoTime() - time) + " nano seconds");
    }
}
