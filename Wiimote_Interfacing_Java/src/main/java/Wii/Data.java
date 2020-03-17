package Wii;

import Enums.*;
import FRCCodexClasses.Codex;

public class Data {
    public static final double NULL_CODEX_VALUE = 0.0;
    public static final Codex<EInfared> infared1 = new Codex(NULL_CODEX_VALUE, EInfared.class);
    public static final Codex<EButtons> buttons1 = new Codex(NULL_CODEX_VALUE, EButtons.class);
    public static final Codex<EGyro> gyro1 = new Codex(NULL_CODEX_VALUE, EGyro.class);
//    public final Codex<EInfared> infared2 = new Codex(NULL_CODEX_VALUE, EInfared.class);
//    public final Codex<EButtons> buttons2 = new Codex(NULL_CODEX_VALUE, EButtons.class);
//    public final Codex<EGyro> gyro2 = new Codex(NULL_CODEX_VALUE, EGyro.class);
//    public final Codex<EInfared> infared3 = new Codex(NULL_CODEX_VALUE, EInfared.class);
//    public final Codex<EButtons> buttons3 = new Codex(NULL_CODEX_VALUE, EButtons.class);
//    public final Codex<EGyro> gyro3 = new Codex(NULL_CODEX_VALUE, EGyro.class);
//    public final Codex<EInfared> infared4 = new Codex(NULL_CODEX_VALUE, EInfared.class);
//    public final Codex<EButtons> buttons4 = new Codex(NULL_CODEX_VALUE, EButtons.class);
//    public final Codex<EGyro> gyro4 = new Codex(NULL_CODEX_VALUE, EGyro.class);

    //MUST RUN reset() ON ALL CODEXES AT THE BEGINNING OF EACH CYCLE OR THE CODE WON'T WORK
    public static final Codex[] allCodexes = {
            infared1,
            buttons1,
            gyro1,
//            infared2,
//            buttons2,
//            gyro2,
//            infared3,
//            buttons3,
//            gyro3,
//            infared4,
//            buttons4,
//            gyro4
    };
}


