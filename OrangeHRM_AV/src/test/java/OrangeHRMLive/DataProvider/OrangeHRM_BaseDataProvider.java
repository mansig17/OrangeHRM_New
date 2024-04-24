package OrangeHRMLive.DataProvider;

import java.io.*;
import java.lang.reflect.Method;
import java.util.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.testng.annotations.DataProvider;


public class OrangeHRM_BaseDataProvider
{
	public static XSSFWorkbook workbook;
	static Map< String, Map<String, Integer>> masterMap;
	
	public static Map <String, Integer> TestScenarioMappingDriver, LoginMap, AddCandidateMap;
	public static XSSFSheet testCasesSheet, LoginSheet, AddCandidateSheet;
	
	static int driverRow;
	//The following function created mappings for all the sheets(DriverSheet and diff transactions)
	public static void BaseDataProvider() throws IOException
	{
		
		System.out.println("In base provider");
		File file= new File("C:\\Users\\SRIJAN\\OneDrive\\Desktop\\OrangeHRM.xlsx");
		FileInputStream fis= new FileInputStream(file);
		
		
		workbook= new XSSFWorkbook(fis);
		
		masterMap= new HashMap< String, Map<String, Integer>>();// attempting to create a master map which will hold 
		//all the mappings which are created dynamically whenever a transaction name is passed.
		
		//getting all the sheets
		
		testCasesSheet= workbook.getSheet("testCasesSheet");
		driverRow=testCasesSheet.getLastRowNum();
		
		//LoginSheet= workbook.getSheet("Login");
		//AddCandidateSheet= workbook.getSheet("AddCandidate");
		
		//creating the mappings
		
		TestScenarioMappingDriver=HashMappings(testCasesSheet);
		masterMap.put("testCasesSheet", TestScenarioMappingDriver);
		
		//LoginMap=HashMappings(LoginSheet);
		//AddCandidateMap=HashMappings(AddCandidateSheet);
		
		System.out.println("Got the maps");
		
		for(Sheet s: workbook)
		{
			System.out.println(s);
		}
		
		
	}
	
	//Common function to create mappings for sheets
	public static Map<String, Integer> HashMappings(XSSFSheet sheet)
	{
		Map<String, Integer> map= new HashMap<>();
		
		Row firstRow= sheet.getRow(0);
	
		
		for(Cell cell: firstRow)
		{
			map.put(cell.getStringCellValue(), cell.getColumnIndex());
		}
		
		System.out.print(map);
		return map;
	}
	
	
	public ArrayList<String> getTestCaseNames()
	{
		
		//System.out.println("Getting test case names");
		int lastRow= testCasesSheet.getLastRowNum();
		//System.out.println(lastRow);
		
		ArrayList<String> testCaseNames =new ArrayList<String>();  
		
		for(int i=1;i<lastRow;i++)
		{
			 Row currentRow = testCasesSheet.getRow(i);
			
			String TestRun= currentRow.getCell(TestScenarioMappingDriver.get("Run")).toString();
			String TestName= currentRow.getCell(TestScenarioMappingDriver.get("TestCaseName")).toString();
			
			if(TestRun.equalsIgnoreCase("yes"))
			{
				testCaseNames.add(TestName.toString());
				//System.out.print(i+" "+testCaseNames);
				
			}
			
		}
			return testCaseNames;
	}
	
	
	
