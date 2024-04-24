package OrangeHRMLive.DataProvider;
import OrangeHRMLive.DataProvider.OrangeHRM_BaseDataProvider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class OrangeHRM_AV_DataProvider extends OrangeHRM_BaseDataProvider

{
	

	@DataProvider(name="TransactionalData")
	public static Object[][] GetData(Method m) throws IOException
	{
		BaseDataProvider();// call to open the workbook and create the sheets
		
		
		//String m="LoginTest";
		//System.out.println(m.toString().indexOf("Login"));
		Object [][] obj;
		
		//System.out.println(m.toString().indexOf("Login"));
		
		ArrayList<String> arr= new ArrayList<>();// to find all the tests which are given as yes
		String transactionName= null;
		
		
		//System.out.println(className);
		if(m.toString().indexOf("Login")>=0)
		{
			createDynamicMapping("Login");
			transactionName= "Login";
			
			
		}
		else
			if(m.toString().indexOf("AddCandidate")>=0)
			{
				createDynamicMapping("AddCandidate");
				transactionName="AddCandidate";
				
			}
			
		System.out.println(transactionName);
		arr= GetTestCasesToRun(getTheSheet(transactionName), getTheMap(transactionName));
			
		
		int s= arr.size();
		obj= new Object[s][1];
		
		for(int i=0;i<s;i++)
		{
			obj[i][0]=arr.get(i);
			System.out.println(obj[i][0]);
		}
		return obj;
		
		
	}
	

}
