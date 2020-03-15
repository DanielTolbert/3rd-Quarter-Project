import Enums.*;
import FRCCodexClasses.Codex;

public class Data {
    public static final double NULL_CODEX_VALUE = 0.0;
    public final Codex<EInfared> infared = new Codex(NULL_CODEX_VALUE, EInfared.class);
    public final Codex<EButtons> buttons = new Codex(NULL_CODEX_VALUE, EButtons.class);
    public final Codex<EGyro> gyro = new Codex(NULL_CODEX_VALUE, EGyro.class);

    //MUST RUN reset() ON ALL CODEXES AT THE BEGINNING OF EACH CYCLE OR THE CODE WON'T WORK
    public final Codex[] allCodexes = {
            infared,
            buttons,
            gyro
    };
}