	public ArrayList<String> getTransactionNames(String testCaseName)
	{
		int LastRow = testCasesSheet.getLastRowNum();
		//System.out.println(LastRow);
		int i;
		for(i=1; i<LastRow; i++)
		{
			Row currRow= testCasesSheet.getRow(i);
			if(currRow.getCell(TestScenarioMappingDriver.get("TestCaseName")).toString().equalsIgnoreCase(testCaseName))
				{
				System.out.println("TestCaseNameMatch");
				break;
				}
				
		}
		
		ArrayList<String> transactions= new ArrayList<String>();
		Row reqRow= testCasesSheet.getRow(i);
		
		for(int j=1;j<=2;j++)
		{
			System.out.println("Transaction"+j);
			Cell trans= reqRow.getCell(TestScenarioMappingDriver.get("Transaction"+j));
			if(trans!=null)
				transactions.add(trans.toString());
		}
		
		return transactions;
	}
	
	
	public static Cell GetCellData(String transactionName, String testCaseName, String cellName)
	{
		int r=1;
		Cell value=null;
		
		XSSFSheet sheet= workbook.getSheet(transactionName);
		int lastRow=sheet.getLastRowNum();
		Row row;
		
		
		for(r=1;r<lastRow;r++)
		{
			Row currRow= sheet.getRow(r);
						
				if(currRow.getCell(masterMap.get(transactionName).get("TestCaseName")).toString().equalsIgnoreCase(testCaseName))
				{
					break;
				}		
			
		}
		
		 row= sheet.getRow(r);
		 
		 value= row.getCell(masterMap.get(transactionName).get(cellName));
		
		
		/*if(transactionName.equalsIgnoreCase("Login"))
		{
		for(r=1;r<lastRow;r++)
		{
			Row currRow= sheet.getRow(r);
			
			
				if(currRow.getCell(LoginMap.get("TestCaseName")).toString().equalsIgnoreCase(testCaseName))
				{
					break;
				}
			
				
			
		}
		
		 row= sheet.getRow(r);
		 
		 value= row.getCell(LoginMap.get(cellName));
		}
		else
			if(transactionName.equalsIgnoreCase("testCasesSheet"))
			{
			for(r=1;r<lastRow;r++)
			{
				Row currRow= sheet.getRow(r);
				
				
					if(currRow.getCell(TestScenarioMappingDriver.get("TestCaseName")).toString().equalsIgnoreCase(testCaseName))
					{
						break;
					}
				
					
				
			}
			
			 row= sheet.getRow(r);
			 
			 value= row.getCell(TestScenarioMappingDriver.get(cellName));
			}*/
			
		
		
		return value;
		
	}
	
	
	public static String GetRunType(String testCaseName)
	{
		
		String runType= GetCellData("testCasesSheet", testCaseName,"Run").toString();
		System.out.println(runType);
		
		return runType;
	}
	
	public static ArrayList<String> GetTestCasesToRun( XSSFSheet sheet, Map<String, Integer> map)
	{
		System.out.println("Getting test cases to run");
		ArrayList<String> testCaseList= new ArrayList<>();
		System.out.println(sheet.toString());
		int lastRow= sheet.getLastRowNum();
		
		System.out.println(map);
		for(int i=1;i<=lastRow;i++)
		{
			
			Row currRow= sheet.getRow(i);
			
			//System.out.println(tname);
			System.out.println(currRow.getCell(map.get("FirstName")).toString());
			System.out.println(currRow.getCell(map.get("TestCaseName")).toString());
			
			String tc= currRow.getCell(map.get("TestCaseName")).toString();
			System.out.println("TEST CASE=" +tc);
			
			//GetRunType function checks whether the test case name found in the trnsaction sheet is set to run in the main driver sheet
			
			
			if(GetRunType(tc).equalsIgnoreCase("yes"))
			{
				
				testCaseList.add(tc);
				
			}
			
		}
		System.out.println(testCaseList);
		return testCaseList;
		
	}
	
	public static void createDynamicMapping(String transName)
	{
	
		XSSFSheet currSheet= null ;
		Map<String, Integer> currMap= null;
		currSheet=workbook.getSheet(transName);
		currMap= HashMappings(currSheet);
		masterMap.put(transName,currMap);
		
	}
	
	static XSSFSheet getTheSheet(String transaction)
	{
		return workbook.getSheet(transaction);
	}
	
	
	static Map<String, Integer> getTheMap(String transaction)
	{
		System.out.println(masterMap);
		return masterMap.get(transaction);
	}
	
	

}
