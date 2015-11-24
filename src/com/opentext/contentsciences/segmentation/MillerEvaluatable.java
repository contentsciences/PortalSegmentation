package com.opentext.contentsciences.segmentation;

import com.epicentric.segment.CannotFindResourceException;
import com.epicentric.segment.Evaluatable;

public class MillerEvaluatable implements Evaluatable {

	@Override
	public Object getProperty(String arg0) throws CannotFindResourceException {
		// TODO Auto-generated method stub
		System.out.println("MillerEvaluatable.getProperty() entry for argument: " + arg0);
		return arg0 + "_evaluatedvalue";
	}

}
