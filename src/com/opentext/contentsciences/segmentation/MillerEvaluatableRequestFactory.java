package com.opentext.contentsciences.segmentation;

import com.epicentric.segment.Evaluatable;
import com.epicentric.segment.EvaluatableFactory;
import com.vignette.portal.website.enduser.PortalRequest;

import javax.portlet.PortletRequest;

public class MillerEvaluatableRequestFactory
  implements EvaluatableFactory
{
  public Evaluatable getEvaluatableInstance(PortalRequest portalRequest)
  {
	 
	System.out.println("Miller MillerEvaluatableRequestFactory - getEvaluatableInstance for PortalRequest");
    return new MillerEvaluatableRequest(portalRequest.getRequest());
  }

  public Evaluatable getEvaluatableInstance(PortletRequest portletRequest)
  {
		System.out.println("Miller MillerEvaluatableRequestFactory - getEvaluatableInstance for PortletRequest");
    return new MillerEvaluatableRequest(portletRequest);
  }
}