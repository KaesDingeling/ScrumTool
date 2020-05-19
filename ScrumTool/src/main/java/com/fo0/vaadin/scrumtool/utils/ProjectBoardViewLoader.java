package com.fo0.vaadin.scrumtool.utils;

import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

import com.fo0.vaadin.scrumtool.data.table.ProjectData;
import com.fo0.vaadin.scrumtool.data.table.ProjectDataCard;
import com.fo0.vaadin.scrumtool.data.table.ProjectDataColumn;
import com.fo0.vaadin.scrumtool.views.ProjectBoardView;
import com.fo0.vaadin.scrumtool.views.components.ColumnComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ProjectBoardViewLoader {

	public static void createMissingColumns(ProjectBoardView view, HorizontalLayout currentColumns, Set<ProjectDataColumn> latestColumns) {
		latestColumns.stream().forEachOrdered(latestColumnAtDb -> {

			ColumnComponent cc = view.getColumnLayoutById(latestColumnAtDb.getId());
			if (cc == null) {
				cc = checkForMissingColumn(view, currentColumns, latestColumnAtDb);
			} else {
				log.info("[COLUMN] no update: " + cc.getId());
			}

			checkForMissingCard(view, latestColumnAtDb, cc);
		});
	}

	private static ColumnComponent checkForMissingColumn(ProjectBoardView view, HorizontalLayout currentColumns,
			ProjectDataColumn latestColumnAtDb) {
		log.info("[COLUMN] add missing column: {} - {}", latestColumnAtDb.getId(), latestColumnAtDb.getName());
		return view.addColumn(latestColumnAtDb.getId(), latestColumnAtDb.getName());
	}

	private static void checkForMissingCard(ProjectBoardView view, ProjectDataColumn latestColumnAtDb, ColumnComponent ccc) {
		latestColumnAtDb.getCards().stream().forEachOrdered(pdc -> {
			ProjectDataCard pdcc = ccc.getCardById(pdc.getId());
			if (pdcc == null) {
				log.info("[CARD] update: column {} - card {} - {}", ccc.getId().get(), pdc.getId(), pdc.getText());
				view.addCard(ccc.getId().get(), pdc.getId(), pdc.getText());
			} else {
				log.info("[CARD] no card update: " + pdcc.getId());
			}
		});
	}

	public static void loadData(ProjectBoardView view, ProjectData latestData) {
		if (latestData == null) {
			log.info("no data found");
			return;
		}

		if (CollectionUtils.isEmpty(latestData.getColumns())) {
			log.info("no columns found");
			return;
		}

		createMissingColumns(view, view.getColumns(), latestData.getColumns());
	}

}
