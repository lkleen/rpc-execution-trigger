package org.larsworks.trading.data.collector.persistence.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.larsworks.trading.data.collector.repository.Repository;

import java.nio.file.Path;

/**
 */
public class JsonRepository extends Repository {

    @Getter
    @Setter
    @JsonIgnore
    Path path;

}
