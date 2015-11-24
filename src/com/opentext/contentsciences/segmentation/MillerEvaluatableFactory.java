package com.opentext.contentsciences.segmentation;

import javax.portlet.PortletRequest;

import com.epicentric.segment.Evaluatable;
import com.epicentric.segment.EvaluatableFactory;
import com.vignette.portal.website.enduser.PortalRequest;

public class MillerEvaluatableFactory implements EvaluatableFactory {

	@Override
	public Evaluatable getEvaluatableInstance(PortalRequest arg0) {
		// TODO Auto-generated method stub
		System.out.println("MillerEvaluatableFactory.getEvaluatableInstance() entry for PortalRequest argument: " + arg0);
		return new MillerEvaluatable();
	}

	@Override
	public Evaluatable getEvaluatableInstance(PortletRequest arg0) {
		// TODO Auto-generated method stub
		System.out.println("MillerEvaluatableFactory.getEvaluatableInstance() entry for PortletRequest argument: " + arg0);		
		return new MillerEvaluatable();
	}

}
