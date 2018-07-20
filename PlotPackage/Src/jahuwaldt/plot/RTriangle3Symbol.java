/*
*   RTriangle3Symbol  -- Represents a right triangle plot symbol, lower right.
*
*   Copyright (C) 2000-2002 by Joseph A. Huwaldt <jhuwaldt@knology.net>.
*   All rights reserved.
*   
*   This library is free software; you can redistribute it and/or
*   modify it under the terms of the GNU Library General Public
*   License as published by the Free Software Foundation; either
*   version 2 of the License, or (at your option) any later version.
*   
*   This library is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
*   Library General Public License for more details.
**/
package jahuwaldt.plot;

import java.awt.*;

/**
*  <p> This class represents a right triangle plot symbol with
*      the 90 degree corner in the lower right.
*  </p>
*
*  <p>  Modified by:  Joseph A. Huwaldt  </p>
*
*  @author  Joseph A. Huwaldt   Date:  January 2, 2001
*  @version January 2, 2001
**/
public class RTriangle3Symbol extends PolygonSymbol {

	//	Number of points in the coordinate arrays.
	private static final int kNumPoints = 3;
	
	//-------------------------------------------------------------------------
	/**
	*  Creates a right triangle with 90 deg angle in lower right corner plot
	*  symbol that has a width of 8 pixels, is transparent and
	*  has a border color of black.
	**/
	public RTriangle3Symbol() {}
	
	//-------------------------------------------------------------------------

	/**
	*  Method that determines the corner points of the
	*  polygon used to draw this plot symbol.  The corner
	*  points are stored in 2 arrays, xPoints and yPoints.
	*  If the arrays don't already exist for this symbol instance,
	*  this method must allocate them.
	*
	*  @param  x  The horizontal position of the center of the symbol.
	*  @param  y  The vertical position of the center of the symbol.
	**/
	protected void generatePoints(int x, int y) {

		//	Allocate memory for point arrays if they don't already exist.
		if (xPoints == null) {
			xPoints = new int[kNumPoints];
			yPoints = new int[kNumPoints];
		}
		
		int width3 = getSize()/3;
		int width32 = (int)( ((float)getSize()/3.F)*2.F );
		int xpw3 = x + width3;
		int ypw3 = y + width3;
		
		//	Determine points.
		xPoints[0] = x - width32;		yPoints[0] = ypw3;
		xPoints[1] = xpw3;				yPoints[1] = ypw3;
		xPoints[2] = xpw3;				yPoints[2] = y - width32;
		
	}
}


