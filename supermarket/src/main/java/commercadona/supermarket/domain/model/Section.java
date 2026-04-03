package commercadona.supermarket.domain.model;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

public enum Section {
    HORNO,
    CAJAS,
    PESCADERIA,
    VERDURAS,
    DROGUERIA;

    @Getter @Setter
    private Set<String> skillsList = new HashSet<>();
}
