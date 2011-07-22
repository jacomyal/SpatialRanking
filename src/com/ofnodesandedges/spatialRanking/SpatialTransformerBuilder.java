/*
Copyright 2008-2010 Gephi
Authors : Mathieu Bastian, Mathieu Jacomy, Julian Bilcke
Website : http://www.gephi.org

This file is part of Gephi.

Gephi is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

Gephi is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with Gephi.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ofnodesandedges.spatialRanking;

import org.gephi.graph.api.NodeData;
import org.gephi.ranking.api.Ranking;
import org.gephi.ranking.api.Transformer;
import org.gephi.ranking.spi.TransformerBuilder;
import org.openide.util.lookup.ServiceProvider;

/**
 * @author Alexis Jacomy
 */
@ServiceProvider(service = TransformerBuilder.class, position = 1000)
public class SpatialTransformerBuilder implements TransformerBuilder {
    
    public final String NODES_SPATIAL_RANKING = "nodes_spatial_ranking";
    
    @Override
    public Transformer buildTransformer() {
        return new NodeSpatialTransformer();
    }

    @Override
    public boolean isTransformerForElement(String elementType) {
        return elementType.equals(Ranking.NODE_ELEMENT);
    }

    @Override
    public String getName() {
        return NODES_SPATIAL_RANKING;
    }

    public static class NodeSpatialTransformer extends AbstractSpatialTransformer<NodeData> {

        @Override
        public Object transform(NodeData target, float normalizedValue) {
            float coordinate = getValue(normalizedValue);
            
            if(this.axe.equals("X")) target.setX(coordinate);
            else if(this.axe.equals("Y")) target.setY(coordinate);
            else if(this.axe.equals("Z")) target.setZ(coordinate);
            
            return Float.valueOf(coordinate);
        }
    }
}
