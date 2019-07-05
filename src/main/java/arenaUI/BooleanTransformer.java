package arenaUI;
import org.apache.commons.collections15.Transformer;

public final class BooleanTransformer implements Transformer<Boolean, String> {

    @Override
    public String transform(Boolean tieneSugerencia) {
        return tieneSugerencia ? "SI" : "NO";
    }
}
