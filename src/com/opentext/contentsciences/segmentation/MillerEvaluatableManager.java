package com.opentext.contentsciences.segmentation;


import com.epicentric.segment.EvaluatableManager;
import com.epicentric.segment.PropertySchema;
import com.epicentric.segment.PropertySchema.PropertyDefinition;

public class MillerEvaluatableManager implements EvaluatableManager {

	@Override
	public PropertySchema getPropertySchema() {
		// TODO Auto-generated method stub
		System.out.println("MillerEvaluatableManager.getPropertySchema() entry");
		PropertySchema millerSchema = new MillerPropertySchema(MillerPropertyOneClass.class);
		return millerSchema;
	}

}
