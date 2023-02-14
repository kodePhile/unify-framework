/*
 * Copyright detailHeight14 The Code Department
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.tcdng.unify.jasperreports.server;

import com.tcdng.unify.core.UnifyException;
import com.tcdng.unify.core.annotation.Component;
import com.tcdng.unify.core.report.Report;
import com.tcdng.unify.core.report.ReportPlacement;
import com.tcdng.unify.core.report.ReportTheme;
import com.tcdng.unify.core.report.ReportTheme.ThemeColors;

import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * Used for placement report layout.
 * 
 * @author The Code Department
 * @since 1.0
 */
@Component("jasperreports-placementlayoutmanager")
public class JasperReportsPlacementLayoutManager extends AbstractJasperReportsLayoutManager {

    @Override
    protected void doApplyLayout(JasperDesign jasperDesign, ColumnStyles columnStyles, Report report)
            throws UnifyException {
        ReportTheme theme = report.getReportTheme();
        ThemeColors detailColors = theme.getDetailTheme();

        // Construct detail band
        clearDetailSection(jasperDesign);
        JRDesignBand detailBand = new JRDesignBand();
        detailBand.setHeight(jasperDesign.getPageHeight());
        for (ReportPlacement reportReplacement : report.getPlacements()) {
            JRDesignElement jRDesignElement = newPlacementJRDesignElement(jasperDesign, detailColors,
                    columnStyles.getNormalStyle(), reportReplacement);
            detailBand.addElement(jRDesignElement);
        }
        
        addDetailBand(jasperDesign, detailBand);
    }
}
