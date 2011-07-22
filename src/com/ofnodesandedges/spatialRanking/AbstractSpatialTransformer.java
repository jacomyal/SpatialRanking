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

import org.gephi.ranking.api.Transformer;
import org.gephi.ranking.plugin.transformer.AbstractTransformer;

/**
 * Spatial transformer. Use a linear scale + the interpolator to compute object's
 * coordinates.
 * 
 * @see Transformer
 * @author Alexis Jacomy
 */
public abstract class AbstractSpatialTransformer<Target> extends AbstractTransformer<Target> {

    protected float min = 0f;
    protected float max = 500f;
    protected String axe = "X";

    public AbstractSpatialTransformer() {}

    public AbstractSpatialTransformer(float lowerBound, float upperBound) {
        super(lowerBound, upperBound);
    }

    public AbstractSpatialTransformer(float lowerBound, float upperBound, float min, float max) {
        super(lowerBound, upperBound);
        this.min = min;
        this.max = max;
    }

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }

    public String getAxe() {
        return axe;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public void setAxe(String axe) {
        this.axe = axe;
    }

    public float getValue(float normalizedValue){
        return normalizedValue * (max - min) + min;
    }
}
