package org.springblade.neo4j.domain.relationship;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.*;
import org.springblade.neo4j.domain.DynamicEntity;

@Data
@RelationshipProperties
public class DynamicEntityRelationship {
    @Id
    @GeneratedValue
    Long id;

    @TargetNode
    private DynamicEntity endNode;
}
