package com.opentext.miller.segmentation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.epicentric.jdbc.ConnectionPool;
import com.epicentric.jdbc.ConnectionPoolManager;
import com.epicentric.jdbc.TimeoutException;
import com.epicentric.segment.CannotFindResourceException;
import com.epicentric.segment.Evaluatable;
import com.epicentric.user.User;
import com.vignette.portal.website.enduser.internal.PortalContextImpl;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

public class MillerEvaluatableRequest
  implements Evaluatable
{
  private HttpServletRequest request;
  private PortletRequest portletRequest;

  /*
   May want to define constants for expected properties if handling explicitly - see commented out switch statement in getProperty()
  private final String MILLER_FLOOR_PROPERTY="Floor";
  private final String MILLER_LOCATION_PROPERTY="Location";
  private final String MILLER_LEVEL_PROPERTY="Level";
  */
  
  public MillerEvaluatableRequest(HttpServletRequest request)
  {
	  System.out.println("Miller EvaluatableRequest - HttpServletRequest constructor");
    if (request == null) {
      throw new IllegalArgumentException("no null arguments allowed in EvaluatableRequest:EvaluatableRequest(request)");
    }

    this.request = request;
  }

  public MillerEvaluatableRequest(PortletRequest portletRequest) {
	  System.out.println("Miller EvaluatableRequest - portletRequest constructor");
    if (portletRequest == null) {
      throw new IllegalArgumentException("no null arguments allowed in EvaluatableRequest:EvaluatableRequest(request)");
    }

    this.portletRequest = portletRequest;
  }

  public Object getProperty(String propertyID) throws CannotFindResourceException
  {
	  
	// we have the request already - getProperty will get the specified prop value using the request and extrernal data  
	 System.out.println("Miller EvaluatableRequest - getProperty() entry: " + propertyID);
	  
	 PortalContextImpl portalContext=(PortalContextImpl)request.getAttribute("portalContext");
	 
	 User currentUser = portalContext.getCurrentUser();
	 String userName=(String)currentUser.getProperty("username");
	 /*
	  * currentUser.getProperty("username") will probably return the login name (depending on portal entity xmls).
	  * Expect to use this, with the passed in propertyID, in external db lookup & return the property value
	  */
	
	System.out.println("Miller EvaluatableRequest - getProperty() - user is " + currentUser.getDisplayName() + " : " + currentUser.getProperty("uid") + " : " + " : " + currentUser.getProperty("username"));
	  
	 
    if (propertyID == null)
      throw new IllegalArgumentException("no null arguments allowed in EvaluatableRequest:getProperty(String)");

    /*
     * for the simple db schema described, you could just do...
     */
    String selectProperty="SELECT PROPERTY_VALUE FROM MILLER_SEGMENTATION WHERE USER_ID=? AND PROPERTY_KEY=?";
    String propertyValue="NOT_FOUND";
    
    
    ConnectionPool connectionPool=null;
    Connection connection=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    
	try{
		
        connectionPool = ConnectionPoolManager.getDefaultPool();
		connection = connectionPool.getConnection();
		ps = connection.prepareStatement(selectProperty);

		ps.setString(1,userName);
		ps.setString(2,propertyID);

		rs = ps.executeQuery();

		while (rs.next()){
			/*
			 * may want to do simething if multiple rows found?
			 */
			propertyValue = rs.getString("PROPERTY_VALUE");
        }    
		return propertyValue;
	} catch (SQLException se) {
		se.printStackTrace();
	} catch (TimeoutException e) {
		e.printStackTrace();
	} finally {
		try {
			if (rs!=null)
				rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (ps!=null)
				ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (connection !=null)
				connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    /*
     * Or could switch on the property id, eg to query different tables...
     */
    		
    /*    
 	switch(propertyID) {
    	case MILLER_FLOOR_PROPERTY: {    		
    		String userFloor="something from a lookup";
    		return userFloor;
    	}
    	case MILLER_LEVEL_PROPERTY: {    		
    		String userLevel="something from a lookup";
    		return userLevel;
    	}
    	case MILLER_LOCATION_PROPERTY: {    		
    		String userLocation="something from a lookup";
    		return userLocation;
    	}
    	default: {
    		throw new IllegalArgumentException("Unexpected propertyid " + propertyID + " in MillerEvaluatableRequest:getProperty(String)");
    	}
    }
    */
    

    return null;
  }
}