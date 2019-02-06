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

import java.util.HashMap;
import java.util.Map;

import de.unihalle.informatik.Alida.annotations.ALDDerivedClass;
import de.unihalle.informatik.MiToBo.core.datatypes.images.*;
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
	extends RootSegmentationOperator {

	/**
	 * Input image.
	 */
	private MTBImage inImg = null;

	/**
	 * Default operator.
	 * @throws ALDOperatorException		Thrown in case of failure.
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
		
		// copy input image to local data structure
		this.inImg = MTBImage.createMTBImage(this.image);
		
		// allocate result data structure
		this.resultLineMap = new HashMap<Integer, Map<Integer, Node>>();
		
		// first treeline
		HashMap<Integer, Node> fSeg = new HashMap<Integer, Node>();	
		fSeg.put(new Integer(1), new Node(-1, this.inImg.getSizeX()/2.0-100,
				this.inImg.getSizeY()/2.0));
		fSeg.put(new Integer(2), new Node(1, this.inImg.getSizeX()/2.0+100,
				this.inImg.getSizeY()/2.0));
		fSeg.put(new Integer(3), new Node(2, this.inImg.getSizeX()/2.0+150,
				this.inImg.getSizeY()/2.0+50));
		fSeg.put(new Integer(4), new Node(2, this.inImg.getSizeX()/2.0+150,
				this.inImg.getSizeY()/2.0-50));
		// ... add treeline to result hash map
		this.resultLineMap.put(new Integer(1), fSeg);
		
		// second treeline
		fSeg = new HashMap<Integer, Node>();	
		fSeg.put(new Integer(1), new Node(-1, this.inImg.getSizeX()/2.0-200,
				this.inImg.getSizeY()/4.0));
		fSeg.put(new Integer(2), new Node(1, this.inImg.getSizeX()/2.0-225,
				this.inImg.getSizeY()/4.0+50));
		fSeg.put(new Integer(3), new Node(2, this.inImg.getSizeX()/2.0-250,
				this.inImg.getSizeY()/4.0+100));
		fSeg.put(new Integer(4), new Node(3, this.inImg.getSizeX()/2.0-225,
				this.inImg.getSizeY()/4.0+150));
		// ... add treeline to result hash map
		this.resultLineMap.put(new Integer(2), fSeg);
	}

	@Override
	public String getUniqueClassIdentifier() {
		return "DummySegmentationDemo";
	}
}
