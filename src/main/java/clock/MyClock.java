package clock;

import java.time.Clock;

public class MyClock {
    private static Clock clock = Clock.systemDefaultZone();

    public static Clock getIntance(){
        return clock;
    }

    public static void definirClock(Clock unClock){
        clock = unClock;
    }

}
