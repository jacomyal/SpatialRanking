/*
Copyright 2008-2010 Gephi
Authors : Mathieu Bastian <mathieu.bastian@gephi.org>
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

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import org.gephi.ranking.api.Ranking;
import org.gephi.ranking.api.Transformer;
import org.gephi.ranking.spi.TransformerUI;
import org.openide.util.NbBundle;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Alexis Jacomy
 */
@ServiceProvider(service = TransformerUI.class, position = 1000)
public class SpatialTransformerUI implements TransformerUI {

    @Override
    public Icon getIcon() {
        return new ImageIcon(getClass().getResource("/com/ofnodesandedges/spatialRanking/resources/xyz.png"));
    }

    @Override
    public boolean isUIForTransformer(Transformer transformer) {
        return transformer instanceof SpatialTransformerBuilder.NodeSpatialTransformer;
    }

    @Override
    public JPanel getPanel(Transformer transformer, Ranking ranking) {
        return new SpatialTransformerPanel(transformer, ranking);
    }

    @Override
    public String getDisplayName() {
        return NbBundle.getMessage(SpatialTransformerUI.class, "SpatialTransformerUI.name");
    }
}
