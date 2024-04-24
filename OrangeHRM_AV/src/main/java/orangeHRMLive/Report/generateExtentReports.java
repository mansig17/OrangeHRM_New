package orangeHRMLive.Report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class generateExtentReports

{
	public ExtentReports report;
	public void generateReport()
	{
		report= new ExtentReports();
		ExtentSparkReporter sp= new ExtentSparkReporter("rep.html");
		sp.viewConfigurer().viewOrder().as(new ViewName[] {ViewName.DASHBOARD, ViewName.TEST, ViewName.LOG});
		
		sp.config().setDocumentTitle("Report");
		sp.config().setTheme(Theme.DARK);
		sp.config().setReportName("Mansi Gupta");
		
		report.attachReporter(sp);
		
	}

}
