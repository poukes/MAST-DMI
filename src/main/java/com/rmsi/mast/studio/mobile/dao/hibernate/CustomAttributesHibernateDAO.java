package com.rmsi.mast.studio.mobile.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.rmsi.mast.studio.dao.hibernate.GenericHibernateDAO;
import com.rmsi.mast.studio.domain.CustomAttributes;
import com.rmsi.mast.studio.domain.ResourceAttributeValues;
import com.rmsi.mast.studio.domain.ResourcePOIAttributeValues;
import com.rmsi.mast.studio.mobile.dao.CustomAttributesDAO;


@Repository
public class CustomAttributesHibernateDAO extends GenericHibernateDAO<CustomAttributes, Integer> implements CustomAttributesDAO{

	
	private static final Logger logger = Logger.getLogger(CustomAttributesHibernateDAO.class);
	@Override
	public CustomAttributes addResourceCustomAttributeValues(
			CustomAttributes customAttributes) {
		 try {
	            return makePersistent(customAttributes);

	        } catch (Exception ex) {
	            System.out.println("Exception while adding data...." + ex);
	            logger.error(ex);
	            throw ex;
	        }
	}
	@Override
	public List<Object[]> getResourceAttributeValuesBylandId(
			Integer projectId, Integer landId) {
		try{
			List<CustomAttributes> lstResourceDetails= new ArrayList<>();
		

		String query = "Select Distinct RA.LandID,RA.customattributevalueid,RA.attributevalue,AM.customattributeid,AC.categoryName,cust.attributeoptionsid, cust.optiontext "+
						"from la_ext_resource_custom_attributevalue RA,la_ext_resource_custom_attribute AM,la_ext_attributecategory AC, la_ext_customattributeoptions cust "+
						"Where RA.customattributeid=AM.customattributeid And AM.AttributeCategoryID=AC.AttributeCategoryID And cust.attributeoptionsid=RA.attributeoptionsid "+
						"and RA.projectid ="+projectId+" And RA.LandID="+landId ;

		List<Object[]> arrObject = getEntityManager().createNativeQuery(query).getResultList();
		
	/*	for(Object [] object : arrObject){
			CustomAttributes resourceattributesvalues = new CustomAttributes();
			resourceattributesvalues.setLandid(Integer.valueOf(object[0].toString()));
			resourceattributesvalues.setCustomattributevalueid(Integer.valueOf(object[1].toString()));
			resourceattributesvalues.setAttributevalue(object[2].toString());
			resourceattributesvalues.setFieldname(Integer.parseInt(object[5].toString()));
			
			lstResourceDetails.add(resourceattributesvalues);
		}
		return lstResourceDetails;*/
		return arrObject;
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
	}
	@Override
	public List<CustomAttributes> getResourceCustomAttributeValuesBylandId(
			Integer projectId, Integer landId) {
		try{
			
			Query query= getEntityManager().createQuery("select re from CustomAttributes re where re.projectid =:projectid and re.landid =:landid" );
			
				List<CustomAttributes> lstResourceDetails = query.setParameter("projectid", projectId).setParameter("landid", landId).getResultList();
			
		

		
		
		
			
			
		
		return lstResourceDetails;
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
	}
	@Override
	public List<ResourcePOIAttributeValues> getResourcePoiValuesBylandId(
			Integer projectId, Integer landId) {
		try{
			
			Query query= getEntityManager().createQuery("select re from ResourcePOIAttributeValues re where re.projectid =:projectid and re.landid =:landid" );
			
				List<ResourcePOIAttributeValues> lstResourceDetails = query.setParameter("projectid", projectId).setParameter("landid", landId).getResultList();
			
		

		
		
		
			
			
		
		return lstResourceDetails;
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
	}
	@Override
	public List<CustomAttributes> getResourceCustomAttributeValuesBylandid(
			Integer projectId, Integer landId) {
		
		List<CustomAttributes> lstResourceDetails= new ArrayList<>();
		try{
			String query = "Select Distinct RA.LandID,RA.customattributevalueid,RA.attributevalue,AM.customattributeid,AC.categoryName,cust.attributeoptionsid, cust.optiontext "+
					"from la_ext_resource_custom_attributevalue RA,la_ext_resource_custom_attribute AM,la_ext_attributecategory AC, la_ext_customattributeoptions cust "+
					"Where RA.customattributeid=AM.customattributeid And AM.AttributeCategoryID=AC.AttributeCategoryID And cust.attributeoptionsid=RA.attributeoptionsid "+
					"and RA.projectid ="+projectId+" And RA.LandID="+landId ;

	List<Object[]> arrObject = getEntityManager().createNativeQuery(query).getResultList();
			
			
			/*Query query= getEntityManager().createQuery("select re from CustomAttributes re where re.projectid =:projectid and re.landid =:landid" );
			
				List<CustomAttributes> lstResourceDetails = query.setParameter("projectid", projectId).setParameter("landid", landId).getResultList();
			
		*/

	for(Object [] object : arrObject){
		CustomAttributes resourceattributesvalues = new CustomAttributes();
		resourceattributesvalues.setLandid(Integer.valueOf(object[0].toString()));
		resourceattributesvalues.setCustomattributevalueid(Integer.valueOf(object[1].toString()));
		resourceattributesvalues.setAttributevalue(object[2].toString());
		resourceattributesvalues.setAttributeoptionsid(Integer.parseInt(object[5].toString()));
//		resourceattributesvalues.setFieldname(Integer.parseInt(object[6].toString()));
		resourceattributesvalues.setGeomtype(object[6].toString());
		
		lstResourceDetails.add(resourceattributesvalues);
	}
	return lstResourceDetails;
		
		
			
			
		
		
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
	}
	
}
