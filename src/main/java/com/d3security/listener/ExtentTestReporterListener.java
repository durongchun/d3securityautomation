package com.d3security.listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.ResourceCDN;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.TestAttribute;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Test report listener
 * ExtentReports 
 *
 * @author Lucy
 * @version 1.0.0
 * @date 2022/10/20
 */
@Slf4j
public class ExtentTestReporterListener implements IReporter {
    /**
     * Report save path of file
     */
    private static final String OUTPUT_FOLDER = System.getProperty("user.dir") + File.separator + "target" + File.separator + "test-output" + File.separator + "extentreport" + File.separator;
 
    /**
     * Report name
     */
    private static final String FILE_NAME = "D3Security Automation Test Report";

    /**
     * Node
     */
    private ExtentReports extent;

    /**
     * Reproduce reports
     *
     * @param xmlSuites       List<XmlSuite>
     * @param suites          List<ISuite>
     * @param outputDirectory String
     */
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        log.info("Starts to reproduce extentreports Test Report");
        init();
        boolean createSuiteNode = false;
        if (suites.size() > 1) {
            createSuiteNode = true;
        }
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
            // If no test case in suite, directly skip and wouldn't reproduce  in reports.
            if (result.size() == 0) {
                continue;
            }
            // Count suite fail, pass and skip size
            int suiteFailSize = 0;
            int suitePassSize = 0;
            int suiteSkipSize = 0;
            ExtentTest suiteTest = null;
            // If multi-suite, in the report, the test results of the same suite are grouped together to create a first-level node.
            if (createSuiteNode) {
                suiteTest = extent.createTest(suite.getName()).assignCategory(suite.getName());
            }
            boolean createSuiteResultNode = false;
            if (result.size() > 1) {
                createSuiteResultNode = true;
            }
            for (ISuiteResult r : result.values()) {
                ExtentTest resultNode;
                ITestContext context = r.getTestContext();
                if (createSuiteResultNode) {
                    // If no suite, it will be created as a first-level node in SuiteResult, otherwise it will be created as a child node of suite.
                    if (null == suiteTest) {
                        resultNode = extent.createTest(r.getTestContext().getName());
                    } else {
                        resultNode = suiteTest.createNode(r.getTestContext().getName());
                    }
                } else {
                    resultNode = suiteTest;
                }
                if (resultNode != null) {
                    resultNode.getModel().setName(suite.getName() + " : " + r.getTestContext().getName());
                    if (resultNode.getModel().hasCategory()) {
                        resultNode.assignCategory(r.getTestContext().getName());
                    } else {
                        resultNode.assignCategory(suite.getName(), r.getTestContext().getName());
                    }
                    resultNode.getModel().setStartTime(r.getTestContext().getStartDate());
                    resultNode.getModel().setEndTime(r.getTestContext().getEndDate());
                    // Count data under SuiteResult 
                    int passSize = r.getTestContext().getPassedTests().size();
                    int failSize = r.getTestContext().getFailedTests().size();
                    int skipSize = r.getTestContext().getSkippedTests().size();
                    suitePassSize += passSize;
                    suiteFailSize += failSize;
                    suiteSkipSize += skipSize;
                    if (failSize > 0) {
                        resultNode.getModel().setStatus(Status.FAIL);
                    }
                    resultNode.getModel().setDescription(String.format("Pass: %s ; Fail: %s ; Skip: %s ;", passSize, failSize, skipSize));
                }
                buildTestNodes(resultNode, context.getFailedTests(), Status.FAIL);
                buildTestNodes(resultNode, context.getSkippedTests(), Status.SKIP);
                buildTestNodes(resultNode, context.getPassedTests(), Status.PASS);
            }
            if (suiteTest != null) {
                suiteTest.getModel().setDescription(String.format("Pass: %s ; Fail: %s ; Skip: %s ;", suitePassSize, suiteFailSize, suiteSkipSize));
                if (suiteFailSize > 0) {
                    suiteTest.getModel().setStatus(Status.FAIL);
                }
            }

        }
        extent.flush();
    }

    /**
     * Initialize file storage path
     */
    private void init() {
        // Create directory 
        File reportDir = new File(OUTPUT_FOLDER);
        if (!reportDir.exists() && !reportDir.isDirectory()) {
            reportDir.mkdir();
        }
        // Date format
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMdHms");
        String timeStr = simpleDateFormat.format(new Date());
        // Full name of reports reproduced
        String fullFileName = FILE_NAME + "-" + timeStr + ".html";
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(OUTPUT_FOLDER + fullFileName);
        // Set static files DNS
        // How to resolve if can't visit 'cdn.rawgit.com' 
        htmlReporter.config().setResourceCDN(ResourceCDN.EXTENTREPORTS);

        htmlReporter.config().setDocumentTitle("D3Security Automation Test Reports");
        htmlReporter.config().setReportName("D3Security Automation Test Reports");
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setCSS(".node.level-1  ul{ display:none;} .node.level-1.active ul{display:block;}");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setReportUsesManualConfiguration(true);
    }

    /**
     * Create node
     *
     * @param extenttest ExtentTest
     * @param tests      IResultMap
     * @param status     Status
     */
    private void buildTestNodes(ExtentTest extenttest, IResultMap tests, Status status) {
        // If have parent node, get the lable of parent node
        String[] categories = new String[0];
        if (extenttest != null) {
            List<TestAttribute> categoryList = extenttest.getModel().getCategoryContext().getAll();
            categories = new String[categoryList.size()];
            for (int index = 0; index < categoryList.size(); index++) {
                categories[index] = categoryList.get(index).getName();
            }
        }

        ExtentTest test;

        if (tests.size() > 0) {
            // Adjust test cases order, order by time
            Set<ITestResult> treeSet = new TreeSet<>(new Comparator<ITestResult>() {
                @Override
                public int compare(ITestResult o1, ITestResult o2) {
                    return o1.getStartMillis() < o2.getStartMillis() ? -1 : 1;
                }
            });
            treeSet.addAll(tests.getAllResults());
            for (ITestResult result : treeSet) {
                Object[] parameters = result.getParameters();
                StringBuffer name = new StringBuffer();
                
                for (Object param : parameters) {
                    name.append(param.toString());
                }
                if (name.length() > 0) {
                    if (name.length() > 50) {
                        name = new StringBuffer(name.substring(0, 49) + "...");
                    }
                } else {
                    name = new StringBuffer(result.getMethod().getMethodName());
                }
                if (extenttest == null) {
                    test = extent.createTest(name.toString());
                } else {
                    // When creating as a child node, set the same label as the parent node to facilitate report retrieval.
                    test = extenttest.createNode(name.toString()).assignCategory(categories);
                }
                // test.getModel().setDescription(description.toString());
                // test = extent.createTest(result.getMethod().getMethodName());
                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);

                List<String> outputList = Reporter.getOutput(result);
                for (String output : outputList) {
                    // Include the log output of the test cases in the report
                    test.debug(output);
                }
                if (result.getThrowable() != null) {
                    test.log(status, result.getThrowable());
                } else {
                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");
                }

                test.getModel().setStartTime(getTime(result.getStartMillis()));
                test.getModel().setEndTime(getTime(result.getEndMillis()));
            }
        }
    }

    /**
     * Convert millis to Calendar 
     *
     * @param millis millisecond
     * @return Data
     */
    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}
