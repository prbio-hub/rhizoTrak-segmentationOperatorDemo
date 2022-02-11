/* 
 * This file is part of the rhizoTrak project.
 * 
 * Note that rhizoTrak extends TrakEM2, hence, its code base substantially 
 * relies on the source code of the TrakEM2 project and the corresponding Fiji 
 * plugin, initiated by A. Cardona in 2005. Large portions of rhizoTrak's code 
 * are directly derived/copied from the source code of TrakEM2.
 * 
 * For more information on TrakEM2 please visit its websites:
 * 
 *  https://imagej.net/TrakEM2
 * 
 *  https://github.com/trakem2/TrakEM2/wiki
 * 
 * Fore more information on rhizoTrak, visit
 *
 *  https://github.com/prbio-hub/rhizoTrak/wiki
 *
 * Both projects, TrakEM2 and rhizoTrak, are released under GPL. 
 * Please find below first the copyright notice of rhizoTrak, and further on
 * (in case that this file was part of the original TrakEM2 source code base
 * and contained a TrakEM2 file header) the original file header with the 
 * TrakEM2 license note.
 */

/*
 * Copyright (C) 2018 - @YEAR@ by the rhizoTrak development team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Fore more information on rhizoTrak, visit
 *
 *    https://github.com/prbio-hub/rhizoTrak/wiki
 *
 */

package rhizoTrak.segmentation;

import de.unihalle.informatik.Alida.exceptions.ALDOperatorException;
import de.unihalle.informatik.Alida.exceptions.ALDProcessingDAGException;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import de.unihalle.informatik.Alida.annotations.ALDDerivedClass;
import de.unihalle.informatik.MiToBo.core.datatypes.MTBTreeNode;
import de.unihalle.informatik.MiToBo.core.datatypes.images.MTBImage;
import ij.ImagePlus;
import de.unihalle.informatik.MiToBo.apps.minirhizotron.datatypes.MTBRootTree;
import de.unihalle.informatik.MiToBo.apps.minirhizotron.datatypes.MTBRootTreeNodeData;
import de.unihalle.informatik.MiToBo.apps.minirhizotron.segmentation.*;

/**
 * Demo operator for showing how to implement a segmentation operator for
 * rhizoTrak.
 * <p>
 * The operator just instantiates to treelines, independent of the given 
 * image data.
 *
 * @author Birgit Moeller
 */

@ALDDerivedClass
public class RhizoTrakDummySegmentationDemo 
extends RootImageSegmentationOperator {

	/**
	 * @throws ALDOperatorException
	 */
	public RhizoTrakDummySegmentationDemo() throws ALDOperatorException {
		super();
	}

	/**
	 * This method does the actual work and is mandatory.
	 * @throws ALDOperatorException					Thrown in case of failure. 
	 * @throws ALDProcessingDAGException		Thrown in case of failure. 
	 */
	@SuppressWarnings("unused")
	@Override
	protected void operate() 
			throws ALDOperatorException, ALDProcessingDAGException {

		MTBTreeNode rnode;
		MTBRootTree rTree;
		MTBRootTreeNodeData nodeData;

		// init result hash map, will in the end contain vectors of treelines
		// indexed by the IDs of the target layers as hash values
		this.resultTreelines = new HashMap<>();

		// iterate over all layers for which images are provided
		Set<Integer> layerIDs = this.inputImages.keySet();
		for (Integer layerID : layerIDs) {
			
			// vector of resulting treelines for the current layer
			Vector<MTBRootTree> rTreelines = new Vector<MTBRootTree>();

			// get current image
			ImagePlus imp = this.inputImages.get(layerID);

			// ... as demo we will create a treeline in form of a cross in the center of the image
			
			// root node
			int centerX = imp.getWidth()/2;
			int centerY = imp.getHeight()/2;
			nodeData = new MTBRootTreeNodeData(centerX, centerY);
			rnode = new MTBTreeNode(nodeData);
			rTree = new MTBRootTree(rnode);
			
			// top branch 
			nodeData = new MTBRootTreeNodeData(centerX, centerY + imp.getHeight()/4);
			rnode.addChild(new MTBTreeNode(nodeData));
			// bottom branch 
			nodeData = new MTBRootTreeNodeData(centerX, centerY - imp.getHeight()/4);
			rnode.addChild(new MTBTreeNode(nodeData));
			// left branch 
			nodeData = new MTBRootTreeNodeData(centerX - imp.getWidth()/4, centerY);
			rnode.addChild(new MTBTreeNode(nodeData));
			// right branch 
			nodeData = new MTBRootTreeNodeData(centerX + imp.getWidth()/4, centerY);
			rnode.addChild(new MTBTreeNode(nodeData));
			
			// add new treeline to vector
			rTreelines.add(rTree);
			
			// store vector of treelines to result object
			this.resultTreelines.put(layerID, rTreelines);
		}			
	}

	@Override
	public String getUniqueClassIdentifier() {
		return "DummySegmentationDemo";
	}

	@Override
	public EnumSet<LayerSubset> getLayerSubsetForInputImages() {
		EnumSet<LayerSubset> subset = EnumSet.of(LayerSubset.ALL);
		return subset;
	}

	@Override
	public EnumSet<LayerSubset> getLayerSubsetForInputTreelines() {
		return EnumSet.noneOf(LayerSubset.class);
	}

	@Override
	public boolean getOnlySelectedTreelines() {
		return false;
	}

	@Override
	public OpWorkingMode getOperatorWorkingMode() {
		return OpWorkingMode.SEGMENTATION_CREATE;
	}

	@Override
	public boolean isToTransferDiameterOnUpdate() {
		return false;
	}

	@Override
	public boolean isToTransferStatusOnUpdate() {
		return false;
	}
}
