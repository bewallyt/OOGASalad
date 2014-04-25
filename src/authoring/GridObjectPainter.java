package authoring;

import javax.swing.ImageIcon;

public class GridObjectPainter {

	public GridObjectPainter(int x, int y, ImageIcon image) {
		GridViewerFeature gridViewer=(GridViewerFeature)FeatureManager.getFeature("GridViewerFeature");
		Grid grid=gridViewer.getCurrentGrid();
		TilePanel panel=grid.getTilePanel(x, y);
		panel.addGridObjectImage(image);
		panel.revalidate();
	}

}
