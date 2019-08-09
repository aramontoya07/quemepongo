package clock;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public class MyClock {
    private static Clock clock = Clock.systemDefaultZone();

    public static Clock getIntance(){
        return clock;
    }

    public static void definirClock(Clock unClock){
        clock = unClock;
    }

}
