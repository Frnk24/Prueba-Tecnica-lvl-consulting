package ms.clinica.gestion.dto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CollectionResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<T> elements;
    private Integer start;
    private Integer limit;
    private Long totalCount;

    public CollectionResponse(List<T> elements) {
        this.elements = elements;
    }

    public CollectionResponse(List<T> elements, Integer start, Integer limit, Long totalCount) {
        this.elements = elements;
        this.start = start;
        this.limit = limit;
        this.totalCount = totalCount;
    }
}
