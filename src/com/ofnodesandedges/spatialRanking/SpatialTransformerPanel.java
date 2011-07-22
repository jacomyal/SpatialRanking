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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.gephi.ranking.api.Ranking;
import org.gephi.ranking.api.Transformer;
import org.gephi.ui.components.JRangeSlider;
import org.openide.util.NbPreferences;

/**
 *
 * @author Alexis Jacomy
 */
public class SpatialTransformerPanel extends JPanel {

    private static final int SLIDER_MAXIMUM = 100;
    private AbstractSpatialTransformer spatialTransformer;
    private Ranking ranking;

    public SpatialTransformerPanel(Transformer transformer, Ranking ranking) {
        initComponents();

        final String MIN_VALUE = "SpatialTransformerPanel_" + transformer.getClass().getSimpleName() + "_min";
        final String MAX_VALUE = "SpatialTransformerPanel_" + transformer.getClass().getSimpleName() + "_max";
        final String AXIS = "SpatialTransformerPanel_" + transformer.getClass().getSimpleName() + "_axis";

        spatialTransformer = (AbstractSpatialTransformer) transformer;
        this.ranking = ranking;

        float minValueStart = NbPreferences.forModule(SpatialTransformerPanel.class).getFloat(MIN_VALUE, spatialTransformer.getMin());
        float maxValueStart = NbPreferences.forModule(SpatialTransformerPanel.class).getFloat(MAX_VALUE, spatialTransformer.getMax());
        String axisStart = NbPreferences.forModule(SpatialTransformerPanel.class).get(AXIS, spatialTransformer.getAxe());
        
        spatialTransformer.setMin(minValueStart);
        spatialTransformer.setMax(maxValueStart);
        
        axeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "X", "Y", "Z" }));
        axeComboBox.setSelectedItem(axisStart);
        axeComboBox.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent evt) {
                spatialTransformer.setAxe(axeComboBox.getModel().getSelectedItem().toString());
                NbPreferences.forModule(SpatialTransformerPanel.class).put(AXIS, axeComboBox.getModel().getSelectedItem().toString());
            }
        });

        minValue.setValue(minValueStart);
        minValue.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                spatialTransformer.setMin((Float) minValue.getValue());
                NbPreferences.forModule(SpatialTransformerPanel.class).putFloat(MIN_VALUE, (Float) minValue.getValue());
            }
        });
        
        maxValue.setValue(maxValueStart);
        maxValue.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                spatialTransformer.setMax((Float) maxValue.getValue());
                NbPreferences.forModule(SpatialTransformerPanel.class).putFloat(MAX_VALUE, (Float) maxValue.getValue());
            }
        });

        //Range
        JRangeSlider slider = (JRangeSlider) rangeSlider;
        slider.setMinimum(0);
        slider.setMaximum(SLIDER_MAXIMUM);
        slider.setValue(0);
        slider.setUpperValue(SLIDER_MAXIMUM);
        slider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                JRangeSlider source = (JRangeSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    setRangeValues();
                }
            }
        });
        refreshRangeValues();
    }

    private void setRangeValues() {
        JRangeSlider slider = (JRangeSlider) rangeSlider;
        float low = slider.getValue() / 100f;
        float high = slider.getUpperValue() / 100f;
        spatialTransformer.setLowerBound(low);
        spatialTransformer.setUpperBound(high);

        lowerBoundLabel.setText(ranking.unNormalize(spatialTransformer.getLowerBound()).toString());
        upperBoundLabel.setText(ranking.unNormalize(spatialTransformer.getUpperBound()).toString());
    }

    private void refreshRangeValues() {
        JRangeSlider slider = (JRangeSlider) rangeSlider;
        slider.setValue((int) (spatialTransformer.getLowerBound() * 100f));
        slider.setUpperValue((int) (spatialTransformer.getUpperBound() * 100f));

        lowerBoundLabel.setText(ranking.unNormalize(spatialTransformer.getLowerBound()).toString());
        upperBoundLabel.setText(ranking.unNormalize(spatialTransformer.getUpperBound()).toString());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelMinValue = new javax.swing.JLabel();
        minValue = new javax.swing.JSpinner();
        labelMaxValue = new javax.swing.JLabel();
        maxValue = new javax.swing.JSpinner();
        labelRange = new javax.swing.JLabel();
        rangeSlider = new JRangeSlider();
        upperBoundLabel = new javax.swing.JLabel();
        lowerBoundLabel = new javax.swing.JLabel();
        axeComboBox = new javax.swing.JComboBox();
        labelAxis = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(225, 114));

        labelMinValue.setText(org.openide.util.NbBundle.getMessage(SpatialTransformerPanel.class, "SpatialTransformerPanel.labelMinValue.text")); // NOI18N

        minValue.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(1.0f), Float.valueOf(0.1f), null, Float.valueOf(0.5f)));

        labelMaxValue.setText(org.openide.util.NbBundle.getMessage(SpatialTransformerPanel.class, "SpatialTransformerPanel.labelMaxValue.text")); // NOI18N

        maxValue.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(4.0f), Float.valueOf(0.5f), null, Float.valueOf(0.5f)));

        labelRange.setText(org.openide.util.NbBundle.getMessage(SpatialTransformerPanel.class, "SpatialTransformerPanel.labelRange.text")); // NOI18N

        rangeSlider.setFocusable(false);

        upperBoundLabel.setFont(new java.awt.Font("Tahoma", 0, 10));
        upperBoundLabel.setForeground(new java.awt.Color(102, 102, 102));
        upperBoundLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        upperBoundLabel.setText(org.openide.util.NbBundle.getMessage(SpatialTransformerPanel.class, "SpatialTransformerPanel.upperBoundLabel.text")); // NOI18N

        lowerBoundLabel.setFont(new java.awt.Font("Tahoma", 0, 10));
        lowerBoundLabel.setForeground(new java.awt.Color(102, 102, 102));
        lowerBoundLabel.setText(org.openide.util.NbBundle.getMessage(SpatialTransformerPanel.class, "SpatialTransformerPanel.lowerBoundLabel.text")); // NOI18N

        axeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        labelAxis.setText(org.openide.util.NbBundle.getMessage(SpatialTransformerPanel.class, "SpatialTransformerPanel.labelAxis.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelMinValue)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addComponent(labelMaxValue)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maxValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelAxis)
                        .addGap(18, 18, 18)
                        .addComponent(axeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelRange)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lowerBoundLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                .addComponent(upperBoundLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(rangeSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMinValue)
                    .addComponent(minValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maxValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMaxValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAxis)
                    .addComponent(axeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelRange, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rangeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lowerBoundLabel)
                    .addComponent(upperBoundLabel))
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox axeComboBox;
    private javax.swing.JLabel labelAxis;
    private javax.swing.JLabel labelMaxValue;
    private javax.swing.JLabel labelMinValue;
    private javax.swing.JLabel labelRange;
    private javax.swing.JLabel lowerBoundLabel;
    private javax.swing.JSpinner maxValue;
    private javax.swing.JSpinner minValue;
    private javax.swing.JSlider rangeSlider;
    private javax.swing.JLabel upperBoundLabel;
    // End of variables declaration//GEN-END:variables
}
